package mj.project.encryption.factories;

import javax.inject.Inject;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class RSAKeyPairFactory {

    private static final String ALGORITHM = "RSA";
    private static final int KEY_SIZE = 1024;

    @Inject
    public RSAKeyPairFactory() {
    }

    public KeyPair create() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(KEY_SIZE);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
