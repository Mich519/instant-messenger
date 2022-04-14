package mj.project.encryption.encryptors;

import mj.project.encryption.factories.RSAPrivateKeyFactory;
import mj.project.encryption.factories.RSAPublicKeyFactory;
import mj.project.encryption.services.LocalKeyService;
import mj.project.utils.io.ObjectFileIO;

import javax.inject.Inject;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSAFileIO {

    private final ObjectFileIO objectFileIO;
    private final LocalKeyService localKeyService;
    private final RSAPrivateKeyFactory rsaPrivateKeyFactory;
    private final RSAPublicKeyFactory rsaPublicKeyFactory;

    @Inject
    public RSAFileIO(ObjectFileIO objectFileIO, LocalKeyService localKeyService, RSAPrivateKeyFactory rsaPrivateKeyFactory, RSAPublicKeyFactory rsaPublicKeyFactory) {
        this.objectFileIO = objectFileIO;
        this.localKeyService = localKeyService;
        this.rsaPrivateKeyFactory = rsaPrivateKeyFactory;
        this.rsaPublicKeyFactory = rsaPublicKeyFactory;
    }

    private void writeKey(byte[] keyBytes, String path, byte[] password) {
        byte[] keyBytesEncrypted = localKeyService.encrypt(keyBytes, password);
        objectFileIO.writeObject(keyBytesEncrypted, path);
    }

    private byte[] readKey(String path, byte[] password) {
        byte[] keyBytesEncrypted = objectFileIO.readObject(byte[].class, path);
        return localKeyService.decrypt(keyBytesEncrypted, password);
    }

    public KeyPair readKeyPairFromFile(String privateKeyPath, String publicKeyPath, byte[] password) {
        byte[] privateKeyBytes = readKey(privateKeyPath, password);
        byte[] publicKeyBytes = readKey(publicKeyPath, password);
        PrivateKey privateKey = rsaPrivateKeyFactory.create(privateKeyBytes);
        PublicKey publicKey = rsaPublicKeyFactory.create(publicKeyBytes);
        return new KeyPair(publicKey, privateKey);
    }

    public void writeKeyPairToFile(KeyPair keyPair, String privateKeyPath, String publicKeyPath, byte[] password) {
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        writeKey(privateKeyBytes, privateKeyPath, password);
        writeKey(publicKeyBytes, publicKeyPath, password);
    }
}
