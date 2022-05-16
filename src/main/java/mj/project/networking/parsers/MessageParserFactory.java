package mj.project.networking.parsers;

import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.RSAService;
import mj.project.encryption.services.SessionKeyService;
import mj.project.exceptions.MessageParserNotFoundForGivenMessageType;
import mj.project.networking.message.MessageFactory;
import mj.project.networking.message.MessageType;

import javax.inject.Inject;

public class MessageParserFactory {

    private final SessionKeyService sessionKeyService;
    private final KeyStorage keyStorage;
    private final MessageFactory messageFactory;

    private final RSAService rsaService;

    @Inject
    public MessageParserFactory(SessionKeyService sessionKeyService, KeyStorage keyStorage, MessageFactory messageFactory, RSAService rsaService) {
        this.sessionKeyService = sessionKeyService;
        this.keyStorage = keyStorage;
        this.messageFactory = messageFactory;
        this.rsaService = rsaService;
    }

    public MessageParser createParser(MessageType messageType) {
        switch (messageType) {
            case TEXT: return new TextMessageParser(sessionKeyService, keyStorage, messageFactory);
            case FILE: return new FileMessageParser(sessionKeyService, keyStorage, messageFactory);
            case SESSION_KEY: return new SessionKeyMessageParser(sessionKeyService, keyStorage, messageFactory);
            case PUBLIC_KEY: return new PublicKeyMessageParser(rsaService, keyStorage, messageFactory);
            case EMPTY: return new EmptyMessageParser();
        }
        throw new MessageParserNotFoundForGivenMessageType();
    }
}
