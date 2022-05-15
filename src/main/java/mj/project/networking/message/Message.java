package mj.project.networking.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

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
