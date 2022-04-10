package mj.project.gui.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import mj.project.configurations.AppConfig;
import mj.project.encryption.services.SessionKeyService;
import mj.project.gui.controllers.Controllers;
import mj.project.networking.ClientSocketService;
import mj.project.networking.message.Message;
import mj.project.networking.message.MessageType;

import javax.crypto.SecretKey;
import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;

public class SendSessionKeyEventHandler implements EventHandler<Event> {

    private final SessionKeyService sessionKeyService;

    @Inject
    public SendSessionKeyEventHandler(SessionKeyService sessionKeyService) {
        this.sessionKeyService = sessionKeyService;
    }

    @Override
    public void handle(Event event) {
        PublicKey recipientPublicKey = AppConfig.getInstance().getRecipientPublicKey();
        if(recipientPublicKey == null) {
            throw new RuntimeException("Recipient's key not exists");
        }
        SecretKey secretKey = sessionKeyService.createSessionKey();
        byte[] elo = secretKey.getEncoded();
        byte[] encryptedSessionKey = sessionKeyService.encryptSessionKey(secretKey, recipientPublicKey);

        Message sessionKeyMessage = Message
                .builder()
                .senderName(AppConfig.getInstance().getMyNickName().getBytes(StandardCharsets.US_ASCII))
                .content(encryptedSessionKey)
                .messageType(MessageType.SESSION_KEY)
                .build();

        Controllers.getMainViewController().addMessage(elo);
        ClientSocketService.getInstance().sendMessage(sessionKeyMessage);
    }
}
