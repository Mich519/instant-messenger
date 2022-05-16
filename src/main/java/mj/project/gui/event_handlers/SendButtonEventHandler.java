package mj.project.gui.event_handlers;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.SessionKeyService;
import mj.project.networking.message.Message;
import mj.project.networking.message.MessageService;
import mj.project.networking.message.MessageType;
import mj.project.networking.sockets.ClientSocketService;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class SendButtonEventHandler implements EventHandler<Event> {


    private final SessionKeyService sessionKeyService;
    private final KeyStorage keyStorage;
    private final MessageService messageService;
    private final ClientSocketService clientSocketService;
    private final TextArea textArea;

    public SendButtonEventHandler(TextArea textArea , SessionKeyService sessionKeyService, KeyStorage keyStorage, MessageService messageService, ClientSocketService clientSocketService) {
        this.textArea = textArea;
        this.sessionKeyService = sessionKeyService;
        this.keyStorage = keyStorage;
        this.messageService = messageService;
        this.clientSocketService = clientSocketService;
    }

    @Override
    public void handle(Event event) {
        String messageContent = textArea.getText();
        byte[] messageContentEncoded = sessionKeyService.encrypt(messageContent.getBytes(StandardCharsets.UTF_8), keyStorage.getSessionKey());
        Message messageSimpleContent = messageService.createMessage(List.of(messageContentEncoded), MessageType.TEXT);
        clientSocketService.sendMessage(messageSimpleContent);
    }
}
