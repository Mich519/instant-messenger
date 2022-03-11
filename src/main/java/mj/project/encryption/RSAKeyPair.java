package mj.project.encryption;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

public class RSAKeyPair {

    private KeyPair keyPair;

    public RSAKeyPair() {
        createKeyPair();
    }

    // copy constructor
    public RSAKeyPair(RSAKeyPair other) {
        this.keyPair = other.getKeyPair();
    }

    public void createKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            this.keyPair = keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void loadKeyPair(String publicKeyPath, String privateKeyPath) {
        try {
            byte[] publicKeyBytes = Files.readAllBytes(Paths.get(publicKeyPath));
            byte[] privateKeyBytes = Files.readAllBytes(Paths.get(privateKeyPath));
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey publicKey = kf.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
            PrivateKey privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
            this.keyPair = new KeyPair(publicKey, privateKey);

        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    public void saveKeyPairToFile(String publicKeyPath, String privateKeyPath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(publicKeyPath)) {
            fos.write(keyPair.getPublic().getEncoded());
        }
        try (FileOutputStream fos = new FileOutputStream(privateKeyPath)) {
            fos.write(keyPair.getPrivate().getEncoded());
        }
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RSAKeyPair that = (RSAKeyPair) o;
        return Objects.equals(keyPair, that.keyPair);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyPair);
    }
}
