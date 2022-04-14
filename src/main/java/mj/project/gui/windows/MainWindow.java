package mj.project.gui.windows;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mj.project.configurations.AppConfig;
import mj.project.exceptions.InvalidFxmlPathException;
import mj.project.gui.controllers.MainViewController;
import mj.project.modules.DaggerDiContainer;
import mj.project.modules.DiContainer;

import javax.inject.Inject;
import javax.inject.Provider;
import java.net.URL;
import java.util.Map;

public class MainWindow extends Application {

    @Inject
    public MainWindow() {

    }

    @Override
    public void start(Stage stage) throws Exception {
        String mainViewFxmlPath = "/views/MainView.fxml";
        URL mainViewFxmlResource = getClass().getResource(mainViewFxmlPath);
        if (mainViewFxmlResource == null) {
            throw new InvalidFxmlPathException(mainViewFxmlPath);
        }

        DiContainer diContainer = DaggerDiContainer.create();
        Map<Class<?>, Provider<Object>> controllers = diContainer.getControllers();
        FXMLLoader fxmlLoader = new FXMLLoader(mainViewFxmlResource);
        fxmlLoader.setControllerFactory(type -> controllers.get(type).get());
        Parent root = fxmlLoader.load();

        ((MainViewController)fxmlLoader.getController()).setStage(stage);

        Scene scene = new Scene(
                root,
                AppConfig.SCREEN_WIDTH,
                AppConfig.SCREEN_HEIGHT
        );

        stage.setTitle(AppConfig.WINDOW_TITLE);
        stage.setResizable(AppConfig.WINDOW_RESIZABLE);
        stage.setScene(scene);
        stage.show();
    }
}
