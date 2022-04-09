package mj.project.encryption.encryptors.factories;

import mj.project.encryption.encryptors.factories.KeyFactory;
import mj.project.exceptions.PublicKeyCreateException;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class RSAPublicKeyFactory implements KeyFactory<PublicKey> {

    private static final String ALGORITHM = "RSA";

    @Override
    public PublicKey create(byte[] input) {
        try {
            EncodedKeySpec keySpec = new X509EncodedKeySpec(input);
            return java.security.KeyFactory.getInstance(ALGORITHM).generatePublic(keySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new PublicKeyCreateException();
    }
}
