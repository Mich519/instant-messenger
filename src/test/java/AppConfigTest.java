import mj.project.configurations.AppConfigSerializer;
import org.junit.jupiter.api.Test;

public class AppConfigTest {

    @Test
    void testowanko() {
        AppConfigSerializer appConfigSerializer = new AppConfigSerializer();
        appConfigSerializer.saveSettingsToFile();
    }

    @Test
    void testowanko2() {
        AppConfigSerializer appConfigSerializer = new AppConfigSerializer();
        appConfigSerializer.loadSettingsFromFile();
    }
}
