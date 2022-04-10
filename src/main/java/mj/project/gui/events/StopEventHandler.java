package mj.project.gui.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import mj.project.networking.ServerSocketService;

public class StopEventHandler implements EventHandler<Event> {

    @Override
    public void handle(Event event) {
        ServerSocketService.getInstance().stopListening();
    }
}
