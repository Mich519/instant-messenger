package mj.project.encryption.encryptors;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import mj.project.encryption.factories.RSAPrivateKeyFactory;
import mj.project.encryption.factories.RSAPublicKeyFactory;
import mj.project.encryption.services.LocalKeyService;
import mj.project.utils.io.ObjectFileIO;

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
public final class RSAFileIO_Factory implements Factory<RSAFileIO> {
  private final Provider<ObjectFileIO> objectFileIOProvider;

  private final Provider<LocalKeyService> localKeyServiceProvider;

  private final Provider<RSAPrivateKeyFactory> rsaPrivateKeyFactoryProvider;

  private final Provider<RSAPublicKeyFactory> rsaPublicKeyFactoryProvider;

  public RSAFileIO_Factory(Provider<ObjectFileIO> objectFileIOProvider,
      Provider<LocalKeyService> localKeyServiceProvider,
      Provider<RSAPrivateKeyFactory> rsaPrivateKeyFactoryProvider,
      Provider<RSAPublicKeyFactory> rsaPublicKeyFactoryProvider) {
    this.objectFileIOProvider = objectFileIOProvider;
    this.localKeyServiceProvider = localKeyServiceProvider;
    this.rsaPrivateKeyFactoryProvider = rsaPrivateKeyFactoryProvider;
    this.rsaPublicKeyFactoryProvider = rsaPublicKeyFactoryProvider;
  }

  @Override
  public RSAFileIO get() {
    return newInstance(objectFileIOProvider.get(), localKeyServiceProvider.get(), rsaPrivateKeyFactoryProvider.get(), rsaPublicKeyFactoryProvider.get());
  }

  public static RSAFileIO_Factory create(Provider<ObjectFileIO> objectFileIOProvider,
      Provider<LocalKeyService> localKeyServiceProvider,
      Provider<RSAPrivateKeyFactory> rsaPrivateKeyFactoryProvider,
      Provider<RSAPublicKeyFactory> rsaPublicKeyFactoryProvider) {
    return new RSAFileIO_Factory(objectFileIOProvider, localKeyServiceProvider, rsaPrivateKeyFactoryProvider, rsaPublicKeyFactoryProvider);
  }

  public static RSAFileIO newInstance(ObjectFileIO objectFileIO, LocalKeyService localKeyService,
      RSAPrivateKeyFactory rsaPrivateKeyFactory, RSAPublicKeyFactory rsaPublicKeyFactory) {
    return new RSAFileIO(objectFileIO, localKeyService, rsaPrivateKeyFactory, rsaPublicKeyFactory);
  }
}
