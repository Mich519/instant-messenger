package mj.project.configurations;

import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;
import java.util.List;


public class AppConfig {

    private AppConfig() {}

    @Getter
    @Setter
    private static int SCREEN_WIDTH = 640;

    @Getter
    @Setter
    private static int SCREEN_HEIGHT = 480;

    @Getter
    @Setter
    private static String WINDOW_TITLE = "BSK Project";

    @Getter
    @Setter
    private static boolean WINDOW_RESIZABLE = false;

    @Getter
    @Setter
    private static String CONFIG_FILENAME = "config_file";

    @Getter
    @Setter
    private static Path CONFIG_FILE_PATH = Path.of("/");

    @Getter
    @Setter
    private static Path PRIVATE_KEY_PATH = Path.of("/");

    @Getter
    @Setter
    private static Path PUBLIC_KEY_PATH = Path.of("/");

    @Getter
    @Setter
    private static int MY_PORT = 8080;

    @Getter
    @Setter
    private static String TARGET_IP = "127.0.0.1";

    @Getter
    @Setter
    private static int TARGET_PORT = 8080;

    @Getter
    @Setter
    private static List<String> ALLOWED_FILE_EXTENSIONS = List.of("*.txt", "*.png", "*.pdf", "*.avi");
}
