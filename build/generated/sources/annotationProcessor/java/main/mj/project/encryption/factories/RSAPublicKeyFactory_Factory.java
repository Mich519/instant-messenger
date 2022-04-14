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
public final class RSAPublicKeyFactory_Factory implements Factory<RSAPublicKeyFactory> {
  @Override
  public RSAPublicKeyFactory get() {
    return newInstance();
  }

  public static RSAPublicKeyFactory_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RSAPublicKeyFactory newInstance() {
    return new RSAPublicKeyFactory();
  }

  private static final class InstanceHolder {
    private static final RSAPublicKeyFactory_Factory INSTANCE = new RSAPublicKeyFactory_Factory();
  }
}
