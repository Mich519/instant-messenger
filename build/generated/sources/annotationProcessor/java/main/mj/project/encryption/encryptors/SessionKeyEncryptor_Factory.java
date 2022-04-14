package mj.project.encryption.encryptors;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class SessionKeyEncryptor_Factory implements Factory<SessionKeyEncryptor> {
  private final Provider<AESEncryptor> aesEncryptorProvider;

  public SessionKeyEncryptor_Factory(Provider<AESEncryptor> aesEncryptorProvider) {
    this.aesEncryptorProvider = aesEncryptorProvider;
  }

  @Override
  public SessionKeyEncryptor get() {
    return newInstance(aesEncryptorProvider.get());
  }

  public static SessionKeyEncryptor_Factory create(Provider<AESEncryptor> aesEncryptorProvider) {
    return new SessionKeyEncryptor_Factory(aesEncryptorProvider);
  }

  public static SessionKeyEncryptor newInstance(AESEncryptor aesEncryptor) {
    return new SessionKeyEncryptor(aesEncryptor);
  }
}
