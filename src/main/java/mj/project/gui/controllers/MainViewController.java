package mj.project.gui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mj.project.configurations.AppConfig;
import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.RSAService;
import mj.project.encryption.services.SessionKeyService;
import mj.project.exceptions.PortRangeException;
import mj.project.exceptions.RecipientKeyNotFound;
import mj.project.exceptions.SessionKeyNotExchangedException;
import mj.project.gui.events.OpenSettingsWindowEventHandler;
import mj.project.networking.data.NetworkPropertiesStorage;
import mj.project.networking.message.Message;
import mj.project.networking.message.MessageType;
import mj.project.networking.services.ClientSocketService;
import mj.project.networking.services.MessageService;
import mj.project.networking.services.ServerSocketService;

import javax.crypto.SecretKey;
import javax.inject.Inject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PublicKey;

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
            if (keyStorage.getSessionKey() == null) {
                PublicKey recipientPublicKey = keyStorage.getRecipientPublicKey();
                if (recipientPublicKey == null) {
                    log("Recipient key not found");
                    throw new RecipientKeyNotFound();
                }
                SecretKey sessionKey = sessionKeyService.createSessionKey();
                byte[] encryptedSessionKey = sessionKeyService.encryptSessionKey(sessionKey, recipientPublicKey);
                Message sessionKeyMessage = messageService.createMessage(encryptedSessionKey, MessageType.SESSION_KEY);
                keyStorage.setSessionKey(sessionKey);
                clientSocketService.sendMessage(sessionKeyMessage);
                log("Session key has been sent to the user");
            }
            String messageContent = textArea.getText();
            byte[] messageContentEncoded = sessionKeyService.encrypt(messageContent.getBytes(StandardCharsets.UTF_8), keyStorage.getSessionKey());
            Message message = messageService.createMessage(messageContentEncoded, MessageType.TEXT);
            clientSocketService.sendMessage(message);
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
                Message publicKeyMessage = messageService.createMessage(encodedPublicKey, MessageType.PUBLIC_KEY);
                clientSocketService.sendMessage(publicKeyMessage);
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

    public void setStage(Stage stage) {
    }
}
