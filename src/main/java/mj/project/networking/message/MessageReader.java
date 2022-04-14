package mj.project.networking.message;

import javafx.application.Platform;
import mj.project.configurations.AppConfig;
import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.SessionKeyService;
import mj.project.gui.controllers.Controllers;
import mj.project.encryption.factories.RSAPublicKeyFactory;
import mj.project.networking.data.NetworkPropertiesStorage;

import javax.crypto.SecretKey;
import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;

public class MessageReader {

    private final SessionKeyService sessionKeyService;
    private final KeyStorage keyStorage;
    private final NetworkPropertiesStorage networkPropertiesStorage;

    @Inject
    public MessageReader(KeyStorage keyStorage, SessionKeyService sessionKeyService, NetworkPropertiesStorage networkPropertiesStorage) {
        this.sessionKeyService = sessionKeyService;
        this.keyStorage = keyStorage;
        this.networkPropertiesStorage = networkPropertiesStorage;
    }

    public void read(Message message) {
        MessageType messageType = message.getMessageType();

        /*if (messageType.equals(MessageType.PUBLIC_KEY)) {
            RSAPublicKeyFactory rsaPublicKeyFactory = new RSAPublicKeyFactory();
            PublicKey recipientPublicKey = rsaPublicKeyFactory.create(message.getContent());
            keyStorage.setRecipientPublicKey(recipientPublicKey);

        } else if (messageType.equals(MessageType.TEXT)) {
            Platform.runLater(() -> Controllers.getMainViewController().addMessage(message));
        } else if (messageType.equals(MessageType.SESSION_KEY)) {
            SecretKey decodedSessionKey = sessionKeyService.decodeSessionKey(message.getContent(), keyStorage.getThisKeyPair().getPrivate());
            Message m = Message.builder()
                    .senderName(networkPropertiesStorage.getMyNickName().getBytes(StandardCharsets.UTF_8))
                    .content(decodedSessionKey.getEncoded())
                    .build();
            Platform.runLater(() -> Controllers.getMainViewController().addMessage(m));
        }*/
    }
}
