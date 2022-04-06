package mj.project.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocketService {
    private static final ClientSocketService CLIENT_SOCKET_SERVICE = new ClientSocketService();
    private Socket clientSocket;
    private PrintWriter outputStream;
    private BufferedReader inputStream;

    private ClientSocketService() {}

    public static ClientSocketService getInstance() {
        return CLIENT_SOCKET_SERVICE;
    }

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        outputStream = new PrintWriter(clientSocket.getOutputStream(), true);
        inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void sendMessage(String message) {
        try {
            outputStream.println(message);
            String response = inputStream.readLine();
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
