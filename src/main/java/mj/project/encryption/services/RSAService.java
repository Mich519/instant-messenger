package mj.project.encryption.services;

import mj.project.encryption.encryptors.Encryptor;
import mj.project.encryption.encryptors.RSAEncryptor;
import mj.project.encryption.encryptors.RSAFileStreamHandler;
import mj.project.encryption.factories.RSAKeyPairFactory;

import javax.inject.Inject;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSAService {

    private final Encryptor rsaEncryptor;
    private final RSAKeyPairFactory rsaKeyPairFactory;
    private final RSAFileStreamHandler rsaFileStreamHandler;

    @Inject
    public RSAService(Encryptor rsaEncryptor,
                      RSAKeyPairFactory rsaKeyPairFactory,
                      RSAFileStreamHandler rsaFileStreamHandler) {
        this.rsaEncryptor = rsaEncryptor;
        this.rsaKeyPairFactory = rsaKeyPairFactory;
        this.rsaFileStreamHandler = rsaFileStreamHandler;
    }

    /*public RSAService() {
        this.rsaEncryptor = new RSAEncryptor();
        this.rsaKeyPairFactory = new RSAKeyPairFactory();
        this.rsaFileStreamHandler = new RSAFileStreamHandler();
    }*/

    public byte[] encrypt(byte[] input, PublicKey publicKey) {
        return rsaEncryptor.encrypt(input, publicKey.getEncoded());
    }

    public byte[] decrypt(byte[] input, PrivateKey privateKey) {
        return rsaEncryptor.decrypt(input, privateKey.getEncoded());
    }

    public KeyPair createKeyPair() {
        return rsaKeyPairFactory.create();
    }

    public KeyPair loadKeyPairFromFile(String privateKeyPath, String publicKeyPath, byte[] password) {
        return rsaFileStreamHandler.readKeyPairFromFile(privateKeyPath, publicKeyPath, password);
    }

    public void saveKeyPairToFile(KeyPair keyPair, String privateKeyPath, String publicKeyPath, byte[] password) {
        rsaFileStreamHandler.writeKeyPairToFile(keyPair, privateKeyPath, publicKeyPath, password);
    }
}
