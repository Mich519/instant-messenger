package mj.project.networking;

import mj.project.utils.Utils;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

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
        try {
            outputStream.writeObject(message);
            outputStream.flush();
            //String response = inputStream.readLine();
        } catch (IOException e) {
            e.printStackTrace();
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
