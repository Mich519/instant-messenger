package mj.project.encryption;

import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class LocalKey {

    private SecretKey localKey;
    private IvParameterSpec iv;

    public LocalKey(String password) {
        create(password);
    }

    /**
     * creates local key based on hashed user password
     */
    public void create(String password) {
        byte[] hashedPassword = DigestUtils.sha256(password);
        this.localKey = new SecretKeySpec(hashedPassword, "AES");
    }

    public byte[] encrypt(byte[] plainText) {
        Cipher cipher;
        byte[] encryptedText = null;
        try {

            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] iv = new byte[cipher.getBlockSize()];
            IvParameterSpec ivParams = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, localKey, ivParams);
            encryptedText = cipher.doFinal(plainText);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return encryptedText;
    }

    public byte[] decrypt(byte[] encryptedText) {
        Cipher cipher;
        byte[] decryptedText = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] ivByte = new byte[cipher.getBlockSize()];
            IvParameterSpec ivParams = new IvParameterSpec(ivByte);
            cipher.init(Cipher.DECRYPT_MODE, localKey, ivParams);
            decryptedText = cipher.doFinal(encryptedText);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return decryptedText;
    }
}
