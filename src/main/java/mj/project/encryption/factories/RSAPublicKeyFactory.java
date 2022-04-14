package mj.project.encryption.factories;

import mj.project.exceptions.PublicKeyCreateException;

import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.*;

public class RSAPublicKeyFactory implements KeyFactory<PublicKey> {

    private static final String ALGORITHM = "RSA";

    @Inject
    public RSAPublicKeyFactory() {
    }

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
