package mj.project.networking.message;

import javafx.application.Platform;
import mj.project.configurations.AppConfig;
import mj.project.encryption.services.SessionKeyService;
import mj.project.gui.controllers.Controllers;
import mj.project.encryption.factories.RSAPublicKeyFactory;

import javax.crypto.SecretKey;
import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;

public class MessageReader {

    private final SessionKeyService sessionKeyService;

    @Inject
    public MessageReader(SessionKeyService sessionKeyService) {
        this.sessionKeyService = sessionKeyService;
    }

    public void read(Message message) {
        MessageType messageType = message.getMessageType();

        if (messageType.equals(MessageType.PUBLIC_KEY)) {
            RSAPublicKeyFactory rsaPublicKeyFactory = new RSAPublicKeyFactory();
            PublicKey recipientPublicKey = rsaPublicKeyFactory.create(message.getContent());
            AppConfig.getInstance().setRecipientPublicKey(recipientPublicKey);

        } else if (messageType.equals(MessageType.TEXT)) {
            Platform.runLater(() -> Controllers.getMainViewController().addMessage(message));
        } else if (messageType.equals(MessageType.SESSION_KEY)) {
            SecretKey decodedSessionKey = sessionKeyService.decodeSessionKey(message.getContent(), AppConfig.getInstance().getThisKeyPair().getPrivate());
            Message m = Message.builder()
                    .senderName(AppConfig.getInstance().getMyNickName().getBytes(StandardCharsets.UTF_8))
                    .content(decodedSessionKey.getEncoded())
                    .build();
            Platform.runLater(() -> Controllers.getMainViewController().addMessage(m));
        }
    }
}
