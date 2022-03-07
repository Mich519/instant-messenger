package mj.project.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mj.project.configurations.AppConfiguration;

public class ChooseFileEventHandler implements EventHandler<Event> {

    /**
     * owner/context of the FileChooser stage
     */
    private final Stage parentStage;

    public ChooseFileEventHandler(Stage parentStage) {
        this.parentStage = parentStage;
    }

    @Override
    public void handle(Event event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Specific files", AppConfiguration.ALLOWED_FILE_EXTENSIONS);
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.showOpenDialog(parentStage);
    }
}
