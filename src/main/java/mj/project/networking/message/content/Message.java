package mj.project.networking.message.content;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import mj.project.networking.message.MessageType;
import mj.project.networking.message.parsers.IMessageParser;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@Builder
public class Message implements Serializable {
    private final List<byte[]> content;
    private final byte[] senderName;
    private final MessageType messageType;
}
