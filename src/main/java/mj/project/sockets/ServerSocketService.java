package mj.project.sockets;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSocketService {

    private static final ServerSocketService SERVER_SOCKET_SERVICE = new ServerSocketService();
    private ServerSocketListenTask serverSocketListenTask;

    private ServerSocketService() {}

    public static ServerSocketService getInstance() {
        return SERVER_SOCKET_SERVICE;
    }

    public void startListening(int port) {
        ExecutorService listenTaskExecutor = Executors.newSingleThreadExecutor();
        serverSocketListenTask = new ServerSocketListenTask(port);
        listenTaskExecutor.submit(serverSocketListenTask);
    }

    public void stopListening() {
        serverSocketListenTask.stop();
    }
}
