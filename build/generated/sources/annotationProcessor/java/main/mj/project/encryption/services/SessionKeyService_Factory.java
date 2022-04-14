package mj.project.encryption.services;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import mj.project.encryption.encryptors.SessionKeyEncryptor;
import mj.project.encryption.factories.SessionKeyFactory;

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
public final class SessionKeyService_Factory implements Factory<SessionKeyService> {
  private final Provider<SessionKeyFactory> sessionKeyFactoryProvider;

  private final Provider<SessionKeyEncryptor> sessionKeyEncryptorProvider;

  private final Provider<RSAService> rsaServiceProvider;

  public SessionKeyService_Factory(Provider<SessionKeyFactory> sessionKeyFactoryProvider,
      Provider<SessionKeyEncryptor> sessionKeyEncryptorProvider,
      Provider<RSAService> rsaServiceProvider) {
    this.sessionKeyFactoryProvider = sessionKeyFactoryProvider;
    this.sessionKeyEncryptorProvider = sessionKeyEncryptorProvider;
    this.rsaServiceProvider = rsaServiceProvider;
  }

  @Override
  public SessionKeyService get() {
    return newInstance(sessionKeyFactoryProvider.get(), sessionKeyEncryptorProvider.get(), rsaServiceProvider.get());
  }

  public static SessionKeyService_Factory create(
      Provider<SessionKeyFactory> sessionKeyFactoryProvider,
      Provider<SessionKeyEncryptor> sessionKeyEncryptorProvider,
      Provider<RSAService> rsaServiceProvider) {
    return new SessionKeyService_Factory(sessionKeyFactoryProvider, sessionKeyEncryptorProvider, rsaServiceProvider);
  }

  public static SessionKeyService newInstance(SessionKeyFactory sessionKeyFactory,
      SessionKeyEncryptor sessionKeyEncryptor, RSAService rsaService) {
    return new SessionKeyService(sessionKeyFactory, sessionKeyEncryptor, rsaService);
  }
}
