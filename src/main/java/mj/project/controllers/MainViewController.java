package mj.project.controllers;

import javafx.fxml.FXML;
import mj.project.events.OpenSettingsWindowEventHandler;
import mj.project.events.SendMessageEventHandler;
import javafx.scene.control.*;

public class MainViewController {

    @FXML
    TextArea textArea;

    @FXML
    Button sendButton;

    @FXML
    MenuItem optionsMenuItem;

    @FXML
    public void initialize() {
        sendButton.setOnMouseClicked(new SendMessageEventHandler());
        optionsMenuItem.setOnAction(new OpenSettingsWindowEventHandler());
    }
}
