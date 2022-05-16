package mj.project.gui.event_handlers;

import javafx.event.Event;
import javafx.event.EventHandler;
import mj.project.encryption.data.KeyStorage;
import mj.project.exceptions.KeyPairNotFound;
import mj.project.gui.controllers.MainViewController;
import mj.project.networking.sockets.ServerSocketService;

public class ListenButtonEventHandler implements EventHandler<Event> {

    private final KeyStorage keyStorage;
    private final ServerSocketService serverSocketService;

    public ListenButtonEventHandler(KeyStorage keyStorage, ServerSocketService serverSocketService) {
        this.keyStorage = keyStorage;
        this.serverSocketService = serverSocketService;
    }

    @Override
    public void handle(Event event) {
        if (keyStorage.getThisKeyPair() == null) {
            MainViewController.addLog("Your key pair not found. Please Generate new key pair.");
            throw new KeyPairNotFound();
        }
        serverSocketService.startListening();

    }
}
