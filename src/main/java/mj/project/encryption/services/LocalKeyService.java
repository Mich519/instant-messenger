package mj.project.encryption.services;

import mj.project.encryption.encryptors.AESEncryptor;
import mj.project.encryption.encryptors.Encryptor;
import mj.project.encryption.hashers.Hasher;
import mj.project.encryption.hashers.SHA256Hasher;

import javax.inject.Inject;

public class LocalKeyService {

    private final Hasher passwordHasher;
    private final Encryptor encryptor;

    @Inject
    public LocalKeyService() {
        this.passwordHasher = new SHA256Hasher();
        this.encryptor = new AESEncryptor();
    }

    public byte[] encrypt(byte[] plainText, byte[] password) {
        byte[] hashedPassword = passwordHasher.hash(password);
        return encryptor.encrypt(plainText, hashedPassword);
    }

    public byte[] decrypt(byte[] encryptedText, byte[] password) {
        byte[] hashedPassword = passwordHasher.hash(password);
        return encryptor.decrypt(encryptedText, hashedPassword);
    }
}
