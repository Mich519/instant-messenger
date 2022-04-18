package mj.project.gui.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import mj.project.configurations.AppConfig;
import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.SessionKeyService;
import mj.project.networking.services.ClientSocketService;
import mj.project.networking.message.content.Message;
import mj.project.networking.message.MessageType;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;

public class SendSessionKeyEventHandler implements EventHandler<Event> {

    private final SessionKeyService sessionKeyService;
    private final AppConfig appConfig;
    private final KeyStorage keyStorage;
    private final ClientSocketService clientSocketService;

    public SendSessionKeyEventHandler(SessionKeyService sessionKeyService, AppConfig appConfig, KeyStorage keyStorage, ClientSocketService clientSocketService) {
        this.sessionKeyService = sessionKeyService;
        this.appConfig = appConfig;
        this.keyStorage = keyStorage;
        this.clientSocketService = clientSocketService;
    }

    @Override
    public void handle(Event event) {
       /* PublicKey recipientPublicKey = keyStorage.getRecipientPublicKey();
        if(recipientPublicKey == null) {
            throw new RuntimeException("Recipient's key not exists");
        }
        SecretKey secretKey = sessionKeyService.createSessionKey();
        byte[] elo = secretKey.getEncoded();
        byte[] encryptedSessionKey = sessionKeyService.encryptSessionKey(secretKey, recipientPublicKey);

        Message sessionKeyMessageSimpleContent = Message
                .builder()
                .senderName("hello".getBytes(StandardCharsets.US_ASCII))
                .content(encryptedSessionKey)
                .messageType(MessageType.SESSION_KEY)
                .build();

        //Controllers.getMainViewController().addMessage(elo);
        clientSocketService.sendMessage(sessionKeyMessageSimpleContent);*/
    }
}
