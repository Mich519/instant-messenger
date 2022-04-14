package mj.project.networking.services;

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
public final class ClientSocketService_Factory implements Factory<ClientSocketService> {
  @Override
  public ClientSocketService get() {
    return newInstance();
  }

  public static ClientSocketService_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ClientSocketService newInstance() {
    return new ClientSocketService();
  }

  private static final class InstanceHolder {
    private static final ClientSocketService_Factory INSTANCE = new ClientSocketService_Factory();
  }
}
