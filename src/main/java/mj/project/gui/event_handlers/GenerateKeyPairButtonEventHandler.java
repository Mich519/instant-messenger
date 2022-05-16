package mj.project.gui.event_handlers;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import mj.project.configurations.AppConfig;
import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.RSAService;
import mj.project.gui.controllers.MainViewController;

import java.security.KeyPair;

public class GenerateKeyPairButtonEventHandler implements EventHandler<Event> {

    private final TextField generateKeyPairPasswordTextField;
    private final RSAService rsaService;
    private final KeyStorage keyStorage;

    public GenerateKeyPairButtonEventHandler(TextField generateKeyPairPasswordTextField, RSAService rsaService, KeyStorage keyStorage) {
        this.generateKeyPairPasswordTextField = generateKeyPairPasswordTextField;
        this.rsaService = rsaService;
        this.keyStorage = keyStorage;
    }

    @Override
    public void handle(Event event) {
        byte[] localKey = generateKeyPairPasswordTextField.getText().getBytes();
        KeyPair keyPair = rsaService.createKeyPair();
        keyStorage.setThisKeyPair(keyPair);
        keyStorage.setLocalKey(localKey);
        rsaService.saveKeyPairToFile(keyPair, AppConfig.PRIVATE_KEY_FILE_PATH, AppConfig.PUBLIC_KEY_FILE_PATH, localKey);
        MainViewController.addLog("Key pair generated");
    }
}
