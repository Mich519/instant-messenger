package mj.project.modules;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.RSAService;
import mj.project.encryption.services.SessionKeyService;
import mj.project.networking.data.NetworkPropertiesStorage;
import mj.project.networking.services.ClientSocketService;
import mj.project.networking.services.MessageService;
import mj.project.networking.services.ServerSocketService;

@ScopeMetadata
@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ControllerModule_ProvideMainViewControllerFactory implements Factory<Object> {
  private final Provider<KeyStorage> keyStorageProvider;

  private final Provider<NetworkPropertiesStorage> networkPropertiesStorageProvider;

  private final Provider<SessionKeyService> sessionKeyServiceProvider;

  private final Provider<MessageService> messageServiceProvider;

  private final Provider<ClientSocketService> clientSocketServiceProvider;

  private final Provider<ServerSocketService> serverSocketServiceProvider;

  private final Provider<RSAService> rsaServiceProvider;

  public ControllerModule_ProvideMainViewControllerFactory(Provider<KeyStorage> keyStorageProvider,
      Provider<NetworkPropertiesStorage> networkPropertiesStorageProvider,
      Provider<SessionKeyService> sessionKeyServiceProvider,
      Provider<MessageService> messageServiceProvider,
      Provider<ClientSocketService> clientSocketServiceProvider,
      Provider<ServerSocketService> serverSocketServiceProvider,
      Provider<RSAService> rsaServiceProvider) {
    this.keyStorageProvider = keyStorageProvider;
    this.networkPropertiesStorageProvider = networkPropertiesStorageProvider;
    this.sessionKeyServiceProvider = sessionKeyServiceProvider;
    this.messageServiceProvider = messageServiceProvider;
    this.clientSocketServiceProvider = clientSocketServiceProvider;
    this.serverSocketServiceProvider = serverSocketServiceProvider;
    this.rsaServiceProvider = rsaServiceProvider;
  }

  @Override
  public Object get() {
    return provideMainViewController(keyStorageProvider.get(), networkPropertiesStorageProvider.get(), sessionKeyServiceProvider.get(), messageServiceProvider.get(), clientSocketServiceProvider.get(), serverSocketServiceProvider.get(), rsaServiceProvider.get());
  }

  public static ControllerModule_ProvideMainViewControllerFactory create(
      Provider<KeyStorage> keyStorageProvider,
      Provider<NetworkPropertiesStorage> networkPropertiesStorageProvider,
      Provider<SessionKeyService> sessionKeyServiceProvider,
      Provider<MessageService> messageServiceProvider,
      Provider<ClientSocketService> clientSocketServiceProvider,
      Provider<ServerSocketService> serverSocketServiceProvider,
      Provider<RSAService> rsaServiceProvider) {
    return new ControllerModule_ProvideMainViewControllerFactory(keyStorageProvider, networkPropertiesStorageProvider, sessionKeyServiceProvider, messageServiceProvider, clientSocketServiceProvider, serverSocketServiceProvider, rsaServiceProvider);
  }

  public static Object provideMainViewController(KeyStorage keyStorage,
      NetworkPropertiesStorage networkPropertiesStorage, SessionKeyService sessionKeyService,
      MessageService messageService, ClientSocketService clientSocketService,
      ServerSocketService serverSocketService, RSAService rsaService) {
    return Preconditions.checkNotNullFromProvides(ControllerModule.provideMainViewController(keyStorage, networkPropertiesStorage, sessionKeyService, messageService, clientSocketService, serverSocketService, rsaService));
  }
}
