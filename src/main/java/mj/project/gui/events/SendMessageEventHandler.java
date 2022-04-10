package mj.project.gui.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import lombok.AllArgsConstructor;
import mj.project.configurations.AppConfig;
import mj.project.encryption.services.SessionKeyService;
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

        if(AppConfig.getInstance().getSessionKey() == null)
            throw new RuntimeException("Session key not exchanged");
        String messageContent = textArea.getText();
        SessionKeyService sessionKeyService = new SessionKeyService();
        byte[] messageContentEncoded = sessionKeyService.encrypt(messageContent.getBytes(StandardCharsets.UTF_8), AppConfig.getInstance().getSessionKey());
        Message message = Message.builder()
                .senderName(AppConfig.getInstance().getMyNickName().getBytes(StandardCharsets.UTF_8))
                .content(messageContentEncoded)
                .messageType(MessageType.TEXT)
                .build();

        ClientSocketService.getInstance().sendMessage(message);
    }
}
