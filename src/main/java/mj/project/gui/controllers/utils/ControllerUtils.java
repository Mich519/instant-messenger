package mj.project.gui.controllers.utils;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ControllerUtils {

    public static void addLabelToPane(String text, Pane parentPane) {
        Label messageLabel = new Label(text);
        parentPane.getChildren().add(messageLabel);
    }
}
