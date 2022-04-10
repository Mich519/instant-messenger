package mj.project.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import mj.project.encryption.block_ciphers.BlockCiphers;
import mj.project.gui.events.SaveSettingsEventHandler;
import mj.project.gui.events.SelectBlockCypherEventHandler;

public class SettingsViewController {

    @FXML
    Button saveSettingsButton;

    @FXML
    RadioButton cbcRadioButton;

    @FXML
    RadioButton ecbRadioButton;

    @FXML
    public void initialize() {
        Controllers.setSettingsViewController(this);
        //saveSettingsButton.setOnMouseClicked(new SaveSettingsEventHandler());

        ToggleGroup blockCypherGroup = new ToggleGroup();
        ecbRadioButton.setToggleGroup(blockCypherGroup);
        ecbRadioButton.setUserData(BlockCiphers.newECBBlockCipher());
        cbcRadioButton.setToggleGroup(blockCypherGroup);
        cbcRadioButton.setUserData(BlockCiphers.newCBCBlockCipher());
        cbcRadioButton.setSelected(true);

        blockCypherGroup.selectedToggleProperty().addListener(new SelectBlockCypherEventHandler(blockCypherGroup));
    }
}
