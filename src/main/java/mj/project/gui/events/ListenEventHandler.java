package mj.project.gui.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import lombok.AllArgsConstructor;
import mj.project.configurations.AppConfig;
import mj.project.networking.ServerSocketService;

@AllArgsConstructor
public class ListenEventHandler implements EventHandler<Event> {

    private final Label statusBar;

    @Override
    public void handle(Event event) {
        ServerSocketService.getInstance().startListening();
    }
}
