package mj.project.networking.services;

import mj.project.networking.message.MessageFactory;
import mj.project.networking.message.content.Message;
import mj.project.networking.message.MessageParser;
import mj.project.networking.message.MessageType;

import javax.inject.Inject;
import java.util.List;

public class MessageService {

    private final MessageFactory messageFactory;
    private final MessageParser messageParser;

    @Inject
    public MessageService(MessageFactory messageFactory, MessageParser messageParser) {
        this.messageFactory = messageFactory;
        this.messageParser = messageParser;
    }

    public Message createMessage(List<byte[]> contentList, MessageType messageType) {
        return messageFactory.createMessage(contentList, messageType);
    }

    public void parseMessage(Message messageSimpleContent) {
        messageParser.parse(messageSimpleContent);
    }
}
