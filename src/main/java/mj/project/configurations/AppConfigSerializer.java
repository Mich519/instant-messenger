package mj.project.configurations;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

/**
 * This class is used for serializing and deserializing AppConfig object
 */
public class AppConfigSerializer {

    private final ObjectMapper objectMapper;
    private final AppConfig appConfigInstance = AppConfig.getInstance();

    public AppConfigSerializer() {
        this.objectMapper = new ObjectMapper();
    }

    public void saveSettingsToFile() throws IOException {
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        String configPath = appConfigInstance.getConfigFilePath();
        File directory = new File(AppConfig.getInstance().getBasePath());
        if (!directory.exists()) {
            directory.mkdir();

        }
        writer.writeValue(new File(configPath), appConfigInstance);
    }

    public void loadSettingsFromFile() throws IOException {
        String configPath = appConfigInstance.getConfigFilePath();
        AppConfig loadedAppConfig = objectMapper.readValue(new File(configPath), AppConfig.class);
        AppConfig.setInstance(loadedAppConfig);
    }
}

