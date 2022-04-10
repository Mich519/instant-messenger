package mj.project.configurations;

import mj.project.networking.ServerSocketService;

import javax.inject.Inject;
import java.io.IOException;

public class AppInitializer {
    private final AppConfigSerializer appConfigSerializer;
    private final ServerSocketService serverSocketService;

    @Inject
    public AppInitializer(AppConfigSerializer appConfigSerializer, ServerSocketService serverSocketService) {
        this.appConfigSerializer = appConfigSerializer;
        this.serverSocketService = serverSocketService;
    }

    public void init() {

        try {
            appConfigSerializer.loadSettingsFromFile();
            serverSocketService.startListening();
        } catch (IOException e) {
            try {
                appConfigSerializer.saveSettingsToFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
