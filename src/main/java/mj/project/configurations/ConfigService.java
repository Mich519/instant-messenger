package mj.project.configurations;

import java.io.*;
import java.util.Properties;

/**
 * This class is used for saving/loading config file
 * to/from local disk
 */
public class ConfigService {


    public void saveConfigToFile() {

        /*try {
            File appConfig = new File(AppConfig.CONFIG_FILENAME);
            FileInputStream propsInput = null;
            propsInput = new FileInputStream(AppConfig.CONFIG_FILENAME);
            Properties prop = new Properties();
            prop.load(propsInput);
            try (Writer inputStream = new FileWriter(appConfig)) {

                // Setting the properties.
                prop.setProperty("A_PROP", "A_VALUE");

                // Storing the properties in the file with a heading comment.
                prop.store(inputStream, "INFORMATION!!!");

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    public void loadConfigFromFile() {

    }
}
