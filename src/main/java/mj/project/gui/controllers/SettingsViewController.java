package mj.project.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import lombok.NoArgsConstructor;
import mj.project.configurations.AppConfig;
import mj.project.encryption.block_ciphers.BlockCipher;
import mj.project.gui.events.GenerateKeyPairEventHandler;
import mj.project.gui.events.SelectBlockCypherEventHandler;

import javax.inject.Inject;

@NoArgsConstructor
public class SettingsViewController {

    @FXML
    Button saveSettingsButton;

    @FXML
    RadioButton cbcRadioButton;

    @FXML
    RadioButton ecbRadioButton;

    @FXML
    Button generateKeyPair;

    GenerateKeyPairEventHandler generateKeyPairEventHandler;

    @Inject
    public SettingsViewController(GenerateKeyPairEventHandler generateKeyPairEventHandler) {
        this.generateKeyPairEventHandler = generateKeyPairEventHandler;
    }

    @FXML
    public void initialize() {
        Controllers.setSettingsViewController(this);
        //saveSettingsButton.setOnMouseClicked(new SaveSettingsEventHandler());

        ToggleGroup blockCypherGroup = new ToggleGroup();
        ecbRadioButton.setToggleGroup(blockCypherGroup);
        ecbRadioButton.setUserData(BlockCipher.ECB);
        cbcRadioButton.setToggleGroup(blockCypherGroup);
        cbcRadioButton.setUserData(BlockCipher.CBC);
        cbcRadioButton.setSelected(true);

        generateKeyPair.setOnMouseClicked(generateKeyPairEventHandler);

        //SelectBlockCypherEventHandler selectBlockCypherEventHandler = new SelectBlockCypherEventHandler(blockCypherGroup, appConfig);
        //blockCypherGroup.selectedToggleProperty().addListener(selectBlockCypherEventHandler);
    }
}
