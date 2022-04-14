package mj.project.gui.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import mj.project.networking.services.ServerSocketService;

import javax.inject.Inject;

public class StopEventHandler implements EventHandler<Event> {

    private final ServerSocketService serverSocketService;

    public StopEventHandler(ServerSocketService serverSocketService) {
        this.serverSocketService = serverSocketService;
    }

    @Override
    public void handle(Event event) {
        serverSocketService.stopListening();
    }
}
