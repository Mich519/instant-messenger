package mj.project.networking.services;

import mj.project.networking.message.MessageFactory;
import mj.project.networking.message.Message;
import mj.project.networking.message.MessageParser;
import mj.project.networking.message.MessageType;

import javax.inject.Inject;

public class MessageService {

    private final MessageFactory messageFactory;
    private final MessageParser messageParser;

    @Inject
    public MessageService(MessageFactory messageFactory, MessageParser messageParser) {
        this.messageFactory = messageFactory;
        this.messageParser = messageParser;
    }

    public Message createMessage(byte[] textContent, MessageType messageType) {
        return messageFactory.createMessage(textContent, messageType);
    }

    public void parseMessage(Message message) {
        messageParser.parse(message);
    }
}
