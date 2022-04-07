package mj.project.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mj.project.configurations.AppConfig;
import mj.project.encryption.RSAService;
import mj.project.exceptions.PortRangeException;
import mj.project.sockets.ClientSocketService;

import java.io.IOException;

public class ConnectEventHandler implements EventHandler<Event> {

    private final TextField portTextField;
    private final Label statusLabel;

    public ConnectEventHandler(TextField portTextField, Label statusLabel) {
        this.portTextField = portTextField;
        this.statusLabel = statusLabel;
    }

    @Override
    public void handle(Event event) {
        try {
            int targetPort = Integer.parseInt(portTextField.getText());
            if (targetPort < 0 || targetPort > 65535) {
                throw new PortRangeException();
            }
            // connect with a host
            ClientSocketService.getInstance().startConnection(AppConfig.getInstance().getTargetIp(), targetPort);

            // send public key to the reciever
            RSAService rsaService = new RSAService();


            rsaService.createKeyPair(); // todo: change this
           // rsaService.
            //ClientSocketService.getInstance().sendMessage();



        } catch (NumberFormatException e) {
            statusLabel.setText("Error: Port number contains invalid characters");
        } catch (PortRangeException e) {
            statusLabel.setText("Error: Port number is out of range");
        } catch (IOException e) {
            statusLabel.setText("Error: Unable to connect with a host");
        }
    }
}
