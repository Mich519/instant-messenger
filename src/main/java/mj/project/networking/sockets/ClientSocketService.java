package mj.project.networking.sockets;

import mj.project.gui.controllers.MainViewController;
import mj.project.networking.message.Message;
import mj.project.networking.message.MessageSender;
import mj.project.networking.parsers.MessageParserService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.*;
import java.net.Socket;

@Singleton
public class ClientSocketService {

    private Socket clientSocket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private final MessageParserService messageParserService;

    @Inject
    public ClientSocketService(MessageParserService messageParserService) {
        this.messageParserService = messageParserService;
    }

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        inputStream = new ObjectInputStream(clientSocket.getInputStream());
    }

    public void sendMessage(Message message) {
        try {
            MessageSender messageSender = new MessageSender();
            messageSender.send(message, outputStream);
            Message response = (Message) inputStream.readObject();
            MainViewController.addLog("sending response: " + response);
            messageParserService.parseMessage(response);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopConnection() {
        try {
            inputStream.close();
            outputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
