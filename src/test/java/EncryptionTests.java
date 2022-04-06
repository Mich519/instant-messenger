import static org.junit.jupiter.api.Assertions.assertEquals;

import mj.project.encryption.RSAService;
import mj.project.encryption.SessionKeyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.*;
import java.security.*;


public class EncryptionTests {

    @Test
    void bytesEncryptionAndDecryptionWithRsa() {
        byte[] input = "some_text".getBytes();
        RSAService rsaService = new RSAService();
        KeyPair keyPair = rsaService.createKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        byte[] encoded = rsaService.encrypt(input, publicKey);
        byte[] decoded = rsaService.decrypt(encoded, privateKey);
        Assertions.assertArrayEquals(input, decoded);
    }

    @Test
    void saveAndLoadRsaKeyPair() {
        String publicKeyPath ="D:\\public_key";
        String privateKeyPath ="D:\\private_key";
        String userPassword = "some_password";
        RSAService rsaService = new RSAService();
        KeyPair keyPair = rsaService.createKeyPair();
        rsaService.saveKeyPairToFile(
                keyPair,
                publicKeyPath,
                privateKeyPath,
                userPassword.getBytes()
        );
        KeyPair loadedKeyPair = rsaService.loadKeyPairFromFile(
                publicKeyPath,
                privateKeyPath,
                userPassword.getBytes()
        );
        Assertions.assertArrayEquals(loadedKeyPair.getPrivate().getEncoded(), keyPair.getPrivate().getEncoded());
        Assertions.assertArrayEquals(loadedKeyPair.getPublic().getEncoded(), keyPair.getPublic().getEncoded());
    }

    @Test
    void sessionKeyEncryptionAndDecryptionWithRsa() {
        RSAService rsaService = new RSAService();
        KeyPair keyPair = rsaService.createKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        SessionKeyService sessionKeyService = new SessionKeyService();
        SecretKey sessionKey = sessionKeyService.createSessionKey();
        byte[] encryptedSessionKey = sessionKeyService.encryptSessionKey(sessionKey, publicKey);
        SecretKey decodedSessionKey = sessionKeyService.decodeSessionKey(encryptedSessionKey, privateKey);
        Assertions.assertArrayEquals(sessionKey.getEncoded(), decodedSessionKey.getEncoded());
    }
}

