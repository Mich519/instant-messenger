package mj.project.encryption;

import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class LocalKey {

    private SecretKey localKey;

    public LocalKey(SecretKey localKey) {
        this.localKey = localKey;
    }

    /**
     * creates local key based on hashed user password
     * @param password user password
     */
    public void create(String password) {
        byte[] hashedPassword = DigestUtils.sha256(password);
        this.localKey = new SecretKeySpec(hashedPassword, "AES");
    }

    public byte[] encrypt(byte[] plainText) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(Cipher.ENCRYPT_MODE, localKey);
        return cipher.doFinal(plainText);
    }

    public byte[] decrypt(byte[] encryptedText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(Cipher.DECRYPT_MODE, localKey);
        return cipher.doFinal(encryptedText);
    }
}
