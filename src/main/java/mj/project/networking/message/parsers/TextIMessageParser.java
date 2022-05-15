package mj.project.networking.message.parsers;

import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.SessionKeyService;
import mj.project.gui.controllers.MainViewController;
import mj.project.networking.message.MessageFactory;
import mj.project.networking.message.MessageType;
import mj.project.networking.message.content.Message;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TextIMessageParser implements IMessageParser {

    private final SessionKeyService sessionKeyService;
    private final KeyStorage keyStorage;
    private final MessageFactory messageFactory;

    public TextIMessageParser(SessionKeyService sessionKeyService, KeyStorage keyStorage, MessageFactory messageFactory) {
        this.sessionKeyService = sessionKeyService;
        this.keyStorage = keyStorage;
        this.messageFactory = messageFactory;
    }

    @Override
    public Message parse(Message message) {
        byte[] senderName = message.getSenderName();
        byte[] decryptedMessageBytes = sessionKeyService.decrypt(message.getContent().get(0), keyStorage.getSessionKey());
        MainViewController.addLog("Recieved message: " + new String(decryptedMessageBytes, StandardCharsets.UTF_8));
        MainViewController.addTextMessage(decryptedMessageBytes, senderName);
        return messageFactory.createMessage(List.of(), MessageType.EMPTY);
    }
}
