package mj.project.networking.message.content;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import mj.project.networking.message.MessageType;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@Builder
public class Message implements Serializable {
    private List<byte[]> content;
    private byte[] senderName;
    private MessageType messageType;
}
