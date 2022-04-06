package mj.project.windows;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mj.project.configurations.AppConfig;
import mj.project.controllers.MainViewController;
import mj.project.exceptions.InvalidFxmlPathException;

import java.net.URL;

public class MainWindow extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        String mainViewFxmlPath = "/views/MainView.fxml";
        URL mainViewFxmlResource = getClass().getResource(mainViewFxmlPath);
        if (mainViewFxmlResource == null) {
            throw new InvalidFxmlPathException(mainViewFxmlPath);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(mainViewFxmlResource);
        Parent root = fxmlLoader.load();
        ((MainViewController)fxmlLoader.getController()).setStage(stage);

        Scene scene = new Scene(
                root,
                AppConfig.getSCREEN_WIDTH(),
                AppConfig.getSCREEN_HEIGHT()
        );

        stage.setTitle(AppConfig.getWINDOW_TITLE());
        stage.setResizable(AppConfig.isWINDOW_RESIZABLE());
        stage.setScene(scene);
        stage.show();
    }
}
