package mj.project.networking.message.parsers;

import mj.project.networking.message.content.Message;

import javax.inject.Inject;

public class MessageParserService {

    private final MessageParserFactory messageParserFactory;

    @Inject
    public MessageParserService(MessageParserFactory messageParserFactory) {
        this.messageParserFactory = messageParserFactory;
    }

    public Message parseMessage(Message message) {
        IMessageParser parser = messageParserFactory.createParser(message.getMessageType());
        return parser.parse(message);
    }
}
