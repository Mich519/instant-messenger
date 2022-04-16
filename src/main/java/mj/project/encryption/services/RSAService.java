package mj.project.encryption.services;

import mj.project.encryption.encryptors.Encryptor;
import mj.project.encryption.encryptors.RSAEncryptor;
import mj.project.encryption.encryptors.RSAFileIO;
import mj.project.encryption.factories.RSAKeyPairFactory;
import mj.project.encryption.factories.RSAPrivateKeyFactory;
import mj.project.encryption.factories.RSAPublicKeyFactory;

import javax.inject.Inject;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSAService {

    private final Encryptor rsaEncryptor;
    private final RSAKeyPairFactory rsaKeyPairFactory;
    private final RSAPublicKeyFactory rsaPublicKeyFactory;
    private final RSAFileIO rsaFileIO;

    @Inject
    public RSAService(RSAEncryptor rsaEncryptor,
                      RSAKeyPairFactory rsaKeyPairFactory,
                      RSAPrivateKeyFactory privateKeyFactory, RSAPublicKeyFactory rsaPublicKeyFactory, RSAFileIO rsaFileIO) {
        this.rsaEncryptor = rsaEncryptor;
        this.rsaKeyPairFactory = rsaKeyPairFactory;
        this.rsaPublicKeyFactory = rsaPublicKeyFactory;
        this.rsaFileIO = rsaFileIO;
    }

    public byte[] encrypt(byte[] input, PublicKey publicKey) {
        return rsaEncryptor.encrypt(input, publicKey.getEncoded());
    }

    public byte[] decrypt(byte[] input, PrivateKey privateKey) {
        return rsaEncryptor.decrypt(input, privateKey.getEncoded());
    }

    public KeyPair createKeyPair() {
        return rsaKeyPairFactory.create();
    }

    public PublicKey createPublicKeyFromBytes(byte[] privateKeyBytes) {
        return rsaPublicKeyFactory.create(privateKeyBytes);
    }

    public KeyPair loadKeyPairFromFile(String privateKeyPath, String publicKeyPath, byte[] password) {
        return rsaFileIO.readKeyPairFromFile(privateKeyPath, publicKeyPath, password);
    }

    public void saveKeyPairToFile(KeyPair keyPair, String privateKeyPath, String publicKeyPath, byte[] password) {
        rsaFileIO.writeKeyPairToFile(keyPair, privateKeyPath, publicKeyPath, password);
    }
}
