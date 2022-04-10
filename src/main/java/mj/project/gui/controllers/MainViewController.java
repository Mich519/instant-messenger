package mj.project.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mj.project.configurations.AppInitializer;
import mj.project.gui.events.*;
import mj.project.networking.ServerSocketService;
import mj.project.networking.message.Message;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;

public class MainViewController {

    private Stage stage;

    @FXML
    TextArea textArea;

    @FXML
    Button sendButton;

    @FXML
    Button attachFileButton;

    @FXML
    MenuItem optionsMenuItem;

    @FXML
    TextField receiverPortTextField;

    @FXML
    Button connectButton;

    @FXML
    Button listenButton;

    @FXML
    Button stopButton;

    @FXML
    Label statusLabel;

    @FXML
    VBox messageContainer;

    @FXML
    Button sendSessionKeyButton;

    @Inject
    private SendMessageEventHandler sendMessageEventHandler;

    @Inject
    private ConnectEventHandler connectEventHandler;

    @Inject
    private SendSessionKeyEventHandler sendSessionKeyEventHandler;

    @Inject
    private AppInitializer appInitializer;

    @Inject
    private ListenEventHandler listenEventHandler;

    @Inject
    private StopEventHandler stopEventHandler;

    @FXML
    public void initialize() {
        Controllers.setMainViewController(this);

        // initial stuff
        appInitializer.init();


        // initialize event handlers for controls
        sendButton.setOnMouseClicked(sendMessageEventHandler);
        connectButton.setOnMouseClicked(connectEventHandler);
        listenButton.setOnMouseClicked(listenEventHandler);
        stopButton.setOnMouseClicked(stopEventHandler);
        attachFileButton.setOnMouseClicked(new ChooseFileEventHandler(stage));
        optionsMenuItem.setOnAction(new OpenSettingsWindowEventHandler());

        sendSessionKeyButton.setOnMouseClicked(sendSessionKeyEventHandler);
    }

    public void addMessage(byte[] messageBytes) {
        Label messageLabel = new Label(new String(messageBytes, StandardCharsets.US_ASCII));
        messageContainer.getChildren().add(messageLabel);
    }
    public void addMessage(Message message) {
        String textContent = new String(message.getContent(), StandardCharsets.UTF_8);
        String textNickname = new String(message.getSenderName(), StandardCharsets.UTF_8);
        Label messageLabel = new Label(textNickname + ": " + textContent);
        messageContainer.getChildren().add(messageLabel);
    }

    public void updateStatus(String text) {
        statusLabel.setText(text);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
