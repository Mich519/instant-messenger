package mj.project.networking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@ToString
public class Message implements Serializable {
    private byte[] content;
    private MessageType messageType;
}
