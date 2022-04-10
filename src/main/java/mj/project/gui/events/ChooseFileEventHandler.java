package mj.project.gui.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import mj.project.configurations.AppConfig;

@AllArgsConstructor
public class ChooseFileEventHandler implements EventHandler<Event> {

    /**
     * owner/context of the FileChooser stage
     */
    private final Stage parentStage;

    @Override
    public void handle(Event event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Specific files", AppConfig.getInstance().getAllowedFileExtensions());
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.showOpenDialog(parentStage);
    }
}
