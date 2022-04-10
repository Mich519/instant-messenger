package mj.project.gui.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import lombok.AllArgsConstructor;
import mj.project.configurations.AppConfig;
import mj.project.networking.ServerSocketService;

import javax.inject.Inject;

public class ListenEventHandler implements EventHandler<Event> {

    private final Label statusBar;
    private final ServerSocketService serverSocketService;

    @Inject
    public ListenEventHandler(Label statusBar, ServerSocketService serverSocketService) {
        this.statusBar = statusBar;
        this.serverSocketService = serverSocketService;
    }

    @Override
    public void handle(Event event) {
        serverSocketService.startListening();
    }
}
