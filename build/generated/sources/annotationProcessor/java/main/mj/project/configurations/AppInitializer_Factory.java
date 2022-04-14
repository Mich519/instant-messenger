package mj.project.configurations;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import mj.project.encryption.encryptors.RSAFileIO;
import mj.project.networking.services.ServerSocketService;

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
public final class AppInitializer_Factory implements Factory<AppInitializer> {
  private final Provider<ServerSocketService> serverSocketServiceProvider;

  private final Provider<RSAFileIO> rsaFileIOProvider;

  public AppInitializer_Factory(Provider<ServerSocketService> serverSocketServiceProvider,
      Provider<RSAFileIO> rsaFileIOProvider) {
    this.serverSocketServiceProvider = serverSocketServiceProvider;
    this.rsaFileIOProvider = rsaFileIOProvider;
  }

  @Override
  public AppInitializer get() {
    return newInstance(serverSocketServiceProvider.get(), rsaFileIOProvider.get());
  }

  public static AppInitializer_Factory create(
      Provider<ServerSocketService> serverSocketServiceProvider,
      Provider<RSAFileIO> rsaFileIOProvider) {
    return new AppInitializer_Factory(serverSocketServiceProvider, rsaFileIOProvider);
  }

  public static AppInitializer newInstance(ServerSocketService serverSocketService,
      RSAFileIO rsaFileIO) {
    return new AppInitializer(serverSocketService, rsaFileIO);
  }
}
