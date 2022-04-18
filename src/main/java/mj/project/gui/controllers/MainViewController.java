package mj.project.gui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import mj.project.configurations.AppConfig;
import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.RSAService;
import mj.project.encryption.services.SessionKeyService;
import mj.project.exceptions.PortRangeException;
import mj.project.exceptions.RecipientKeyNotFound;
import mj.project.gui.events.OpenSettingsWindowEventHandler;
import mj.project.networking.data.NetworkPropertiesStorage;
import mj.project.networking.message.MessageType;
import mj.project.networking.message.content.Message;
import mj.project.networking.services.ClientSocketService;
import mj.project.networking.services.MessageService;
import mj.project.networking.services.ServerSocketService;

import javax.crypto.SecretKey;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainViewController {

    @FXML
    TextArea textArea;

    @FXML
    Button sendButton;

    @FXML
    Button attachFileButton;

    @FXML
    MenuItem optionsMenuItem;

    @FXML
    TextField recipientPortTextField;

    @FXML
    Button connectButton;

    @FXML
    Button listenButton;

    @FXML
    Button stopButton;

    @FXML
    Label statusLabel;

    @FXML
    Pane messageContainer;

    @FXML
    Button sendSessionKeyButton;

    @FXML
    Pane logsContainer;

    @FXML
    ProgressBar progressBar;

    private final KeyStorage keyStorage;
    private final NetworkPropertiesStorage networkPropertiesStorage;

    private final SessionKeyService sessionKeyService;
    private final MessageService messageService;
    private final ClientSocketService clientSocketService;
    private final ServerSocketService serverSocketService;
    private final RSAService rsaService;


    @Inject
    public MainViewController(KeyStorage keyStorage,
                              NetworkPropertiesStorage networkPropertiesStorage,
                              SessionKeyService sessionKeyService,
                              MessageService messageService,
                              ClientSocketService clientSocketService,
                              ServerSocketService serverSocketService, RSAService rsaService) {
        this.keyStorage = keyStorage;
        this.networkPropertiesStorage = networkPropertiesStorage;
        this.sessionKeyService = sessionKeyService;
        this.messageService = messageService;
        this.clientSocketService = clientSocketService;
        this.serverSocketService = serverSocketService;
        this.rsaService = rsaService;
    }

    @FXML
    public void initialize() {
        Controllers.setMainViewController(this);

        // initial stuff

        // send event
        sendButton.setOnMouseClicked(event -> {
            String messageContent = textArea.getText();
            byte[] messageContentEncoded = sessionKeyService.encrypt(messageContent.getBytes(StandardCharsets.UTF_8), keyStorage.getSessionKey());
            Message messageSimpleContent = messageService.createMessage(List.of(messageContentEncoded), MessageType.TEXT);
            clientSocketService.sendMessage(messageSimpleContent);
        });

        // connect event
        connectButton.setOnMouseClicked(event -> {
            try {
                int recipientPort = Integer.parseInt(recipientPortTextField.getText());
                if (recipientPort < 0 || recipientPort > 65535) {
                    throw new PortRangeException();
                }
                if (keyStorage.getThisKeyPair() == null) {
                    log("Your key pair not found. Generating new key pair ...");
                    KeyPair newKeyPair = rsaService.createKeyPair();
                    keyStorage.setThisKeyPair(newKeyPair);
                }
                log("Connecting with a host ...");

                // connect with a server
                clientSocketService.startConnection(networkPropertiesStorage.getTargetIp(), Integer.parseInt(recipientPortTextField.getText()));
                byte[] encodedPublicKey = keyStorage.getThisKeyPair().getPublic().getEncoded();
                Message publicKeyMessageSimpleContent = messageService.createMessage(List.of(encodedPublicKey), MessageType.PUBLIC_KEY);
                clientSocketService.sendMessage(publicKeyMessageSimpleContent);

                // now send session key bro
                if (keyStorage.getSessionKey() == null) {
                    PublicKey recipientPublicKey = keyStorage.getRecipientPublicKey();
                    if (recipientPublicKey == null) {
                        log("Recipient key not found");
                        throw new RecipientKeyNotFound();
                    }
                    SecretKey sessionKey = sessionKeyService.createSessionKey();
                    byte[] encryptedSessionKey = sessionKeyService.encryptSessionKey(sessionKey, recipientPublicKey);
                    Message sessionKeyMessageSimpleContent = messageService.createMessage(List.of(encryptedSessionKey), MessageType.SESSION_KEY);
                    keyStorage.setSessionKey(sessionKey);
                    clientSocketService.sendMessage(sessionKeyMessageSimpleContent);
                    log("Session key has been sent to the user");
                }
            } catch (NumberFormatException e) {
                log("Error: Port number contains invalid characters");
            } catch (PortRangeException e) {
                log("Error: Port number is out of range");
            } catch (IOException e) {
                log("Error: Connection failed");
                e.printStackTrace();
            }
        });
        listenButton.setOnMouseClicked(event -> serverSocketService.startListening());
        stopButton.setOnMouseClicked(event -> serverSocketService.stopListening());

        attachFileButton.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Specific files", AppConfig.ALLOWED_FILE_EXTENSIONS);
            fileChooser.getExtensionFilters().add(extensionFilter);
            File selectedFile = fileChooser.showOpenDialog(logsContainer.getScene().getWindow());
            byte[] fileBytes = null;
            try {
                fileBytes = Files.readAllBytes(selectedFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (fileBytes == null) {
                throw new RuntimeException();
            }
            List<byte[]> packetList = new ArrayList<>();
            for (int i = 0; i < fileBytes.length; i += AppConfig.MAX_BYTES_PER_PACKET) {
                byte[] packet = Arrays.copyOfRange(fileBytes, i, Math.min(fileBytes.length, i + AppConfig.MAX_BYTES_PER_PACKET));
                byte[] encryptedPacket = sessionKeyService.encrypt(packet, keyStorage.getSessionKey());
                packetList.add(encryptedPacket);
            }
            Message message = messageService.createMessage(packetList, MessageType.FILE);
            clientSocketService.sendMessage(message);
        });

        //todo
        optionsMenuItem.setOnAction(new OpenSettingsWindowEventHandler());

        sendSessionKeyButton.setOnMouseClicked(event -> {
        });
    }

    private void log(String text) {
        logsContainer.getChildren().add(new Label(text));
    }

    public static void addLog(String text) {
        Platform.runLater(() -> Controllers.getMainViewController().log(text));
    }
}
