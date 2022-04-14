package mj.project.encryption.encryptors;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import mj.project.encryption.factories.RSAPrivateKeyFactory;
import mj.project.encryption.factories.RSAPublicKeyFactory;

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
public final class RSAEncryptor_Factory implements Factory<RSAEncryptor> {
  private final Provider<RSAPublicKeyFactory> rsaPublicKeyFactoryProvider;

  private final Provider<RSAPrivateKeyFactory> rsaPrivateKeyFactoryProvider;

  public RSAEncryptor_Factory(Provider<RSAPublicKeyFactory> rsaPublicKeyFactoryProvider,
      Provider<RSAPrivateKeyFactory> rsaPrivateKeyFactoryProvider) {
    this.rsaPublicKeyFactoryProvider = rsaPublicKeyFactoryProvider;
    this.rsaPrivateKeyFactoryProvider = rsaPrivateKeyFactoryProvider;
  }

  @Override
  public RSAEncryptor get() {
    return newInstance(rsaPublicKeyFactoryProvider.get(), rsaPrivateKeyFactoryProvider.get());
  }

  public static RSAEncryptor_Factory create(
      Provider<RSAPublicKeyFactory> rsaPublicKeyFactoryProvider,
      Provider<RSAPrivateKeyFactory> rsaPrivateKeyFactoryProvider) {
    return new RSAEncryptor_Factory(rsaPublicKeyFactoryProvider, rsaPrivateKeyFactoryProvider);
  }

  public static RSAEncryptor newInstance(RSAPublicKeyFactory rsaPublicKeyFactory,
      RSAPrivateKeyFactory rsaPrivateKeyFactory) {
    return new RSAEncryptor(rsaPublicKeyFactory, rsaPrivateKeyFactory);
  }
}
