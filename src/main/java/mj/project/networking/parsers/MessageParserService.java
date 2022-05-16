package mj.project.networking.parsers;

import mj.project.networking.message.Message;

import javax.inject.Inject;

public class MessageParserService {

    private final MessageParserFactory messageParserFactory;

    @Inject
    public MessageParserService(MessageParserFactory messageParserFactory) {
        this.messageParserFactory = messageParserFactory;
    }

    public Message parseMessage(Message message) {
        MessageParser parser = messageParserFactory.createParser(message.getMessageType());
        return parser.parse(message);
    }
}
