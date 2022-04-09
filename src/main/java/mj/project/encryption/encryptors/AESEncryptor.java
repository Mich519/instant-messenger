package mj.project.encryption.encryptors;

import mj.project.encryption.encryptors.Encryptor;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESEncryptor implements Encryptor {

    private static final String CIPHER_TYPE = "AES/CBC/PKCS5Padding";
    private static final String ALGORITHM = "AES";

    private byte[] doOperation(byte[] input, byte[] keyBytes, int operationMode) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_TYPE);
            byte[] ivByte = new byte[cipher.getBlockSize()];
            IvParameterSpec ivParams = new IvParameterSpec(ivByte);
            SecretKey key = new SecretKeySpec(keyBytes, ALGORITHM);
            cipher.init(operationMode, key, ivParams);
            return cipher.doFinal(input);
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException |
                IllegalBlockSizeException |
                BadPaddingException |
                InvalidKeyException |
                InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    @Override
    public byte[] encrypt(byte[] input, byte[] keyBytes) {
        return doOperation(input, keyBytes, Cipher.ENCRYPT_MODE);
    }

    @Override
    public byte[] decrypt(byte[] input, byte[] keyBytes) {
        return doOperation(input, keyBytes, Cipher.DECRYPT_MODE);
    }
}
