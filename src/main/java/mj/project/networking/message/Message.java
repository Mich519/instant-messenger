package mj.project.networking.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@ToString
@Builder
public class Message implements Serializable {
    private byte[] content;
    private byte[] senderName;
    private MessageType messageType;
}
