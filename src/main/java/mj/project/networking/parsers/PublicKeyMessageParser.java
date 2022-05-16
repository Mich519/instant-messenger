package mj.project.networking.parsers;

import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.RSAService;
import mj.project.gui.controllers.MainViewController;
import mj.project.networking.message.MessageFactory;
import mj.project.networking.message.MessageType;
import mj.project.networking.message.Message;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;

public class PublicKeyMessageParser implements MessageParser {

    private final RSAService rsaService;
    private final KeyStorage keyStorage;
    private final MessageFactory messageFactory;

    public PublicKeyMessageParser(RSAService rsaService, KeyStorage keyStorage, MessageFactory messageFactory) {
        this.rsaService = rsaService;
        this.keyStorage = keyStorage;
        this.messageFactory = messageFactory;
    }
    @Override
    public Message parse(Message message) {
        PublicKey recipientPublicKey = rsaService.createPublicKeyFromBytes(message.getContent().get(0));
        keyStorage.setRecipientPublicKey(recipientPublicKey);
        MainViewController.addLog("received recipient pubkey: " + Arrays.toString(message.getContent().get(0)));
        return messageFactory.createMessage(List.of(keyStorage.getThisKeyPair().getPublic().getEncoded()), MessageType.PUBLIC_KEY);
    }
}
