package mj.project.encryption.encryptors.factories;

import mj.project.encryption.encryptors.factories.KeyFactory;
import mj.project.exceptions.PublicKeyCreateException;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class RSAPrivateKeyFactory implements KeyFactory<PrivateKey> {

    private static final String ALGORITHM = "RSA";

    @Override
    public PrivateKey create(byte[] input) {
        try {
            EncodedKeySpec keySpec = new X509EncodedKeySpec(input);
            return java.security.KeyFactory.getInstance(ALGORITHM).generatePrivate(keySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new PublicKeyCreateException();
    }
}