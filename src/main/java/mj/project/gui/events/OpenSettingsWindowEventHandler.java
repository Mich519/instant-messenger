package mj.project.gui.events;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mj.project.configurations.AppConfig;
import mj.project.modules.DaggerDiContainer;
import mj.project.modules.DiContainer;

import javax.inject.Inject;
import javax.inject.Provider;
import java.io.IOException;
import java.net.URL;
import java.util.Map;


// TODO: allow creating only one instance of the window
// TODO: make main window inaccessible when settings window is opened

public class OpenSettingsWindowEventHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        final String settingsViewFxmlPath = "/views/SettingsView.fxml";
        URL settingsViewFxmlResource = getClass().getResource(settingsViewFxmlPath);

        DiContainer diContainer = DaggerDiContainer.create();
        Map<Class<?>, Provider<Object>> controllers = diContainer.getControllers();
        FXMLLoader fxmlLoader = new FXMLLoader(settingsViewFxmlResource);
        fxmlLoader.setControllerFactory(type -> controllers.get(type).get());

        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = null;
        if (root != null) {
            scene = new Scene(
                    root,
                    AppConfig.SCREEN_WIDTH,
                    AppConfig.SCREEN_HEIGHT
            );
        }

        Stage stage = new Stage();
        stage.setTitle(AppConfig.WINDOW_TITLE);
        stage.setResizable(AppConfig.WINDOW_RESIZABLE);
        stage.setScene(scene);
        stage.show();

        // Hide the current window
        //((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
