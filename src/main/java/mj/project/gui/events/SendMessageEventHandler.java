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

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;

public class SendMessageEventHandler implements EventHandler<Event> {

    private final TextArea textArea;
    private final SessionKeyService sessionKeyService;

    @Inject
    public SendMessageEventHandler(SessionKeyService sessionKeyService) {
        this.textArea = new TextArea();
        this.sessionKeyService = sessionKeyService;
    }

    @Override
    public void handle(Event event) {

        if(AppConfig.getInstance().getSessionKey() == null)
            throw new RuntimeException("Session key not exchanged");
        String messageContent = textArea.getText();
        byte[] messageContentEncoded = sessionKeyService.encrypt(messageContent.getBytes(StandardCharsets.UTF_8), AppConfig.getInstance().getSessionKey());
        Message message = Message.builder()
                .senderName(AppConfig.getInstance().getMyNickName().getBytes(StandardCharsets.UTF_8))
                .content(messageContentEncoded)
                .messageType(MessageType.TEXT)
                .build();

        ClientSocketService.getInstance().sendMessage(message);
    }
}
