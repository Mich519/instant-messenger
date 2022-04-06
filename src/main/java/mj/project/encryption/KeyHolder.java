package mj.project.encryption;

import java.security.Key;
import java.security.KeyPair;
import java.security.PublicKey;

/**
 * This class is used for storing encryption keys in-memory
 */
public class KeyHolder {

    private KeyHolder() {

    }

    private static KeyPair thisRsaKeyPair;

    /**
     * Public key of the recipient
     */
    private static PublicKey recipientPublicKey;

    public static KeyPair getThisRsaKeyPair() {
        return thisRsaKeyPair;
    }

    public static PublicKey getOtherPublicKey() {
        return recipientPublicKey;
    }

    public static void setThisRsaKeyPair(KeyPair thisRsaKeyPair) {
        KeyHolder.thisRsaKeyPair = thisRsaKeyPair;
    }

    public static void setOtherPublicKey(PublicKey recipientPublicKey) {
        KeyHolder.recipientPublicKey = recipientPublicKey;
    }
}
