package mj.project.networking.sockets;

import javafx.concurrent.Task;
import mj.project.gui.controllers.MainViewController;
import mj.project.networking.message.Message;
import mj.project.networking.parsers.MessageParserService;

import javax.inject.Inject;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketListenTask extends Task<Void> {

    private volatile boolean stopped = false;

    private final MessageParserService messageParserService;

    @Inject
    public ServerSocketListenTask(MessageParserService messageParserService) {
        this.messageParserService = messageParserService;
    }

    private int findFreePort() {
        int freePort = -1;
        try (ServerSocket ignored = new ServerSocket(0)) {
            freePort = ignored.getLocalPort();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainViewController.addLog("Listening on port: " + freePort);
        return freePort;
    }

    @Override
    protected Void call() {
        try (ServerSocket serverSocket = new ServerSocket(findFreePort());
             Socket clientSocket = serverSocket.accept();
             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
            out.flush();
            MainViewController.addLog("Connection with client established");
            while (!stopped) {
                Message message = (Message) in.readObject();
                Message response = messageParserService.parseMessage(message);
                out.writeObject(response);
                //s out.println("200");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void stop() {
        stopped = true;
    }
}

