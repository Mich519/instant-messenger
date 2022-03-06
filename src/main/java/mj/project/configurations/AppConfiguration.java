package mj.project.configurations;

import java.nio.file.Path;

public class AppConfiguration {

    private AppConfiguration() {}

    public final static int SCREEN_WIDTH = 640;
    public final static int SCREEN_HEIGHT = 480;
    public final static String WINDOW_TITLE = "BSK Project";
    public final static boolean WINDOW_RESIZABLE = false;

    public final static Path privateKeyPath = Path.of("/");
    public final static Path publicKeyPath = Path.of("/");
}
