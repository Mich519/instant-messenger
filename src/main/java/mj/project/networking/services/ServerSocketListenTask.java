package mj.project.networking.services;

import javafx.concurrent.Task;
import mj.project.gui.controllers.MainViewController;
import mj.project.networking.message.content.Message;
import mj.project.networking.message.parsers.MessageParserService;

import javax.inject.Inject;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketListenTask extends Task<Void> {

    private volatile boolean stopped = false;

    private final MessageService messageService;

    private final MessageParserService messageParserService;


    @Inject
    public ServerSocketListenTask(MessageService messageService, MessageParserService messageParserService) {
        this.messageService = messageService;
        this.messageParserService = messageParserService;
    }

    private int assignFreePort() {
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
        try (ServerSocket serverSocket = new ServerSocket(assignFreePort());
             Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
            MainViewController.addLog("Connection with client established");
            while (!stopped) {
                Message message = (Message) in.readObject();
                Message response = messageParserService.parseMessage(message);
                out.print(response); // todo might not work - check
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

