package mj.project.encryption.encryptors;

public interface Encryptor {
    byte[] encrypt(byte[] input, byte[] keyBytes);
    byte[] decrypt(byte[] input, byte[] keyBytes);
}
