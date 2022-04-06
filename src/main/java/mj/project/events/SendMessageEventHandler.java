package mj.project.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import mj.project.sockets.ClientSocketService;

public class SendMessageEventHandler implements EventHandler<Event> {

    private final TextArea textArea;
    private final Label statusLabel;

    public SendMessageEventHandler(TextArea textArea, Label statusLabel) {
        this.textArea = textArea;
        this.statusLabel = statusLabel;
    }

    @Override
    public void handle(Event event) {
    }
}
