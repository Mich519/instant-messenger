package mj.project.gui.events;

import javafx.event.Event;
import javafx.event.EventHandler;

import javax.inject.Inject;
import java.io.IOException;

public class SaveSettingsEventHandler implements EventHandler<Event> {
    @Override
    public void handle(Event event) {

    }

    /*private final AppConfigSerializer appConfigSerializer;

    @Inject
    public SaveSettingsEventHandler(AppConfigSerializer appConfigSerializer) {
        this.appConfigSerializer = appConfigSerializer;
    }

    @Override
    public void handle(Event event) {
        try {
            appConfigSerializer.saveSettingsToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}

