package mj.project.networking.message;

import mj.project.networking.data.NetworkPropertiesStorage;
import mj.project.networking.message.Message;
import mj.project.networking.message.MessageType;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;

public class MessageFactory {

    private final NetworkPropertiesStorage networkPropertiesStorage;

    @Inject
    public MessageFactory(NetworkPropertiesStorage networkPropertiesStorage) {
        this.networkPropertiesStorage = networkPropertiesStorage;
    }

    public Message createMessage(byte[] content, MessageType messageType) {
        return Message.builder()
                .messageType(messageType)
                .senderName(networkPropertiesStorage.getMyNickName().getBytes(StandardCharsets.UTF_8))
                .content(content)
                .build();
    }
}
