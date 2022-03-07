package mj.project.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import mj.project.events.ChooseFileEventHandler;
import mj.project.events.OpenSettingsWindowEventHandler;
import mj.project.events.SendMessageEventHandler;

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
    public void initialize() {

        // initialize event handlers for controls
        sendButton.setOnMouseClicked(new SendMessageEventHandler());
        attachFileButton.setOnMouseClicked(new ChooseFileEventHandler(stage));
        optionsMenuItem.setOnAction(new OpenSettingsWindowEventHandler());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
