package mj.project.networking.parsers;

import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.SessionKeyService;
import mj.project.gui.controllers.MainViewController;
import mj.project.networking.message.MessageFactory;
import mj.project.networking.message.MessageType;
import mj.project.networking.message.Message;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.List;

public class SessionKeyMessageParser implements MessageParser {
    private final SessionKeyService sessionKeyService;
    private final KeyStorage keyStorage;
    private final MessageFactory messageFactory;

    public SessionKeyMessageParser(SessionKeyService sessionKeyService, KeyStorage keyStorage, MessageFactory messageFactory) {
        this.sessionKeyService = sessionKeyService;
        this.keyStorage = keyStorage;
        this.messageFactory = messageFactory;
    }

    @Override
    public Message parse(Message message) {
        SecretKey decodedSessionKey = sessionKeyService.decodeSessionKey(message.getContent().get(0), keyStorage.getThisKeyPair().getPrivate());
        keyStorage.setSessionKey(decodedSessionKey);
        MainViewController.addLog("Recieved session key: " + Arrays.toString(decodedSessionKey.getEncoded()));
        return messageFactory.createMessage(List.of(), MessageType.EMPTY);
    }
}
