package mj.project.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.util.Map;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.data.KeyStorage_Factory;
import mj.project.encryption.encryptors.AESEncryptor_Factory;
import mj.project.encryption.encryptors.RSAEncryptor;
import mj.project.encryption.encryptors.RSAEncryptor_Factory;
import mj.project.encryption.encryptors.RSAFileIO;
import mj.project.encryption.encryptors.RSAFileIO_Factory;
import mj.project.encryption.encryptors.SessionKeyEncryptor;
import mj.project.encryption.encryptors.SessionKeyEncryptor_Factory;
import mj.project.encryption.factories.RSAKeyPairFactory_Factory;
import mj.project.encryption.factories.RSAPrivateKeyFactory_Factory;
import mj.project.encryption.factories.RSAPublicKeyFactory_Factory;
import mj.project.encryption.factories.SessionKeyFactory_Factory;
import mj.project.encryption.services.LocalKeyService_Factory;
import mj.project.encryption.services.RSAService;
import mj.project.encryption.services.RSAService_Factory;
import mj.project.encryption.services.SessionKeyService;
import mj.project.encryption.services.SessionKeyService_Factory;
import mj.project.gui.controllers.MainViewController;
import mj.project.networking.data.NetworkPropertiesStorage_Factory;
import mj.project.networking.factories.MessageFactory;
import mj.project.networking.factories.MessageFactory_Factory;
import mj.project.networking.message.MessageReader;
import mj.project.networking.message.MessageReader_Factory;
import mj.project.networking.services.ClientSocketService;
import mj.project.networking.services.ClientSocketService_Factory;
import mj.project.networking.services.MessageService;
import mj.project.networking.services.MessageService_Factory;
import mj.project.networking.services.ServerSocketListenTask;
import mj.project.networking.services.ServerSocketListenTask_Factory;
import mj.project.networking.services.ServerSocketService;
import mj.project.networking.services.ServerSocketService_Factory;
import mj.project.utils.io.ObjectFileIO;
import mj.project.utils.io.ObjectFileIO_Factory;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerDiContainer implements DiContainer {
  private final DaggerDiContainer diContainer = this;

  private Provider<KeyStorage> keyStorageProvider;

  private Provider<SessionKeyEncryptor> sessionKeyEncryptorProvider;

  private Provider<RSAEncryptor> rSAEncryptorProvider;

  private Provider<ObjectMapper> provideObjectMapperProvider;

  private Provider<ObjectFileIO> objectFileIOProvider;

  private Provider<RSAFileIO> rSAFileIOProvider;

  private Provider<RSAService> rSAServiceProvider;

  private Provider<SessionKeyService> sessionKeyServiceProvider;

  private Provider<MessageFactory> messageFactoryProvider;

  private Provider<MessageService> messageServiceProvider;

  private Provider<ClientSocketService> clientSocketServiceProvider;

  private Provider<MessageReader> messageReaderProvider;

  private Provider<ServerSocketListenTask> serverSocketListenTaskProvider;

  private Provider<ServerSocketService> serverSocketServiceProvider;

  private Provider<Object> provideMainViewControllerProvider;

  private DaggerDiContainer(ControllerModule controllerModuleParam) {

    initialize(controllerModuleParam);

  }

  public static Builder builder() {
    return new Builder();
  }

  public static DiContainer create() {
    return new Builder().build();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final ControllerModule controllerModuleParam) {
    this.keyStorageProvider = DoubleCheck.provider(KeyStorage_Factory.create());
    this.sessionKeyEncryptorProvider = SessionKeyEncryptor_Factory.create(AESEncryptor_Factory.create());
    this.rSAEncryptorProvider = RSAEncryptor_Factory.create(RSAPublicKeyFactory_Factory.create(), RSAPrivateKeyFactory_Factory.create());
    this.provideObjectMapperProvider = ControllerModule_ProvideObjectMapperFactory.create(controllerModuleParam);
    this.objectFileIOProvider = ObjectFileIO_Factory.create(provideObjectMapperProvider);
    this.rSAFileIOProvider = RSAFileIO_Factory.create(objectFileIOProvider, LocalKeyService_Factory.create(), RSAPrivateKeyFactory_Factory.create(), RSAPublicKeyFactory_Factory.create());
    this.rSAServiceProvider = RSAService_Factory.create(rSAEncryptorProvider, RSAKeyPairFactory_Factory.create(), rSAFileIOProvider);
    this.sessionKeyServiceProvider = SessionKeyService_Factory.create(SessionKeyFactory_Factory.create(), sessionKeyEncryptorProvider, rSAServiceProvider);
    this.messageFactoryProvider = MessageFactory_Factory.create(NetworkPropertiesStorage_Factory.create());
    this.messageServiceProvider = MessageService_Factory.create(messageFactoryProvider);
    this.clientSocketServiceProvider = DoubleCheck.provider(ClientSocketService_Factory.create());
    this.messageReaderProvider = MessageReader_Factory.create(keyStorageProvider, sessionKeyServiceProvider, NetworkPropertiesStorage_Factory.create());
    this.serverSocketListenTaskProvider = ServerSocketListenTask_Factory.create(messageReaderProvider);
    this.serverSocketServiceProvider = DoubleCheck.provider(ServerSocketService_Factory.create(serverSocketListenTaskProvider));
    this.provideMainViewControllerProvider = ControllerModule_ProvideMainViewControllerFactory.create(keyStorageProvider, NetworkPropertiesStorage_Factory.create(), sessionKeyServiceProvider, messageServiceProvider, clientSocketServiceProvider, serverSocketServiceProvider, rSAServiceProvider);
  }

  @Override
  public Map<Class<?>, Provider<Object>> getControllers() {
    return ImmutableMap.<Class<?>, Provider<Object>>of(MainViewController.class, provideMainViewControllerProvider);
  }

  public static final class Builder {
    private ControllerModule controllerModule;

    private Builder() {
    }

    public Builder controllerModule(ControllerModule controllerModule) {
      this.controllerModule = Preconditions.checkNotNull(controllerModule);
      return this;
    }

    public DiContainer build() {
      if (controllerModule == null) {
        this.controllerModule = new ControllerModule();
      }
      return new DaggerDiContainer(controllerModule);
    }
  }
}
