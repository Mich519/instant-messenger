package mj.project.networking.message;

import mj.project.networking.data.NetworkPropertiesStorage;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MessageFactory {

    private final NetworkPropertiesStorage networkPropertiesStorage;

    @Inject
    public MessageFactory(NetworkPropertiesStorage networkPropertiesStorage) {
        this.networkPropertiesStorage = networkPropertiesStorage;
    }

    public Message createMessage(List<byte[]> contentList, MessageType messageType) {
        return Message.builder()
                .messageType(messageType)
                .senderName(networkPropertiesStorage.getMyNickName().getBytes(StandardCharsets.UTF_8))
                .content(contentList)
                .build();
    }
}
