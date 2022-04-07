package mj.project.configurations;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;

/**
 * This class is used for serializing and deserializing AppConfig object
 */
public class AppConfigSerializer {

    private final ObjectMapper objectMapper;
    private final AppConfig appConfigInstance;

    public AppConfigSerializer() {
        this.objectMapper = new ObjectMapper();
        this.appConfigInstance = AppConfig.getInstance();
    }

    /**
     * Serialize AppConfig object to JSON format and save it to file
     */
    public void serialize() {
        try {
            ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
            String configPath = appConfigInstance.getConfigFilename();
            writer.writeValue(new File(configPath), appConfigInstance);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserialize JSON file to AppConfig object
     */
    public void deserialize() {
        try {
            String configPath = appConfigInstance.getConfigFilename();
            AppConfig loadedAppConfig = objectMapper.readValue(new File(configPath), AppConfig.class);
            AppConfig.setInstance(loadedAppConfig);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

