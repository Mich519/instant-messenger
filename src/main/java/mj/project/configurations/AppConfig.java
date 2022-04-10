package mj.project.configurations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;

import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.List;

/**
 * This class contains application properties
 */
@Getter(onMethod_={@Synchronized})
@Setter(onMethod_={@Synchronized})
public class AppConfig {

    private static AppConfig APP_CONFIG_INSTANCE = new AppConfig();

    // gui properties
    private int screenWidth = 640;
    private int screenHeight = 480;
    private String windowTitle = "BSK Project";
    private boolean windowResizable = false;

    // paths
    private String basePath = System.getProperty("user.home") + "\\.bsk-project";
    private String configFilePath = basePath + "\\config.json";
    private String privateKeyPath = basePath + "\\private\\private_key";
    private String publicKeyPath = basePath + "\\public\\public_key";

    private String myNickName = "John";
    private int myPort = 8080;
    private String targetIp = "127.0.0.1";
    private int targetPort = 8080;
    private List<String> allowedFileExtensions = List.of("*.txt", "*.png", "*.pdf", "*.avi");
    volatile private PublicKey recipientPublicKey = null;

    @JsonIgnore
    volatile private SecretKeySpec sessionKey = null;

    private KeyPair thisKeyPair = null;
    private String blockCipher = "CBC";

    private AppConfig() {}

    public static AppConfig getInstance(){
        return APP_CONFIG_INSTANCE;
    }

    public static void setInstance(AppConfig appConfig){
        APP_CONFIG_INSTANCE = appConfig;
    }
}
