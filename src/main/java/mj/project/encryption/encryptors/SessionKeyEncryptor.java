package mj.project.encryption.encryptors;

import javax.inject.Inject;

public class SessionKeyEncryptor implements Encryptor {

    private final Encryptor aesEncryptor;

    @Inject
    public SessionKeyEncryptor(AESEncryptor aesEncryptor) {
        this.aesEncryptor = aesEncryptor;
    }

    @Override
    public byte[] encrypt(byte[] input, byte[] sessionKeyBytes) {
        return aesEncryptor.encrypt(input, sessionKeyBytes);
    }

    @Override
    public byte[] decrypt(byte[] input, byte[] sessionKeyBytes) {
        return aesEncryptor.decrypt(input, sessionKeyBytes);
    }
}
