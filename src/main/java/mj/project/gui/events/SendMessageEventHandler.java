package mj.project.gui.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import mj.project.configurations.AppConfig;
import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.SessionKeyService;
import mj.project.networking.data.NetworkPropertiesStorage;
import mj.project.networking.services.ClientSocketService;

public class SendMessageEventHandler implements EventHandler<Event> {

    private final SessionKeyService sessionKeyService;
    private final TextArea textArea;
    private final AppConfig appConfig;
    private final KeyStorage keyStorage;
    private final NetworkPropertiesStorage networkPropertiesStorage;
    private final ClientSocketService clientSocketService;

    public SendMessageEventHandler(SessionKeyService sessionKeyService, TextArea textArea, AppConfig appConfig, KeyStorage keyStorage, NetworkPropertiesStorage networkPropertiesStorage, ClientSocketService clientSocketService) {
        this.textArea = textArea;
        this.sessionKeyService = sessionKeyService;
        this.appConfig = appConfig;
        this.keyStorage = keyStorage;
        this.networkPropertiesStorage = networkPropertiesStorage;
        this.clientSocketService = clientSocketService;
    }

    @Override
    public void handle(Event event) {

       /* if(keyStorage.getSessionKey() == null)
            throw new RuntimeException("Session key not exchanged");
        String messageContent = textArea.getText();
        byte[] messageContentEncoded = sessionKeyService.encrypt(messageContent.getBytes(StandardCharsets.UTF_8), keyStorage.getSessionKey());
        Message messageSimpleContent = Message.builder()
                .senderName(networkPropertiesStorage.getMyNickName().getBytes(StandardCharsets.UTF_8))
                .content(messageContentEncoded)
                .messageType(MessageType.TEXT)
                .build();

        clientSocketService.sendMessage(messageSimpleContent);*/
    }
}
