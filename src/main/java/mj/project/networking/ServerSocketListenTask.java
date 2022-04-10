package mj.project.networking;

import javafx.application.Platform;
import javafx.concurrent.Task;
import mj.project.gui.controllers.Controllers;
import mj.project.networking.message.Message;
import mj.project.networking.message.MessageReader;

import javax.inject.Inject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketListenTask extends Task<Void> {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private ObjectInputStream in;
    private final MessageReader messageReader;
    private volatile boolean stopped = false;

    @Inject
    public ServerSocketListenTask(MessageReader messageReader) {
        this.messageReader = messageReader;
    }

    private void openStreams() {
        try {
            serverSocket = new ServerSocket(0);
            Platform.runLater(() -> {
                Controllers.getMainViewController().updateStatus(String.valueOf(serverSocket.getLocalPort()));
            });

            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeStreams() {
        try {
            if(clientSocket != null)
                clientSocket.close();
            if(serverSocket != null)
                serverSocket.close();
            if(out != null)
                out.close();
            if(in != null)
                in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessage() {
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
        openStreams();
        while (!stopped) {
            readMessage();
        }
        closeStreams();
        return null;
    }

    public void stop() {
        stopped = true;
        closeStreams();
    }
}

