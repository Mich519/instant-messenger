package mj.project.encryption;

import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;


public class LocalKeyService {

    public byte[] encrypt(byte[] plainText, byte[] password) {
        byte[] hashedPassword = DigestUtils.sha256(password);
        SecretKey localKey = new SecretKeySpec(hashedPassword, "AES");
        AESService aesService = new AESService();
        return aesService.encrypt(plainText, localKey);
    }

    public byte[] decrypt(byte[] encryptedText, byte[] password) {
        byte[] hashedPassword = DigestUtils.sha256(password);
        SecretKey localKey = new SecretKeySpec(hashedPassword, "AES");
        AESService aesService = new AESService();
        return aesService.decrypt(encryptedText, localKey);
    }
}
