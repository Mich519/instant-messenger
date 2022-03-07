package mj.project.events;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mj.project.configurations.AppConfiguration;

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
                    AppConfiguration.SCREEN_WIDTH,
                    AppConfiguration.SCREEN_HEIGHT
            );
        }

        Stage stage = new Stage();
        stage.setTitle(AppConfiguration.WINDOW_TITLE);
        stage.setResizable(AppConfiguration.WINDOW_RESIZABLE);
        stage.setScene(scene);
        stage.show();

        // Hide the current window
        //((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
