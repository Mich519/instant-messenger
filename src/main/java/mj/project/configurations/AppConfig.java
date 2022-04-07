package mj.project.configurations;

import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.List;

/**
 * This class contains application properties
 */
@Getter
@Setter
public class AppConfig {

    private static AppConfig APP_CONFIG_INSTANCE = new AppConfig();

    private int screenWidth = 640;
    private int screenHeight = 480;
    private String windowTitle = "BSK Project";
    private boolean windowResizable = false;
    private String configFilename = "config_file";
    private String configFilePath = "bsk-config.json";
    private Path privateKeyPath = Path.of("/");
    private Path publicKeyPath = Path.of("/");
    private int myPort = 8080;
    private String targetIp = "127.0.0.1";
    private int targetPort = 8080;
    private List<String> allowedFileExtensions = List.of("*.txt", "*.png", "*.pdf", "*.avi");
    private PublicKey recipientPublicKey = null;
    private KeyPair thisKeyPair = null;



    private AppConfig() {}

    public static AppConfig getInstance(){
        return APP_CONFIG_INSTANCE;
    }

    public static void setInstance(AppConfig appConfig){
        APP_CONFIG_INSTANCE = appConfig;
    }
}
