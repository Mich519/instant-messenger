package mj.project.networking.services;

import mj.project.networking.message.Message;
import mj.project.networking.message.MessageSender;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.*;
import java.net.Socket;

@Singleton
public class ClientSocketService {

    private Socket clientSocket;
    private ObjectOutputStream outputStream;
    private BufferedReader inputStream;

    @Inject
    public ClientSocketService() {}

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void sendMessage(Message message) {
        MessageSender messageSender = new MessageSender();
        messageSender.send(message, outputStream);
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
