package mj.project.gui.windows;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mj.project.configurations.AppConfig;
import mj.project.exceptions.InvalidFxmlPathException;
import mj.project.gui.controllers.MainViewController;
import mj.project.gui.windows.ed.DaggerMyDiContainer;
import mj.project.gui.windows.ed.MyDiContainer;

import javax.inject.Provider;
import java.net.URL;
import java.util.Map;

public class MainWindow extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        String mainViewFxmlPath = "/views/MainView.fxml";
        URL mainViewFxmlResource = getClass().getResource(mainViewFxmlPath);
        if (mainViewFxmlResource == null) {
            throw new InvalidFxmlPathException(mainViewFxmlPath);
        }

        MyDiContainer myDiContainer = DaggerMyDiContainer.create();
        Map<Class<?>, Provider<Object>> controllers = myDiContainer.getControllers();
        FXMLLoader fxmlLoader = new FXMLLoader(mainViewFxmlResource);
        fxmlLoader.setControllerFactory(type -> controllers.get(type).get());
        Parent root = fxmlLoader.load();




        ((MainViewController)fxmlLoader.getController()).setStage(stage);

        Scene scene = new Scene(
                root,
                AppConfig.getInstance().getScreenWidth(),
                AppConfig.getInstance().getScreenHeight()
        );

        stage.setTitle(AppConfig.getInstance().getWindowTitle());
        stage.setResizable(AppConfig.getInstance().isWindowResizable());
        stage.setScene(scene);
        stage.show();
    }
}
