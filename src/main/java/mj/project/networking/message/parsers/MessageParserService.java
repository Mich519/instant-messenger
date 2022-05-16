package mj.project.networking.message.parsers;

import mj.project.networking.message.Message;

import javax.inject.Inject;
import java.util.Optional;

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
