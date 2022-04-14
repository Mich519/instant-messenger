package mj.project.gui.windows;

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
public final class MainWindow_Factory implements Factory<MainWindow> {
  @Override
  public MainWindow get() {
    return newInstance();
  }

  public static MainWindow_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static MainWindow newInstance() {
    return new MainWindow();
  }

  private static final class InstanceHolder {
    private static final MainWindow_Factory INSTANCE = new MainWindow_Factory();
  }
}
