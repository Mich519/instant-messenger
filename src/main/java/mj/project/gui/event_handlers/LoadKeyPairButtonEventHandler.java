package mj.project.gui.event_handlers;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import mj.project.configurations.AppConfig;
import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.RSAService;
import mj.project.gui.controllers.MainViewController;

import java.security.KeyPair;

public class LoadKeyPairButtonEventHandler implements EventHandler<Event> {

    private final TextField loadKeyPairPasswordTextField;
    private final RSAService rsaService;
    private final KeyStorage keyStorage;

    public LoadKeyPairButtonEventHandler(TextField loadKeyPairPasswordTextField, RSAService rsaService, KeyStorage keyStorage) {
        this.loadKeyPairPasswordTextField = loadKeyPairPasswordTextField;
        this.rsaService = rsaService;
        this.keyStorage = keyStorage;
    }

    @Override
    public void handle(Event event) {
        byte[] localKey = loadKeyPairPasswordTextField.getText().getBytes();
        KeyPair keyPair = rsaService.loadKeyPairFromFile(AppConfig.PRIVATE_KEY_FILE_PATH, AppConfig.PUBLIC_KEY_FILE_PATH, localKey);
        keyStorage.setThisKeyPair(keyPair);
        MainViewController.addLog("Key pair loaded");
    }
}
