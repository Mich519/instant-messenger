package mj.project.encryption.encryptors;

import mj.project.encryption.factories.RSAPrivateKeyFactory;
import mj.project.encryption.factories.RSAPublicKeyFactory;
import mj.project.encryption.services.LocalKeyService;
import mj.project.utils.io.ByteFileReader;
import mj.project.utils.io.ByteFileWriter;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSAFileStreamHandler {

    private final ByteFileReader byteFileReader;
    private final ByteFileWriter byteFileWriter;
    private final LocalKeyService localKeyService;
    private final RSAPrivateKeyFactory rsaPrivateKeyFactory;
    private final RSAPublicKeyFactory rsaPublicKeyFactory;

    public RSAFileStreamHandler() {
        this.byteFileReader = new ByteFileReader();
        this.byteFileWriter = new ByteFileWriter();
        this.localKeyService = new LocalKeyService();
        this.rsaPrivateKeyFactory = new RSAPrivateKeyFactory();
        this.rsaPublicKeyFactory = new RSAPublicKeyFactory();
    }

    private void writeKey(byte[] keyBytes, String path, byte[] password) {
        byte[] keyBytesEncrypted = localKeyService.encrypt(keyBytes, password);
        byteFileWriter.write(keyBytesEncrypted, path);
    }

    private byte[] readKey(String path, byte[] password) {
        byte[] keyBytesEncrypted = byteFileReader.read(path);
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
