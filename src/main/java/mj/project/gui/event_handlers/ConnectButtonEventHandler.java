package mj.project.gui.event_handlers;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.SessionKeyService;
import mj.project.exceptions.KeyPairNotFound;
import mj.project.exceptions.PortRangeException;
import mj.project.exceptions.RecipientKeyNotFound;
import mj.project.gui.controllers.MainViewController;
import mj.project.networking.data.NetworkPropertiesStorage;
import mj.project.networking.message.Message;
import mj.project.networking.message.MessageService;
import mj.project.networking.message.MessageType;
import mj.project.networking.sockets.ClientSocketService;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.PublicKey;
import java.util.List;

public class ConnectButtonEventHandler implements EventHandler<Event> {

    private final KeyStorage keyStorage;
    private final TextField recipientPortTextField;
    private final ClientSocketService clientSocketService;
    private final NetworkPropertiesStorage networkPropertiesStorage;
    private final MessageService messageService;
    private final SessionKeyService sessionKeyService;

    public ConnectButtonEventHandler(KeyStorage keyStorage, TextField recipientPortTextField, ClientSocketService clientSocketService, NetworkPropertiesStorage networkPropertiesStorage, MessageService messageService, SessionKeyService sessionKeyService) {
        this.keyStorage = keyStorage;
        this.recipientPortTextField = recipientPortTextField;
        this.clientSocketService = clientSocketService;
        this.networkPropertiesStorage = networkPropertiesStorage;
        this.messageService = messageService;
        this.sessionKeyService = sessionKeyService;
    }

    @Override
    public void handle(Event event) {
        try {
            if (keyStorage.getThisKeyPair() == null) {

                MainViewController.addLog("Your key pair not found. Please Generate new key pair.");
                throw new KeyPairNotFound();
            }
            int recipientPort = Integer.parseInt(recipientPortTextField.getText());
            if (recipientPort < 0 || recipientPort > 65535) {
                throw new PortRangeException();
            }

            MainViewController.addLog("Connecting with a host ...");

            // connect with a server
            clientSocketService.startConnection(networkPropertiesStorage.getTargetIp(), Integer.parseInt(recipientPortTextField.getText()));
            byte[] encodedPublicKey = keyStorage.getThisKeyPair().getPublic().getEncoded();
            Message publicKeyMessageSimpleContent = messageService.createMessage(List.of(encodedPublicKey), MessageType.PUBLIC_KEY);
            clientSocketService.sendMessage(publicKeyMessageSimpleContent);
            MainViewController.addLog("Public key has been sent to the user");

            // now send session key
            if (keyStorage.getSessionKey() == null) {
                PublicKey recipientPublicKey = keyStorage.getRecipientPublicKey();
                if (recipientPublicKey == null) {
                    MainViewController.addLog("Recipient key not found");
                    throw new RecipientKeyNotFound();
                }
                SecretKey sessionKey = sessionKeyService.createSessionKey();
                byte[] encryptedSessionKey = sessionKeyService.encryptSessionKey(sessionKey, recipientPublicKey);
                Message sessionKeyMessageSimpleContent = messageService.createMessage(List.of(encryptedSessionKey), MessageType.SESSION_KEY);
                keyStorage.setSessionKey(sessionKey);
                clientSocketService.sendMessage(sessionKeyMessageSimpleContent);
                MainViewController.addLog("Session key has been sent to the user");
            }
        } catch (NumberFormatException e) {
            MainViewController.addLog("Error: Port number contains invalid characters");
        } catch (PortRangeException e) {
            MainViewController.addLog("Error: Port number is out of range");
        } catch (IOException e) {
            MainViewController.addLog("Error: Connection failed");
            e.printStackTrace();
        }
    }
}
