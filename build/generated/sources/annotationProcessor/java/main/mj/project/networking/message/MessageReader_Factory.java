package mj.project.networking.message;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.SessionKeyService;
import mj.project.networking.data.NetworkPropertiesStorage;

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
public final class MessageReader_Factory implements Factory<MessageReader> {
  private final Provider<KeyStorage> keyStorageProvider;

  private final Provider<SessionKeyService> sessionKeyServiceProvider;

  private final Provider<NetworkPropertiesStorage> networkPropertiesStorageProvider;

  public MessageReader_Factory(Provider<KeyStorage> keyStorageProvider,
      Provider<SessionKeyService> sessionKeyServiceProvider,
      Provider<NetworkPropertiesStorage> networkPropertiesStorageProvider) {
    this.keyStorageProvider = keyStorageProvider;
    this.sessionKeyServiceProvider = sessionKeyServiceProvider;
    this.networkPropertiesStorageProvider = networkPropertiesStorageProvider;
  }

  @Override
  public MessageReader get() {
    return newInstance(keyStorageProvider.get(), sessionKeyServiceProvider.get(), networkPropertiesStorageProvider.get());
  }

  public static MessageReader_Factory create(Provider<KeyStorage> keyStorageProvider,
      Provider<SessionKeyService> sessionKeyServiceProvider,
      Provider<NetworkPropertiesStorage> networkPropertiesStorageProvider) {
    return new MessageReader_Factory(keyStorageProvider, sessionKeyServiceProvider, networkPropertiesStorageProvider);
  }

  public static MessageReader newInstance(KeyStorage keyStorage,
      SessionKeyService sessionKeyService, NetworkPropertiesStorage networkPropertiesStorage) {
    return new MessageReader(keyStorage, sessionKeyService, networkPropertiesStorage);
  }
}
