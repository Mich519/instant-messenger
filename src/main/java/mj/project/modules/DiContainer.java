package mj.project.modules;

import dagger.Component;

import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
@Component(modules = {ControllerModule.class})
public interface DiContainer {

    @Named("Controllers")
    Map<Class<?>, Provider<Object>> getControllers();
}
