package mj.project.gui.windows.ed;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import mj.project.configurations.AppInitializer;
import mj.project.gui.controllers.MainViewController;
import mj.project.gui.events.*;

import javax.inject.Named;

@Module
public class MyModule {

    // ********************** CONTROLLERS **********************
    @Provides
    @IntoMap
    @Named("Controllers")
    @ClassKey(MainViewController.class)
    static Object provideMainViewController(SendMessageEventHandler sendMessageEventHandler, ConnectEventHandler connectEventHandler, SendSessionKeyEventHandler sendSessionKeyEventHandler, AppInitializer appInitializer, ListenEventHandler listenEventHandler, StopEventHandler stopEventHandler) {
        return new MainViewController( sendMessageEventHandler,  connectEventHandler,  sendSessionKeyEventHandler,  appInitializer,  listenEventHandler,  stopEventHandler);
    }
}
