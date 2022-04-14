package mj.project.encryption.services;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import mj.project.encryption.encryptors.RSAEncryptor;
import mj.project.encryption.encryptors.RSAFileIO;
import mj.project.encryption.factories.RSAKeyPairFactory;

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
public final class RSAService_Factory implements Factory<RSAService> {
  private final Provider<RSAEncryptor> rsaEncryptorProvider;

  private final Provider<RSAKeyPairFactory> rsaKeyPairFactoryProvider;

  private final Provider<RSAFileIO> rsaFileIOProvider;

  public RSAService_Factory(Provider<RSAEncryptor> rsaEncryptorProvider,
      Provider<RSAKeyPairFactory> rsaKeyPairFactoryProvider,
      Provider<RSAFileIO> rsaFileIOProvider) {
    this.rsaEncryptorProvider = rsaEncryptorProvider;
    this.rsaKeyPairFactoryProvider = rsaKeyPairFactoryProvider;
    this.rsaFileIOProvider = rsaFileIOProvider;
  }

  @Override
  public RSAService get() {
    return newInstance(rsaEncryptorProvider.get(), rsaKeyPairFactoryProvider.get(), rsaFileIOProvider.get());
  }

  public static RSAService_Factory create(Provider<RSAEncryptor> rsaEncryptorProvider,
      Provider<RSAKeyPairFactory> rsaKeyPairFactoryProvider,
      Provider<RSAFileIO> rsaFileIOProvider) {
    return new RSAService_Factory(rsaEncryptorProvider, rsaKeyPairFactoryProvider, rsaFileIOProvider);
  }

  public static RSAService newInstance(RSAEncryptor rsaEncryptor,
      RSAKeyPairFactory rsaKeyPairFactory, RSAFileIO rsaFileIO) {
    return new RSAService(rsaEncryptor, rsaKeyPairFactory, rsaFileIO);
  }
}
