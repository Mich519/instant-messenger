package mj.project.gui.event_handlers;

import javafx.event.Event;
import javafx.event.EventHandler;
import mj.project.networking.sockets.ServerSocketService;

public class StopButtonEventHandler implements EventHandler<Event> {

    private final ServerSocketService serverSocketService;

    public StopButtonEventHandler(ServerSocketService serverSocketService) {
        this.serverSocketService = serverSocketService;
    }

    @Override
    public void handle(Event event) {
        serverSocketService.stopListening();
    }
}
