package mj.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mj.project.configurations.AppConfiguration;
import mj.project.exceptions.InvalidFxmlPathException;

import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        String mainViewFxmlPath = "/view/MainView.fxml";
        URL mainViewFxmlResource = getClass().getResource(mainViewFxmlPath);
        if (mainViewFxmlResource == null) {
            throw new InvalidFxmlPathException(mainViewFxmlPath);
        }

        Parent root = FXMLLoader.load(mainViewFxmlResource);
        Scene scene = new Scene(
                root,
                AppConfiguration.SCREEN_WIDTH,
                AppConfiguration.SCREEN_HEIGHT
        );

        stage.setTitle(AppConfiguration.WINDOW_TITLE);
        stage.setResizable(AppConfiguration.WINDOW_RESIZABLE);
        stage.setScene(scene);
        stage.show();
    }
}
