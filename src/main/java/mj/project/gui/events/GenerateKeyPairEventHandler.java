package mj.project.gui.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.RSAService;
import mj.project.gui.controllers.utils.ControllerUtils;

import javax.inject.Inject;
import java.security.KeyPair;

public class GenerateKeyPairEventHandler implements EventHandler<Event> {

    private final RSAService rsaService;
    private final KeyStorage keyStorage;
    private final Pane pane;

    public GenerateKeyPairEventHandler(RSAService rsaService, KeyStorage keyStorage, Pane pane) {
        this.rsaService = rsaService;
        this.keyStorage = keyStorage;
        this.pane = pane;
    }

    @Override
    public void handle(Event event) {
        KeyPair newKeyPair = rsaService.createKeyPair();
        keyStorage.setThisKeyPair(newKeyPair);
        ControllerUtils.addLabelToPane("New key pair have been created successfully!", pane);
    }
}
