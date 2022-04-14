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
public final class SessionKeyFactory_Factory implements Factory<SessionKeyFactory> {
  @Override
  public SessionKeyFactory get() {
    return newInstance();
  }

  public static SessionKeyFactory_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SessionKeyFactory newInstance() {
    return new SessionKeyFactory();
  }

  private static final class InstanceHolder {
    private static final SessionKeyFactory_Factory INSTANCE = new SessionKeyFactory_Factory();
  }
}
