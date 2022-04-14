package mj.project.encryption.encryptors;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class AESEncryptor_Factory implements Factory<AESEncryptor> {
  @Override
  public AESEncryptor get() {
    return newInstance();
  }

  public static AESEncryptor_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AESEncryptor newInstance() {
    return new AESEncryptor();
  }

  private static final class InstanceHolder {
    private static final AESEncryptor_Factory INSTANCE = new AESEncryptor_Factory();
  }
}
