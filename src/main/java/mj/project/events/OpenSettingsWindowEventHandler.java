package mj.project.events;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mj.project.configurations.AppConfig;

import java.io.IOException;
import java.net.URL;


// TODO: allow creating only one instance of the window
// TODO: make main window inaccessible when settings window is opened

public class OpenSettingsWindowEventHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        String settingsViewFxmlPath = "/views/SettingsView.fxml";
        URL settingsViewFxmlResource = getClass().getResource(settingsViewFxmlPath);

        Parent root = null;
        if(settingsViewFxmlResource != null) {
            try {
                root = FXMLLoader.load(settingsViewFxmlResource);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Scene scene = null;
        if(root != null) {
            scene = new Scene(
                    root,
                    AppConfig.getInstance().getScreenWidth(),
                    AppConfig.getInstance().getScreenHeight()
            );
        }

        Stage stage = new Stage();
        stage.setTitle(AppConfig.getInstance().getWindowTitle());
        stage.setResizable(AppConfig.getInstance().isWindowResizable());
        stage.setScene(scene);
        stage.show();

        // Hide the current window
        //((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
