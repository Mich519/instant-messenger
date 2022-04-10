package mj.project.gui.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import mj.project.configurations.AppConfigSerializer;

import java.io.IOException;

public class SaveSettingsEventHandler implements EventHandler<Event> {

    private final AppConfigSerializer appConfigSerializer;

    public SaveSettingsEventHandler() {
        this.appConfigSerializer = new AppConfigSerializer();
    }

    @Override
    public void handle(Event event) {
        try {
            appConfigSerializer.saveSettingsToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

