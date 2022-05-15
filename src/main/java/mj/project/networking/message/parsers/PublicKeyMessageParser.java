package mj.project.networking.message.parsers;

import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.RSAService;
import mj.project.networking.message.MessageFactory;
import mj.project.networking.message.MessageType;
import mj.project.networking.message.Message;

import javax.inject.Inject;
import java.security.PublicKey;
import java.util.List;

public class PublicKeyMessageParser implements MessageParser {

    private final RSAService rsaService;
    private final KeyStorage keyStorage;
    private MessageFactory messageFactory;

    public PublicKeyMessageParser(RSAService rsaService, KeyStorage keyStorage, MessageFactory messageFactory) {
        this.rsaService = rsaService;
        this.keyStorage = keyStorage;
        this.messageFactory = messageFactory;
    }
    @Override
    public Message parse(Message message) {
        PublicKey recipientPublicKey = rsaService.createPublicKeyFromBytes(message.getContent().get(0));
        keyStorage.setRecipientPublicKey(recipientPublicKey);
        return messageFactory.createMessage(List.of(), MessageType.EMPTY);
    }
}
