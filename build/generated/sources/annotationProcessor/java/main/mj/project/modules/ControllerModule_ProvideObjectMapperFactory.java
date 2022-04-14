package mj.project.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class ControllerModule_ProvideObjectMapperFactory implements Factory<ObjectMapper> {
  private final ControllerModule module;

  public ControllerModule_ProvideObjectMapperFactory(ControllerModule module) {
    this.module = module;
  }

  @Override
  public ObjectMapper get() {
    return provideObjectMapper(module);
  }

  public static ControllerModule_ProvideObjectMapperFactory create(ControllerModule module) {
    return new ControllerModule_ProvideObjectMapperFactory(module);
  }

  public static ObjectMapper provideObjectMapper(ControllerModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideObjectMapper());
  }
}
