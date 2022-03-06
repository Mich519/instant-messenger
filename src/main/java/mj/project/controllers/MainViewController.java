package mj.project.controllers;

import javafx.fxml.FXML;
import mj.project.events.SendMessageEventHandler;
import javafx.scene.control.*;

public class MainViewController {

    @FXML
    private TextArea textArea;

    @FXML
    private Button sendButton;

    @FXML
    void initialize() {
        sendButton.setOnMouseClicked(new SendMessageEventHandler());

    }
}
