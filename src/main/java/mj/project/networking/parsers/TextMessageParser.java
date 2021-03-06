package mj.project.networking.parsers;

import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.SessionKeyService;
import mj.project.gui.controllers.MainViewController;
import mj.project.networking.message.MessageFactory;
import mj.project.networking.message.MessageType;
import mj.project.networking.message.Message;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class TextMessageParser implements MessageParser {

    private final SessionKeyService sessionKeyService;
    private final KeyStorage keyStorage;
    private final MessageFactory messageFactory;

    public TextMessageParser(SessionKeyService sessionKeyService, KeyStorage keyStorage, MessageFactory messageFactory) {
        this.sessionKeyService = sessionKeyService;
        this.keyStorage = keyStorage;
        this.messageFactory = messageFactory;
    }

    @Override
    public Message parse(Message message) {
        byte[] senderName = message.getSenderName();
        byte[] decryptedMessageBytes = sessionKeyService.decrypt(message.getContent().get(0), keyStorage.getSessionKey());
        MainViewController.addLog("Recieved message: " + new String(decryptedMessageBytes, StandardCharsets.UTF_8));
        String messageContent = new String(senderName) + ": " + new String(decryptedMessageBytes);
        MainViewController.addTextMessage(messageContent);
        return messageFactory.createMessage(List.of(message.getContent().get(0)), MessageType.TEXT);
    }
}
