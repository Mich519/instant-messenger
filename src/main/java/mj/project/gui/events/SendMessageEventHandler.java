package mj.project.gui.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import lombok.AllArgsConstructor;
import mj.project.configurations.AppConfig;
import mj.project.networking.ClientSocketService;
import mj.project.networking.message.Message;
import mj.project.networking.message.MessageType;

import java.nio.charset.StandardCharsets;

@AllArgsConstructor
public class SendMessageEventHandler implements EventHandler<Event> {

    private final TextArea textArea;
    private final Label statusLabel;

    @Override
    public void handle(Event event) {
        String messageContent = textArea.getText();
        Message message = Message.builder()
                .senderName(AppConfig.getInstance().getMyNickName().getBytes(StandardCharsets.UTF_8))
                .content(messageContent.getBytes(StandardCharsets.UTF_8))
                .messageType(MessageType.TEXT)
                .build();

        ClientSocketService.getInstance().sendMessage(message);
    }
}
