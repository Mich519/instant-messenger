package mj.project.encryption.services;

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
public final class LocalKeyService_Factory implements Factory<LocalKeyService> {
  @Override
  public LocalKeyService get() {
    return newInstance();
  }

  public static LocalKeyService_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static LocalKeyService newInstance() {
    return new LocalKeyService();
  }

  private static final class InstanceHolder {
    private static final LocalKeyService_Factory INSTANCE = new LocalKeyService_Factory();
  }
}
