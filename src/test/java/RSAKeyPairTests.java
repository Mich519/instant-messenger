import static org.junit.jupiter.api.Assertions.assertEquals;

import mj.project.encryption.RSAKeyPair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.KeyPair;
import java.util.Arrays;


public class RSAKeyPairTests {

    private final String publicKeyPath = "C:\\test_byczku\\public.txt";
    private final String privateKeyPath = "C:\\test_byczku\\private.txt";

    @Test
    void readAndWriteKeysToFileNotThrow() {

        RSAKeyPair rsaKeyPair = new RSAKeyPair();
        rsaKeyPair.createKeyPair();

        RSAKeyPair ref = new RSAKeyPair(rsaKeyPair);

        Assertions.assertDoesNotThrow(() -> {
            rsaKeyPair.saveKeyPairToFile(publicKeyPath, privateKeyPath);
        });

        Assertions.assertDoesNotThrow(() -> {
            rsaKeyPair.loadKeyPair(publicKeyPath, privateKeyPath);
        });

        Assertions.assertTrue(Arrays.toString(ref.getKeyPair().getPrivate().getEncoded()).equals(Arrays.toString(rsaKeyPair.getKeyPair().getPrivate().getEncoded())));
        Assertions.assertTrue(Arrays.toString(ref.getKeyPair().getPublic().getEncoded()).equals(Arrays.toString(rsaKeyPair.getKeyPair().getPublic().getEncoded())));
    }

}

