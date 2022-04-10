import static org.junit.jupiter.api.Assertions.assertEquals;

import mj.project.encryption.services.RSAService;
import mj.project.encryption.services.SessionKeyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.*;
import java.security.*;


public class EncryptionTests {

    void sessionKeyTest() {
        /*SessionKeyService sessionKeyService = new SessionKeyService();
        SecretKey s = sessionKeyService.createSessionKey();*/
    }

    void sessionKeyEncryptionAndDecryption() {
        /*RSAService rsaService = new RSAService();
        KeyPair keyPair = rsaService.createKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        SessionKeyService sessionKeyService = new SessionKeyService();
        SecretKey s = sessionKeyService.createSessionKey();
        byte[] sessionKeyBytes = s.getEncoded();
        byte[] sessionKeyBytesEncrypted = rsaService.encrypt(sessionKeyBytes, publicKey);
        byte[] sessionKeyBytesDecrypted = rsaService.decrypt(sessionKeyBytesEncrypted, privateKey);
        Assertions.assertArrayEquals(sessionKeyBytes, sessionKeyBytesDecrypted);*/
    }

}

