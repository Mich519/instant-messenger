package mj.project.encryption.encryptors;

import mj.project.encryption.encryptors.RSAService;
import mj.project.encryption.encryptors.factories.SessionKeyFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

public class SessionKeyService {

    private final SessionKeyFactory sessionKeyFactory;

    public SessionKeyService() {
        this.sessionKeyFactory = new SessionKeyFactory();
    }

    public SecretKey createSessionKey() {
        byte[] randomBytes = new byte[32];
        SecureRandom random = new SecureRandom();
        random.nextBytes(randomBytes);
        return sessionKeyFactory.create(randomBytes);
    }

    public byte[] encryptSessionKey(SecretKey sessionKey, PublicKey publicKey) {
        RSAService rsaService = new RSAService();
        return rsaService.encrypt(sessionKey.getEncoded(), publicKey);
    }

    public SecretKey decodeSessionKey(byte[] encryptedSessionKey, PrivateKey privateKey) {
        RSAService rsaService = new RSAService();
        byte[] decodedSessionKey = rsaService.decrypt(encryptedSessionKey, privateKey);
        return new SecretKeySpec(decodedSessionKey, "AES");
    }
}
