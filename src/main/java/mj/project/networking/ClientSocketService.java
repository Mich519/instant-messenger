package mj.project.networking;

import mj.project.networking.message.Message;
import mj.project.networking.message.MessageSender;

import java.io.*;
import java.net.Socket;

public class ClientSocketService {
    private static final ClientSocketService CLIENT_SOCKET_SERVICE = new ClientSocketService();
    private Socket clientSocket;
    private ObjectOutputStream outputStream;
    private BufferedReader inputStream;

    private ClientSocketService() {}

    public static ClientSocketService getInstance() {
        return CLIENT_SOCKET_SERVICE;
    }

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
