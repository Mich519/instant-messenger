package mj.project.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class RSAKeyPairFactory {

    public RSAKeyPairFactory() {}

    public KeyPair create() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);

        return keyGen.generateKeyPair();
    }
}
