package mj.project.encryption.services;

import mj.project.encryption.encryptors.SessionKeyEncryptor;
import mj.project.encryption.factories.SessionKeyFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import java.security.*;

public class SessionKeyService {

    private final SessionKeyFactory sessionKeyFactory;
    private final SessionKeyEncryptor sessionKeyEncryptor;
    private final RSAService rsaService;

    @Inject
    public SessionKeyService(SessionKeyFactory sessionKeyFactory,
                             SessionKeyEncryptor sessionKeyEncryptor,
                             RSAService rsaService) {
        this.sessionKeyFactory = sessionKeyFactory;
        this.sessionKeyEncryptor = sessionKeyEncryptor;
        this.rsaService = rsaService;
    }

    public SecretKey createSessionKey() {
        byte[] randomBytes = new byte[32];
        SecureRandom random = new SecureRandom();
        random.nextBytes(randomBytes);
        return sessionKeyFactory.create(randomBytes);
    }

    public byte[] encrypt(byte[] input, SecretKey sessionKey) {
        return sessionKeyEncryptor.encrypt(input, sessionKey.getEncoded());
    }

    public byte[] decrypt(byte[] input, SecretKey sessionKey) {
        return sessionKeyEncryptor.decrypt(input, sessionKey.getEncoded());
    }

    public byte[] encryptSessionKey(SecretKey sessionKey, PublicKey publicKey) {

        return rsaService.encrypt(sessionKey.getEncoded(), publicKey);
    }

    public SecretKey decodeSessionKey(byte[] encryptedSessionKey, PrivateKey privateKey) {
        byte[] decodedSessionKey = rsaService.decrypt(encryptedSessionKey, privateKey);
        return new SecretKeySpec(decodedSessionKey, "AES");
    }
}
