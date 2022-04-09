package mj.project.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import mj.project.networking.ClientSocketService;
import mj.project.networking.Message;
import mj.project.networking.MessageType;

import java.nio.charset.StandardCharsets;

public class SendMessageEventHandler implements EventHandler<Event> {

    private final TextArea textArea;
    private final Label statusLabel;

    public SendMessageEventHandler(TextArea textArea, Label statusLabel) {
        this.textArea = textArea;
        this.statusLabel = statusLabel;
    }

    @Override
    public void handle(Event event) {
        String messageContent = textArea.getText();
        Message message = new Message(messageContent.getBytes(StandardCharsets.UTF_8), MessageType.TEXT);
        ClientSocketService.getInstance().sendMessage(message);
    }
}
