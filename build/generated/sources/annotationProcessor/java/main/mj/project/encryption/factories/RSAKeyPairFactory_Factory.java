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
public final class RSAKeyPairFactory_Factory implements Factory<RSAKeyPairFactory> {
  @Override
  public RSAKeyPairFactory get() {
    return newInstance();
  }

  public static RSAKeyPairFactory_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RSAKeyPairFactory newInstance() {
    return new RSAKeyPairFactory();
  }

  private static final class InstanceHolder {
    private static final RSAKeyPairFactory_Factory INSTANCE = new RSAKeyPairFactory_Factory();
  }
}
