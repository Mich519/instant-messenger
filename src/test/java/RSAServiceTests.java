import static org.junit.jupiter.api.Assertions.assertEquals;

import mj.project.encryption.RSAService;
import mj.project.encryption.SessionKeyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.*;
import java.security.*;


public class RSAServiceTests {

    private final String publicKeyPath = "C:\\test_byczku\\public.txt";
    private final String privateKeyPath = "C:\\test_byczku\\private.txt";

    @Test
    void bytesEncryptionAndDecryptionWithRsaTest() {
        byte[] input = "hello".getBytes();
        RSAService rsaService = new RSAService();
        KeyPair keyPair = rsaService.createKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        byte[] encoded = rsaService.encode(input, privateKey);
        byte[] decoded = rsaService.decode(encoded, publicKey);
        Assertions.assertArrayEquals(input, decoded);
    }

    @Test
    void sessionKeyEncryptionAndDecryptionWithRsaTest() {
        RSAService rsaService = new RSAService();
        KeyPair keyPair = rsaService.createKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        SessionKeyService sessionKeyService = new SessionKeyService();
        SecretKey sessionKey = sessionKeyService.createSessionKey();
        byte[] encodedSessionKey = sessionKeyService.getEncoded(sessionKey, privateKey);
        SecretKey decodedSessionKey = sessionKeyService.getDecoded(encodedSessionKey, publicKey);
        Assertions.assertArrayEquals(sessionKey.getEncoded(), decodedSessionKey.getEncoded());
    }
}

