package mj.project.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import mj.project.configurations.AppConfig;
import mj.project.networking.ServerSocketService;

public class ListenEventHandler implements EventHandler<Event> {

    private final Label statusBar;

    public ListenEventHandler(Label statusBar) {
        this.statusBar = statusBar;
    }

    @Override
    public void handle(Event event) {
        ServerSocketService.getInstance().startListening(AppConfig.getInstance().getMyPort());
    }
}
