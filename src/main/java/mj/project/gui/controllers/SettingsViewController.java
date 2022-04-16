package mj.project.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import lombok.NoArgsConstructor;
import mj.project.configurations.AppConfig;
import mj.project.encryption.block_ciphers.BlockCipher;
import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.LocalKeyService;
import mj.project.encryption.services.RSAService;

import javax.inject.Inject;
import java.security.KeyPair;

public class SettingsViewController {

    @FXML
    Button saveSettingsButton;

    @FXML
    RadioButton cbcRadioButton;

    @FXML
    RadioButton ecbRadioButton;

    @FXML
    TextField loadKeyPairPasswordTextField;

    @FXML
    TextField generateKeyPairPasswordTextField;

    @FXML
    Button loadKeyPairButton;

    @FXML
    Button generateKeyPairButton;

    private final KeyStorage keyStorage;
    private final LocalKeyService localKeyService;
    private final RSAService rsaService;

    @Inject
    public SettingsViewController(KeyStorage keyStorage,
                                  LocalKeyService localKeyService,
                                  RSAService rsaService) {
        this.keyStorage = keyStorage;
        this.localKeyService = localKeyService;
        this.rsaService = rsaService;
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

        generateKeyPairButton.setOnMouseClicked(event -> {
            byte[] localKey = generateKeyPairPasswordTextField.getText().getBytes();
            KeyPair keyPair = rsaService.createKeyPair();
            keyStorage.setThisKeyPair(keyPair);
            keyStorage.setLocalKey(localKey);
            rsaService.saveKeyPairToFile(keyPair, AppConfig.PRIVATE_KEY_FILE_PATH, AppConfig.PUBLIC_KEY_FILE_PATH, localKey);
        });

        loadKeyPairButton.setOnMouseClicked(event -> {
            byte[] localKey = loadKeyPairPasswordTextField.getText().getBytes();
            KeyPair keyPair = rsaService.loadKeyPairFromFile(AppConfig.PRIVATE_KEY_FILE_PATH, AppConfig.PUBLIC_KEY_FILE_PATH, localKey);
            keyStorage.setThisKeyPair(keyPair);
        });
    }
}
