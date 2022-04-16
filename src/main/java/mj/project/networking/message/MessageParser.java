package mj.project.networking.message;

import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.RSAService;
import mj.project.encryption.services.SessionKeyService;
import mj.project.gui.controllers.MainViewController;
import mj.project.networking.data.NetworkPropertiesStorage;

import javax.crypto.SecretKey;
import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.util.Arrays;

public class MessageParser {

    private final SessionKeyService sessionKeyService;
    private final KeyStorage keyStorage;
    private final NetworkPropertiesStorage networkPropertiesStorage;
    private final RSAService rsaService;

    @Inject
    public MessageParser(KeyStorage keyStorage, SessionKeyService sessionKeyService, NetworkPropertiesStorage networkPropertiesStorage, RSAService rsaService) {
        this.sessionKeyService = sessionKeyService;
        this.keyStorage = keyStorage;
        this.networkPropertiesStorage = networkPropertiesStorage;
        this.rsaService = rsaService;
    }

    public void parse(Message message) {
        MessageType messageType = message.getMessageType();

        if (messageType.equals(MessageType.PUBLIC_KEY)) {
            PublicKey recipientPublicKey = rsaService.createPublicKeyFromBytes(message.getContent());
            keyStorage.setRecipientPublicKey(recipientPublicKey);
        } else if (messageType.equals(MessageType.TEXT)) {
            byte[] decryptedMessageBytes = sessionKeyService.decrypt(message.getContent(), keyStorage.getSessionKey());
            MainViewController.addLog("Recieved message: " + new String(decryptedMessageBytes, StandardCharsets.UTF_8));
        } else if (messageType.equals(MessageType.SESSION_KEY)) {
            SecretKey decodedSessionKey = sessionKeyService.decodeSessionKey(message.getContent(), keyStorage.getThisKeyPair().getPrivate());
            keyStorage.setSessionKey(decodedSessionKey);
            MainViewController.addLog("Recieved session key: " + Arrays.toString(decodedSessionKey.getEncoded()));
        }
    }
}
