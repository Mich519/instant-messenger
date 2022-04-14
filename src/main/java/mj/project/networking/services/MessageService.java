package mj.project.networking.services;

import mj.project.networking.factories.MessageFactory;
import mj.project.networking.message.Message;
import mj.project.networking.message.MessageType;

import javax.inject.Inject;

public class MessageService {

    private final MessageFactory messageFactory;

    @Inject
    public MessageService(MessageFactory messageFactory) {
        this.messageFactory = messageFactory;
    }

    public Message createMessage(byte[] textContent, MessageType messageType) {
        return messageFactory.createMessage(textContent, messageType);
    }
}
