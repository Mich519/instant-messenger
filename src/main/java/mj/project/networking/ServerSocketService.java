package mj.project.networking;

import javax.inject.Inject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSocketService {
    private final ServerSocketListenTask serverSocketListenTask;

    @Inject
    public ServerSocketService(ServerSocketListenTask serverSocketListenTask) {
        this.serverSocketListenTask = serverSocketListenTask;
    }

    public void startListening() {
        ExecutorService listenTaskExecutor = Executors.newSingleThreadExecutor();
        listenTaskExecutor.submit(serverSocketListenTask);
    }

    public void stopListening() {
        serverSocketListenTask.stop();
    }
}
