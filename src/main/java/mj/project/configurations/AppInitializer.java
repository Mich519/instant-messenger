package mj.project.configurations;

import mj.project.encryption.encryptors.RSAFileIO;
import mj.project.networking.services.ServerSocketService;

import javax.inject.Inject;

public class AppInitializer {

    private final ServerSocketService serverSocketService;
    private final RSAFileIO rsaFileIO;

    @Inject
    public AppInitializer(ServerSocketService serverSocketService, RSAFileIO rsaFileIO) {
        this.serverSocketService = serverSocketService;
        this.rsaFileIO = rsaFileIO;
    }

    public void initialize() {

        serverSocketService.startListening();

        /*try {
            appConfigSerializer.loadSettingsFromFile();
            serverSocketService.startListening();
        } catch (IOException e) {
            try {
                appConfigSerializer.saveSettingsToFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }*/
    }
}
