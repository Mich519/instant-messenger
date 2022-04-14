package mj.project.networking.services;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import mj.project.networking.message.MessageReader;

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
public final class ServerSocketListenTask_Factory implements Factory<ServerSocketListenTask> {
  private final Provider<MessageReader> messageReaderProvider;

  public ServerSocketListenTask_Factory(Provider<MessageReader> messageReaderProvider) {
    this.messageReaderProvider = messageReaderProvider;
  }

  @Override
  public ServerSocketListenTask get() {
    return newInstance(messageReaderProvider.get());
  }

  public static ServerSocketListenTask_Factory create(
      Provider<MessageReader> messageReaderProvider) {
    return new ServerSocketListenTask_Factory(messageReaderProvider);
  }

  public static ServerSocketListenTask newInstance(MessageReader messageReader) {
    return new ServerSocketListenTask(messageReader);
  }
}
