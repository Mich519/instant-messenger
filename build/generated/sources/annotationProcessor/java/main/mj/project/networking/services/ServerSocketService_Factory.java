package mj.project.networking.services;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class ServerSocketService_Factory implements Factory<ServerSocketService> {
  private final Provider<ServerSocketListenTask> serverSocketListenTaskProvider;

  public ServerSocketService_Factory(
      Provider<ServerSocketListenTask> serverSocketListenTaskProvider) {
    this.serverSocketListenTaskProvider = serverSocketListenTaskProvider;
  }

  @Override
  public ServerSocketService get() {
    return newInstance(serverSocketListenTaskProvider.get());
  }

  public static ServerSocketService_Factory create(
      Provider<ServerSocketListenTask> serverSocketListenTaskProvider) {
    return new ServerSocketService_Factory(serverSocketListenTaskProvider);
  }

  public static ServerSocketService newInstance(ServerSocketListenTask serverSocketListenTask) {
    return new ServerSocketService(serverSocketListenTask);
  }
}
