package mj.project.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.RSAService;
import mj.project.encryption.services.SessionKeyService;
import mj.project.gui.controllers.MainViewController;
import mj.project.networking.data.NetworkPropertiesStorage;
import mj.project.networking.services.ClientSocketService;
import mj.project.networking.services.MessageService;
import mj.project.networking.services.ServerSocketService;

import javax.inject.Named;

@Module
public class ControllerModule {

    @Provides
    @IntoMap
    @Named("Controllers")
    @ClassKey(MainViewController.class)
    static Object provideMainViewController(KeyStorage keyStorage,
                                            NetworkPropertiesStorage networkPropertiesStorage,
                                            SessionKeyService sessionKeyService,
                                            MessageService messageService,
                                            ClientSocketService clientSocketService,
                                            ServerSocketService serverSocketService,
                                            RSAService rsaService) {
        return new MainViewController(keyStorage,
                networkPropertiesStorage,
                 sessionKeyService,
                 messageService,
                 clientSocketService,
                 serverSocketService, rsaService);
    }

    /*@Provides
    @IntoMap
    @Named("Controllers")
    @ClassKey(SettingsViewController.class)
    static Object provideSettingsViewController(GenerateKeyPairEventHandler generateKeyPairEventHandler) {
        return new SettingsViewController(generateKeyPairEventHandler);
    }*/

    @Provides
    public ObjectMapper provideObjectMapper() {
        return new ObjectMapper();
    }
}
