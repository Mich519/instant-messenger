package mj.project.networking.services;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import mj.project.networking.factories.MessageFactory;

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
public final class MessageService_Factory implements Factory<MessageService> {
  private final Provider<MessageFactory> messageFactoryProvider;

  public MessageService_Factory(Provider<MessageFactory> messageFactoryProvider) {
    this.messageFactoryProvider = messageFactoryProvider;
  }

  @Override
  public MessageService get() {
    return newInstance(messageFactoryProvider.get());
  }

  public static MessageService_Factory create(Provider<MessageFactory> messageFactoryProvider) {
    return new MessageService_Factory(messageFactoryProvider);
  }

  public static MessageService newInstance(MessageFactory messageFactory) {
    return new MessageService(messageFactory);
  }
}
