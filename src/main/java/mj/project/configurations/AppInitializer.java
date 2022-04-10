package mj.project.configurations;

import mj.project.networking.ServerSocketService;

import java.io.IOException;

public class AppInitializer {
    private final AppConfigSerializer appConfigSerializer;

    public AppInitializer() {
        this.appConfigSerializer = new AppConfigSerializer();
    }

    public void init() {

        try {
            appConfigSerializer.loadSettingsFromFile();
            ServerSocketService.getInstance().startListening();
        } catch (IOException e) {
            try {
                appConfigSerializer.saveSettingsToFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
