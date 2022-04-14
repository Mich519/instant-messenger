package mj.project.gui.controllers;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import mj.project.gui.events.GenerateKeyPairEventHandler;

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
public final class SettingsViewController_Factory implements Factory<SettingsViewController> {
  private final Provider<GenerateKeyPairEventHandler> generateKeyPairEventHandlerProvider;

  public SettingsViewController_Factory(
      Provider<GenerateKeyPairEventHandler> generateKeyPairEventHandlerProvider) {
    this.generateKeyPairEventHandlerProvider = generateKeyPairEventHandlerProvider;
  }

  @Override
  public SettingsViewController get() {
    return newInstance(generateKeyPairEventHandlerProvider.get());
  }

  public static SettingsViewController_Factory create(
      Provider<GenerateKeyPairEventHandler> generateKeyPairEventHandlerProvider) {
    return new SettingsViewController_Factory(generateKeyPairEventHandlerProvider);
  }

  public static SettingsViewController newInstance(
      GenerateKeyPairEventHandler generateKeyPairEventHandler) {
    return new SettingsViewController(generateKeyPairEventHandler);
  }
}
