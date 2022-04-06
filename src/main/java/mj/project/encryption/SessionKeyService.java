package mj.project.encryption;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

public class SessionKeyService {

    /**
     * Create session key using pseudo-random generator
     * @return session key
     */
    public SecretKey createSessionKey() {
        byte[] randomBytes = new byte[32];
        SecureRandom random = new SecureRandom();
        random.nextBytes(randomBytes);
        return new SecretKeySpec(randomBytes, "AES");
    }

    /**
     * Encrypt session key using receiver's public key
     * @param sessionKey to be encrypted
     * @param publicKey receiver's public key
     * @return encrypted session key bytes
     */
    public byte[] encryptSessionKey(SecretKey sessionKey, PublicKey publicKey) {
        RSAService rsaService = new RSAService();
        return rsaService.encrypt(sessionKey.getEncoded(), publicKey);
    }

    /**
     * Decrypt session key using this private key
     * @param encryptedSessionKey encrypted session key bytes to be decrypted
     * @param privateKey this private key
     * @return decrypted session key
     */
    public SecretKey decodeSessionKey(byte[] encryptedSessionKey, PrivateKey privateKey) {
        RSAService rsaService = new RSAService();
        byte[] decodedSessionKey = rsaService.decrypt(encryptedSessionKey, privateKey);
        return new SecretKeySpec(decodedSessionKey, "AES");
    }

    public byte[] encodePacket(SecretKey sessionKey) {
        //todo
        return null;
    }
}
