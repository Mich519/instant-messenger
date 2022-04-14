package mj.project.encryption.data;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class KeyStorage_Factory implements Factory<KeyStorage> {
  @Override
  public KeyStorage get() {
    return newInstance();
  }

  public static KeyStorage_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static KeyStorage newInstance() {
    return new KeyStorage();
  }

  private static final class InstanceHolder {
    private static final KeyStorage_Factory INSTANCE = new KeyStorage_Factory();
  }
}
