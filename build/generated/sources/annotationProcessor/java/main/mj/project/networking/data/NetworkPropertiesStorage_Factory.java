package mj.project.networking.data;

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
public final class NetworkPropertiesStorage_Factory implements Factory<NetworkPropertiesStorage> {
  @Override
  public NetworkPropertiesStorage get() {
    return newInstance();
  }

  public static NetworkPropertiesStorage_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static NetworkPropertiesStorage newInstance() {
    return new NetworkPropertiesStorage();
  }

  private static final class InstanceHolder {
    private static final NetworkPropertiesStorage_Factory INSTANCE = new NetworkPropertiesStorage_Factory();
  }
}
