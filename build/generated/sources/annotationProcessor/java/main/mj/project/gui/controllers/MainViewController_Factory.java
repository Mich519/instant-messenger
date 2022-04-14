package mj.project.gui.controllers;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class MainViewController_Factory implements Factory<MainViewController> {
  private final Provider<KeyStorage> keyStorageProvider;

  private final Provider<NetworkPropertiesStorage> networkPropertiesStorageProvider;

  private final Provider<SessionKeyService> sessionKeyServiceProvider;

  private final Provider<MessageService> messageServiceProvider;

  private final Provider<ClientSocketService> clientSocketServiceProvider;

  private final Provider<ServerSocketService> serverSocketServiceProvider;

  private final Provider<RSAService> rsaServiceProvider;

  public MainViewController_Factory(Provider<KeyStorage> keyStorageProvider,
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
  public MainViewController get() {
    return newInstance(keyStorageProvider.get(), networkPropertiesStorageProvider.get(), sessionKeyServiceProvider.get(), messageServiceProvider.get(), clientSocketServiceProvider.get(), serverSocketServiceProvider.get(), rsaServiceProvider.get());
  }

  public static MainViewController_Factory create(Provider<KeyStorage> keyStorageProvider,
      Provider<NetworkPropertiesStorage> networkPropertiesStorageProvider,
      Provider<SessionKeyService> sessionKeyServiceProvider,
      Provider<MessageService> messageServiceProvider,
      Provider<ClientSocketService> clientSocketServiceProvider,
      Provider<ServerSocketService> serverSocketServiceProvider,
      Provider<RSAService> rsaServiceProvider) {
    return new MainViewController_Factory(keyStorageProvider, networkPropertiesStorageProvider, sessionKeyServiceProvider, messageServiceProvider, clientSocketServiceProvider, serverSocketServiceProvider, rsaServiceProvider);
  }

  public static MainViewController newInstance(KeyStorage keyStorage,
      NetworkPropertiesStorage networkPropertiesStorage, SessionKeyService sessionKeyService,
      MessageService messageService, ClientSocketService clientSocketService,
      ServerSocketService serverSocketService, RSAService rsaService) {
    return new MainViewController(keyStorage, networkPropertiesStorage, sessionKeyService, messageService, clientSocketService, serverSocketService, rsaService);
  }
}
