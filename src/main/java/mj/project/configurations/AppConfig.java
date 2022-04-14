package mj.project.configurations;

import javax.inject.Inject;
import java.util.List;

/**
 * This class contains application properties
 */
public class AppConfig {

    @Inject
    public AppConfig() {}

    // gui properties
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 700;
    public static final String WINDOW_TITLE = "BSK Project";
    public static final boolean WINDOW_RESIZABLE = false;

    // paths
    public static final String BASE_PATH = System.getProperty("user.home") + "\\.bsk-project";
    public static final String CONFIG_FILE_PATH = BASE_PATH + "\\config.json";
    public static final String PRIVATE_KEY_PATH = BASE_PATH + "\\private\\private_key";
    public static final String PUBLIC_KEY_PATH = BASE_PATH + "\\public\\public_key";

    public static final List<String> ALLOWED_FILE_EXTENSIONS = List.of("*.txt", "*.png", "*.pdf", "*.avi");
}
