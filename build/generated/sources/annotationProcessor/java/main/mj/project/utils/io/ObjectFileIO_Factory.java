package mj.project.utils.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class ObjectFileIO_Factory implements Factory<ObjectFileIO> {
  private final Provider<ObjectMapper> objectMapperProvider;

  public ObjectFileIO_Factory(Provider<ObjectMapper> objectMapperProvider) {
    this.objectMapperProvider = objectMapperProvider;
  }

  @Override
  public ObjectFileIO get() {
    return newInstance(objectMapperProvider.get());
  }

  public static ObjectFileIO_Factory create(Provider<ObjectMapper> objectMapperProvider) {
    return new ObjectFileIO_Factory(objectMapperProvider);
  }

  public static ObjectFileIO newInstance(ObjectMapper objectMapper) {
    return new ObjectFileIO(objectMapper);
  }
}
