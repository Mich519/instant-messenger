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

    public static final String PRIVATE_KEY_DIR_PATH = BASE_PATH + "\\private";
    public static final String PRIVATE_KEY_FILE_PATH = PRIVATE_KEY_DIR_PATH + "\\private_key.json";

    public static final String PUBLIC_KEY_DIR_PATH = BASE_PATH + "\\public";
    public static final String PUBLIC_KEY_FILE_PATH = PUBLIC_KEY_DIR_PATH + "\\public_key.json";

    public static final String TEMP_FILE_DIR_PATH = BASE_PATH + "\\temp";

    public static final List<String> ALLOWED_FILE_EXTENSIONS = List.of("*.txt", "*.png", "*.pdf", "*.avi");
    public static int MAX_BYTES_PER_PACKET = 1000;
}
