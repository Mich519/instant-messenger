package mj.project.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import mj.project.gui.events.SaveSettingsEventHandler;

public class SettingsViewController {

    @FXML
    Button saveSettingsButton;

    @FXML
    public void initialize() {
        Controllers.setSettingsViewController(this);
        saveSettingsButton.setOnMouseClicked(new SaveSettingsEventHandler());
    }
}
