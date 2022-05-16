package mj.project.networking.message;

import mj.project.networking.message.MessageFactory;
import mj.project.networking.message.Message;
import mj.project.networking.message.MessageType;

import javax.inject.Inject;
import java.util.List;

public class MessageService {

    private final MessageFactory messageFactory;
    @Inject
    public MessageService(MessageFactory messageFactory) {
        this.messageFactory = messageFactory;
    }

    public Message createMessage(List<byte[]> contentList, MessageType messageType) {
        return messageFactory.createMessage(contentList, messageType);
    }
}
