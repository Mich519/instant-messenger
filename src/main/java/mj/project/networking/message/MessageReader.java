package mj.project.networking.message;

import javafx.application.Platform;
import mj.project.configurations.AppConfig;
import mj.project.gui.controllers.Controllers;
import mj.project.encryption.encryptors.factories.RSAPublicKeyFactory;

import java.security.PublicKey;

public class MessageReader {

    public void read(Message message) {
        MessageType messageType = message.getMessageType();

        if (messageType.equals(MessageType.PUBLIC_KEY)) {
            RSAPublicKeyFactory rsaPublicKeyFactory = new RSAPublicKeyFactory();
            PublicKey recipientPublicKey = rsaPublicKeyFactory.create(message.getContent());
            AppConfig.getInstance().setRecipientPublicKey(recipientPublicKey);

        } else if (messageType.equals(MessageType.TEXT)) {
            Platform.runLater(() -> Controllers.getMainViewController().addMessage(message));
        }
    }
}
