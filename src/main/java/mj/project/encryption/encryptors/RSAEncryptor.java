package mj.project.encryption.encryptors;

import mj.project.encryption.factories.KeyFactory;
import mj.project.encryption.factories.RSAPrivateKeyFactory;
import mj.project.encryption.factories.RSAPublicKeyFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

public class RSAEncryptor implements Encryptor {

    private static final String ALGORITHM = "RSA";
    private final KeyFactory<PublicKey> publicKeyFactory;
    private final KeyFactory<PrivateKey> privateKeyFactory;

    public RSAEncryptor() {
        this.publicKeyFactory = new RSAPublicKeyFactory();
        this.privateKeyFactory = new RSAPrivateKeyFactory();
    }

    private byte[] doOperation(byte[] input, Key key, int operationMode) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(operationMode, key);
            return cipher.doFinal(input);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();

    }

    @Override
    public byte[] encrypt(byte[] input, byte[] pubKeyBytes) {
        Key pubKey = publicKeyFactory.create(pubKeyBytes);
        return doOperation(input, pubKey, Cipher.ENCRYPT_MODE);
    }

    @Override
    public byte[] decrypt(byte[] input, byte[] privKeyBytes) {
        Key privKey = privateKeyFactory.create(privKeyBytes);
        return doOperation(input, privKey, Cipher.DECRYPT_MODE);
    }
}
