package mj.project.exceptions;

public class InvalidFxmlPathException extends Exception {

    public InvalidFxmlPathException() {}

    public InvalidFxmlPathException(String fxmlPath) {
        super("Fxml resource path " + fxmlPath + " is invalid");
    }
}
