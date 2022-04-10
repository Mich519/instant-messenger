package mj.project.gui.windows.ed;

import dagger.Component;
import mj.project.modules.AppModule;

import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
@Component(modules = {MyModule.class, AppModule.class})
public interface MyDiContainer {
    // ********************** CONTROLLERS **********************
    @Named("Controllers")
    Map<Class<?>, Provider<Object>> getControllers();
}
