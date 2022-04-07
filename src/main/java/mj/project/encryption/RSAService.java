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

// todo: make every method static
public class RSAService {

    /**
     * Generate new RSA key pair.
     * @return RSA key pair
     */
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

    public PublicKey createPublicKeyFromBytes(byte[] bytes) {
        PublicKey publicKey = null;
        try {
            publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bytes));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    /**
     * Encrypt input using RSA algorithm.
     * @param input text to be encrypted
     * @param receiverPublicKey receiver's public key
     * @return encrypted text
     */
    public byte[] encrypt(byte[] input, PublicKey receiverPublicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, receiverPublicKey);
            return cipher.doFinal(input);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    /**
     * Decrypt input using RSA algorithm.
     * @param input text to be decrypted
     * @param receiverPrivateKey receiver's private key
     * @return decrypted text
     */
    public byte[] decrypt(byte[] input, PrivateKey receiverPrivateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, receiverPrivateKey);
            return cipher.doFinal(input);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    /**
     *
     * @param publicKeyPath path to the public key file
     * @param privateKeyPath path to the private key file
     * @param password user password used for decrypting key pair
     * @return RSA kay pair
     */
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

    /**
     * Save RSA key pair to a file.
     * @param keyPair key pair to be saved
     * @param publicKeyPath destination path for public key
     * @param privateKeyPath destination path for private key
     * @param password user password used for encrypting key pair
     */
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
