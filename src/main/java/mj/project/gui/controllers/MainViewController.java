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
import mj.project.gui.event_handlers.*;
import mj.project.gui.events.OpenSettingsWindowEventHandler;
import mj.project.networking.data.NetworkPropertiesStorage;
import mj.project.networking.message.MessageType;
import mj.project.networking.message.Message;
import mj.project.networking.sockets.ClientSocketService;
import mj.project.networking.message.MessageService;
import mj.project.networking.sockets.ServerSocketService;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyPair;
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

    @FXML
    TextField loadKeyPairPasswordTextField;

    @FXML
    TextField generateKeyPairPasswordTextField;

    @FXML
    Button loadKeyPairButton;

    @FXML
    Button generateKeyPairButton;

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

        sendButton.setOnMouseClicked(new SendButtonEventHandler(textArea, sessionKeyService, keyStorage, messageService, clientSocketService));
        connectButton.setOnMouseClicked(new ConnectButtonEventHandler(keyStorage, recipientPortTextField, clientSocketService, networkPropertiesStorage, messageService, sessionKeyService));
        listenButton.setOnMouseClicked(new ListenButtonEventHandler(keyStorage, serverSocketService));
        stopButton.setOnMouseClicked(new StopButtonEventHandler(serverSocketService));
        generateKeyPairButton.setOnMouseClicked(new GenerateKeyPairButtonEventHandler(generateKeyPairPasswordTextField, rsaService, keyStorage));
        loadKeyPairButton.setOnMouseClicked(new LoadKeyPairButtonEventHandler(loadKeyPairPasswordTextField, rsaService, keyStorage));
        attachFileButton.setOnMouseClicked(new AttachFileButtonEventHandler(logsContainer, sessionKeyService, keyStorage, messageService, clientSocketService));

        //todo
        optionsMenuItem.setOnAction(new OpenSettingsWindowEventHandler());
        sendSessionKeyButton.setOnMouseClicked(event ->
        {
        });
    }

    private void log(String text) {
        logsContainer.getChildren().add(new Label(text));
    }

    private void appendTextMessageLabel(String labelText) {
        Label l = new Label(labelText);
        messageContainer.getChildren().add(l);
    }

    public static void addLog(String text) {
        Platform.runLater(() -> Controllers.getMainViewController().log(text));
    }

    public static void addTextMessage(String content) {
        Platform.runLater(() -> Controllers.getMainViewController().appendTextMessageLabel(content));
    }
}
