package mj.project.gui.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.AllArgsConstructor;
import mj.project.configurations.AppConfig;
import mj.project.encryption.encryptors.RSAService;
import mj.project.exceptions.PortRangeException;
import mj.project.networking.ClientSocketService;
import mj.project.networking.message.Message;
import mj.project.networking.message.MessageType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;

@AllArgsConstructor
public class ConnectEventHandler implements EventHandler<Event> {

    private final TextField portTextField;
    private final Label statusLabel;

    @Override
    public void handle(Event event) {
        try {
            int targetPort = Integer.parseInt(portTextField.getText());
            if (targetPort < 0 || targetPort > 65535) {
                throw new PortRangeException();
            }
            // connect with a server
            ClientSocketService.getInstance().startConnection(AppConfig.getInstance().getTargetIp(), targetPort);
            RSAService rsaService = new RSAService();
            KeyPair keyPair = rsaService.createKeyPair();
            byte[] encodedPublicKey = keyPair.getPublic().getEncoded();
            Message message = Message.builder()
                    .senderName(AppConfig.getInstance().getMyNickName().getBytes(StandardCharsets.UTF_8))
                    .content(encodedPublicKey)
                    .messageType(MessageType.PUBLIC_KEY)
                    .build();

            ClientSocketService.getInstance().sendMessage(message);

        } catch (NumberFormatException e) {
            statusLabel.setText("Error: Port number contains invalid characters");
        } catch (PortRangeException e) {
            statusLabel.setText("Error: Port number is out of range");
        } catch (IOException e) {
            statusLabel.setText("Error: Unable to connect with a host");
        }
    }
}
