package mj.project.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mj.project.configurations.AppConfig;
import mj.project.events.*;
import mj.project.sockets.ServerSocketService;

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
    Label statusLabel;

    @FXML
    public void initialize() {
        // open server socket connection
        ServerSocketService.getInstance().startListening(AppConfig.getMY_PORT());

        // initialize event handlers for controls
        sendButton.setOnMouseClicked(new SendMessageEventHandler(textArea, statusLabel));
        connectButton.setOnMouseClicked(new ConnectEventHandler(receiverPortTextField, statusLabel));
        listenButton.setOnMouseClicked(new ListenEventHandler(statusLabel));
        attachFileButton.setOnMouseClicked(new ChooseFileEventHandler(stage));
        optionsMenuItem.setOnAction(new OpenSettingsWindowEventHandler());


    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
