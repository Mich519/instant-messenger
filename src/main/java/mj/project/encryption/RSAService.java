package mj.project.encryption;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAService {

    public KeyPair createKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public byte[] encode(byte[] input, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return cipher.doFinal(input);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public byte[] decode(byte[] input, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return cipher.doFinal(input);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public KeyPair loadKeyPairFromFile(String publicKeyPath, String privateKeyPath, byte[] password) {
        try {
            LocalKeyService localKeyService = new LocalKeyService();
            byte[] publicKeyEncrypted = Files.readAllBytes(Paths.get(publicKeyPath));
            byte[] privateKeyEncrypted = Files.readAllBytes(Paths.get(privateKeyPath));
            byte[] publicKeyBytes = localKeyService.decrypt(publicKeyEncrypted, password);
            byte[] privateKeyBytes = localKeyService.decrypt(privateKeyEncrypted, password);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey publicKey = kf.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
            PrivateKey privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
            return new KeyPair(publicKey, privateKey);

        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public void saveKeyPairToFile(KeyPair keyPair, String publicKeyPath, String privateKeyPath, byte[] password) {
        LocalKeyService localKeyService = new LocalKeyService();

        try (FileOutputStream fos = new FileOutputStream(publicKeyPath)) {
            byte[] publicKeyEncrypted = localKeyService.encrypt(keyPair.getPublic().getEncoded(), password);
            fos.write(publicKeyEncrypted);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream fos = new FileOutputStream(privateKeyPath)) {
            byte[] privateKeyEncrypted = localKeyService.encrypt(keyPair.getPrivate().getEncoded(), password);
            fos.write(privateKeyEncrypted);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
