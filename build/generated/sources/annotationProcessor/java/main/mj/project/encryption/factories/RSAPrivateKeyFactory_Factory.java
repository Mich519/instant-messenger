package mj.project.encryption.factories;

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
public final class RSAPrivateKeyFactory_Factory implements Factory<RSAPrivateKeyFactory> {
  @Override
  public RSAPrivateKeyFactory get() {
    return newInstance();
  }

  public static RSAPrivateKeyFactory_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RSAPrivateKeyFactory newInstance() {
    return new RSAPrivateKeyFactory();
  }

  private static final class InstanceHolder {
    private static final RSAPrivateKeyFactory_Factory INSTANCE = new RSAPrivateKeyFactory_Factory();
  }
}
