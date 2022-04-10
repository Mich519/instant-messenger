package mj.project.networking.message;

import lombok.Getter;

/**
 * This enum is used for identifying packets.
 * Each packet that is sent has attached prefix
 * that specifies its type
 */
public enum MessageType {
    TEXT("-TEXT-"),
    FILE("-FILE-"),
    PUBLIC_KEY("-PUBK-"),
    SESSION_KEY("-SESS-");

    public static final int PREFIX_SIZE = 5;

    @Getter
    private final String packetPrefix;

    MessageType(String packetPrefix) {
        this.packetPrefix = packetPrefix;
    }
}
