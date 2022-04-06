import mj.project.sockets.ClientSocketService;
import mj.project.sockets.ServerSocketService;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class SocketTests {

    @Test
    public void startStopServerSocketListenTask() throws InterruptedException {
        ServerSocketService serverSocketService = new ServerSocketService();
        serverSocketService.startListening(8080);
        serverSocketService.stopListening();
    }

    @Test
    public void connectionBetweenClientsIsEstablished() throws IOException {
        ServerSocketService serverSocketService = new ServerSocketService();
        serverSocketService.startListening(8080);

        ClientSocketService clientSocketService = new ClientSocketService();
        clientSocketService.startConnection("127.0.0.1", 8080);
        clientSocketService.sendMessage("hello server!");
        clientSocketService.stopConnection();
    }
}
