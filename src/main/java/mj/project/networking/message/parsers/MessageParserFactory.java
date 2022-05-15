package mj.project.networking.message.parsers;

import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.SessionKeyService;
import mj.project.exceptions.MessageParserNotFoundForGivenMessageType;
import mj.project.networking.message.MessageFactory;
import mj.project.networking.message.MessageType;

import javax.inject.Inject;

public class MessageParserFactory {

    private final SessionKeyService sessionKeyService;
    private final KeyStorage keyStorage;
    private final MessageFactory messageFactory;

    @Inject
    public MessageParserFactory(SessionKeyService sessionKeyService, KeyStorage keyStorage, MessageFactory messageFactory) {
        this.sessionKeyService = sessionKeyService;
        this.keyStorage = keyStorage;
        this.messageFactory = messageFactory;
    }

    public IMessageParser createParser(MessageType messageType) {
        switch (messageType) {
            case TEXT: return new TextIMessageParser(sessionKeyService, keyStorage, messageFactory);
            case FILE: return null;
            case EMPTY: return null;
            case SESSION_KEY: return null;
            case PUBLIC_KEY: return null;
            default: throw new MessageParserNotFoundForGivenMessageType();
        }
    }
}
