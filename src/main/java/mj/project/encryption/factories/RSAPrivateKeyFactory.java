package mj.project.encryption.factories;

import mj.project.exceptions.PublicKeyCreateException;

import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class RSAPrivateKeyFactory implements KeyFactory<PrivateKey> {

    private static final String ALGORITHM = "RSA";

    @Inject
    public RSAPrivateKeyFactory() {
    }

    @Override
    public PrivateKey create(byte[] input) {
        try {
            EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(input);
            return java.security.KeyFactory.getInstance(ALGORITHM).generatePrivate(keySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new PublicKeyCreateException();
    }
}
