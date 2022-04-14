package mj.project.networking.services;

import javafx.application.Platform;
import javafx.concurrent.Task;
import mj.project.gui.controllers.Controllers;
import mj.project.networking.message.Message;
import mj.project.networking.message.MessageReader;

import javax.inject.Inject;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketListenTask extends Task<Void> {
    private final MessageReader messageReader;
    private volatile boolean stopped = false;

    @Inject
    public ServerSocketListenTask(MessageReader messageReader) {
        this.messageReader = messageReader;
    }

    private void updateServerPortStatusBar(int localPort) {
        Platform.runLater(() -> Controllers.getMainViewController().updateStatus(String.valueOf(localPort)));
    }

    private void readMessage(ObjectInput in) {
        try {
            Object o = in.readObject();
            Message m = (Message) o;
            messageReader.read(m);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Void call() {
        try (ServerSocket serverSocket = new ServerSocket(0);
             Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())
        ) {
            updateServerPortStatusBar(serverSocket.getLocalPort());
            while (!stopped) {
                readMessage(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void stop() {
        stopped = true;
    }
}

