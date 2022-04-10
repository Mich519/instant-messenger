package mj.project.encryption.encryptors;

public class SessionKeyEncryptor implements Encryptor {

    private final AESEncryptor aesEncryptor;

    public SessionKeyEncryptor() {
        this.aesEncryptor = new AESEncryptor();
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
