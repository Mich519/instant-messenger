package mj.project.networking.services;

import javafx.concurrent.Task;
import mj.project.gui.controllers.MainViewController;
import mj.project.networking.message.Message;
import mj.project.networking.message.MessageParser;

import javax.inject.Inject;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketListenTask extends Task<Void> {

    private volatile boolean stopped = false;

    private final MessageService messageService;

    @Inject
    public ServerSocketListenTask(MessageService messageService) {
        this.messageService = messageService;
    }

    private void readMessage(ObjectInput in) {
        try {
            Object o = in.readObject();
            Message m = (Message) o;
            messageService.parseMessage(m);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int assignFreePort() {
        int freePort = -1;
        try (ServerSocket ignored = new ServerSocket(0)) {
            freePort = ignored.getLocalPort();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainViewController.addLog("Listening on port: " + freePort );
        return freePort;
    }


    @Override
    protected Void call() {
        try (ServerSocket serverSocket = new ServerSocket(assignFreePort() );
             Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream()))
        {
            MainViewController.addLog("Connection with client established" );
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

