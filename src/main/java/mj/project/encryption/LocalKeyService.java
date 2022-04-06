package mj.project.encryption;

import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class LocalKeyService {

    /**
     * Encrypt plain text using AES algorithm.
     * Provided user password is hashed using SHA256 algorithm and then used as an encryption key (local key) for AES algorithm.
     * @param plainText text to be encrypted
     * @param password user password
     * @return encrypted text
     */
    public byte[] encrypt(byte[] plainText, byte[] password) {
        byte[] hashedPassword = DigestUtils.sha256(password);
        SecretKey localKey = new SecretKeySpec(hashedPassword, "AES");
        AESService aesService = new AESService();
        return aesService.encrypt(plainText, localKey);
    }

    /**
     * Decrypt text using AES algorithm.
     * Provided user password is hashed using SHA256 algorithm and then used as a decryption key (local key) for AES algorithm.
     * @param encryptedText text to be decrypted
     * @param password user password
     * @return decrypted text
     */
    public byte[] decrypt(byte[] encryptedText, byte[] password) {
        byte[] hashedPassword = DigestUtils.sha256(password);
        SecretKey localKey = new SecretKeySpec(hashedPassword, "AES");
        AESService aesService = new AESService();
        return aesService.decrypt(encryptedText, localKey);
    }
}
