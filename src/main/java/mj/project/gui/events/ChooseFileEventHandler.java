package mj.project.gui.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import mj.project.configurations.AppConfig;

import javax.inject.Inject;

public class ChooseFileEventHandler implements EventHandler<Event> {

    public ChooseFileEventHandler() {
    }

    @Override
    public void handle(Event event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Specific files", AppConfig.ALLOWED_FILE_EXTENSIONS);
        fileChooser.getExtensionFilters().add(extensionFilter);
        //fileChooser.showOpenDialog(parentStage);
    }
}
