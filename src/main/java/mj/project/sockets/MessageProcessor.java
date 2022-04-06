package mj.project.sockets;


import mj.project.encryption.KeyHolder;
import mj.project.encryption.RSAService;

import java.nio.charset.StandardCharsets;
import java.security.PublicKey;

/**
 * This class is used for processing recipient's messages.
 */
public class MessageProcessor {

    public String process(String message) {
        if(KeyHolder.getOtherPublicKey()  != null) {
            // assume that message contains public key
            PublicKey recipientPublicKey = RSAService.createPublicKeyFromBytes(message.getBytes());
            KeyHolder.setOtherPublicKey(recipientPublicKey);
        }
        return null;
    }
}
