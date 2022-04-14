package mj.project.networking.factories;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
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
public final class MessageFactory_Factory implements Factory<MessageFactory> {
  private final Provider<NetworkPropertiesStorage> networkPropertiesStorageProvider;

  public MessageFactory_Factory(
      Provider<NetworkPropertiesStorage> networkPropertiesStorageProvider) {
    this.networkPropertiesStorageProvider = networkPropertiesStorageProvider;
  }

  @Override
  public MessageFactory get() {
    return newInstance(networkPropertiesStorageProvider.get());
  }

  public static MessageFactory_Factory create(
      Provider<NetworkPropertiesStorage> networkPropertiesStorageProvider) {
    return new MessageFactory_Factory(networkPropertiesStorageProvider);
  }

  public static MessageFactory newInstance(NetworkPropertiesStorage networkPropertiesStorage) {
    return new MessageFactory(networkPropertiesStorage);
  }
}
