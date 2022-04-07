package mj.project.sockets;

import mj.project.utils.Utils;
import org.apache.commons.codec.Charsets;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ClientSocketService {
    private static final ClientSocketService CLIENT_SOCKET_SERVICE = new ClientSocketService();
    private Socket clientSocket;
    private OutputStream outputStream;
    private BufferedReader inputStream;

    private ClientSocketService() {}

    public static ClientSocketService getInstance() {
        return CLIENT_SOCKET_SERVICE;
    }

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        outputStream = clientSocket.getOutputStream();
        inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void sendMessage(byte[] message, PacketType packetType) {
        try {
            byte[] messageBuilder = Utils.concatByteArrays(packetType.getPacketPrefix().getBytes(StandardCharsets.US_ASCII), message);
            messageBuilder = Utils.concatByteArrays(messageBuilder, System.lineSeparator().getBytes());
            outputStream.write(messageBuilder);
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
