import mj.project.configurations.AppConfig;
import mj.project.configurations.AppConfigSerializer;
import org.junit.jupiter.api.Test;

public class AppConfigTest {

    @Test
    void testowanko() {
        AppConfigSerializer appConfigSerializer = new AppConfigSerializer();
        appConfigSerializer.serialize();
    }

    @Test
    void testowanko2() {
        AppConfigSerializer appConfigSerializer = new AppConfigSerializer();
        appConfigSerializer.deserialize();
    }
}
