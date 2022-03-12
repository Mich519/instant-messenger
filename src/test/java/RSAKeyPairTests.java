import static org.junit.jupiter.api.Assertions.assertEquals;

import mj.project.encryption.LocalKey;
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

        LocalKey localKey = new LocalKey("hellothere");
        RSAKeyPair rsaKeyPair = new RSAKeyPair();
        rsaKeyPair.createKeyPair();

        RSAKeyPair ref = new RSAKeyPair(rsaKeyPair);

        Assertions.assertDoesNotThrow(() -> rsaKeyPair.saveKeyPairToFile(publicKeyPath, privateKeyPath, localKey));

        Assertions.assertDoesNotThrow(() -> rsaKeyPair.loadKeyPair(publicKeyPath, privateKeyPath, localKey));

        assertEquals(Arrays.toString(ref.getKeyPair().getPrivate().getEncoded()), Arrays.toString(rsaKeyPair.getKeyPair().getPrivate().getEncoded()));
        assertEquals(Arrays.toString(ref.getKeyPair().getPublic().getEncoded()), Arrays.toString(rsaKeyPair.getKeyPair().getPublic().getEncoded()));
    }

}

