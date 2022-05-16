package mj.project.gui.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mj.project.encryption.services.RSAService;
import mj.project.exceptions.PortRangeException;
import mj.project.networking.data.NetworkPropertiesStorage;
import mj.project.networking.sockets.ClientSocketService;

public class ConnectEventHandler implements EventHandler<Event> {

    private final TextField portTextField;
    private final Label statusLabel;
    private final RSAService rsaService;
    private final NetworkPropertiesStorage networkPropertiesStorage;
    private final ClientSocketService clientSocketService;

    public ConnectEventHandler(TextField portTextField, Label statusLabel, RSAService rsaService, NetworkPropertiesStorage networkPropertiesStorage, ClientSocketService clientSocketService) {
        this.portTextField = portTextField;
        this.statusLabel = statusLabel;
        this.rsaService = rsaService;
        this.networkPropertiesStorage = networkPropertiesStorage;
        this.clientSocketService = clientSocketService;
    }

    @Override
    public void handle(Event event) {
        try {
            int targetPort = Integer.parseInt(portTextField.getText());
            if (targetPort < 0 || targetPort > 65535) {
                throw new PortRangeException();
            }
            // connect with a server
            /*clientSocketService.startConnection(networkPropertiesStorage.getTargetIp(), targetPort);
            KeyPair keyPair = rsaService.createKeyPair();
            byte[] encodedPublicKey = keyPair.getPublic().getEncoded();
            appConfig.setThisKeyPair(keyPair);

            Message message = Message.builder()
                    .senderName(appConfig.getMyNickName().getBytes(StandardCharsets.UTF_8))
                    .content(encodedPublicKey)
                    .messageType(MessageType.PUBLIC_KEY)
                    .build();

            ClientSocketService.getInstance().sendMessage(message);*/

        } catch (NumberFormatException e) {
            statusLabel.setText("Error: Port number contains invalid characters");
        } catch (PortRangeException e) {
            statusLabel.setText("Error: Port number is out of range");
        }
    }
}
