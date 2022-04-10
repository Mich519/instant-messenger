package mj.project.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mj.project.encryption.encryptors.Encryptor;
import mj.project.encryption.encryptors.RSAEncryptor;
import mj.project.encryption.encryptors.RSAFileStreamHandler;
import mj.project.encryption.encryptors.SessionKeyEncryptor;
import mj.project.encryption.factories.RSAKeyPairFactory;
import mj.project.encryption.factories.RSAPrivateKeyFactory;
import mj.project.encryption.factories.RSAPublicKeyFactory;
import mj.project.encryption.factories.SessionKeyFactory;
import mj.project.utils.io.ByteFileReader;
import mj.project.utils.io.ByteFileWriter;

import javax.inject.Singleton;

@Module
public class AppModule {

    @Provides
    @Singleton
    public SessionKeyFactory provideSessionKeyFactory() {
        return new SessionKeyFactory();
    }

    @Provides
    @Singleton
    public SessionKeyEncryptor provideSessionKeyEncryptor() {
        return new SessionKeyEncryptor();
    }

    @Provides
    @Singleton
    public Encryptor provideRSAEncryptor() {
        return new RSAEncryptor();
    }

    @Provides
    @Singleton
    public RSAKeyPairFactory provideRSAKeyPairFactory() {
        return new RSAKeyPairFactory();
    }

    @Provides
    @Singleton
    public ByteFileReader provideByteFileReader() {
        return new ByteFileReader();
    }

    @Provides
    @Singleton
    public ByteFileWriter provideByteFileWriter() {
        return new ByteFileWriter();
    }

    @Provides
    @Singleton
    public RSAPublicKeyFactory provideRSAPublicKeyFactory() {
        return new RSAPublicKeyFactory();
    }

    @Provides
    @Singleton
    public RSAPrivateKeyFactory provideRSAPrivateKeyFactory() {
        return new RSAPrivateKeyFactory();
    }

    @Provides
    public TextField provideTextField() {
        return new TextField();
    }
    @Provides
    public Label provideLabel() {
        return new Label();
    }

    @Provides
    @Singleton
    public ObjectMapper provideObjectMapper() {
        return new ObjectMapper();
    }
}
