package mj.project.sockets;

import mj.project.configurations.AppConfig;
import mj.project.encryption.RSAService;
import mj.project.exceptions.InvalidPacketPrefix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.security.PublicKey;

public class ServerSocketListenTask implements Runnable {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private volatile boolean stopped = false;
    private final int port;
    private final RSAService rsaService;

    public ServerSocketListenTask(int port) {
        this.port = port;
        this.rsaService = new RSAService();
    }

    private void handleMessage(String inputLine) {
        String extractedPrefix = inputLine.substring(0, PacketType.PREFIX_SIZE + 1);
        if (extractedPrefix.equals(PacketType.PUBLIC_KEY.getPacketPrefix())) {
            byte[] keyBytes = inputLine.substring(PacketType.PREFIX_SIZE).getBytes();
            PublicKey recipientPublicKey = rsaService.createPublicKeyFromBytes(keyBytes);
            AppConfig.getInstance().setRecipientPublicKey(recipientPublicKey);
        }
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine = in.readLine();

            StringBuilder message = new StringBuilder();
            while (inputLine != null && !stopped) {
                message.append(inputLine);
                out.println(inputLine);
                inputLine = in.readLine();
            }
            if (message.toString().isEmpty() || message.length() < PacketType.PREFIX_SIZE) {
                throw new InvalidPacketPrefix();
            }
            handleMessage(message.toString());
        } catch (SocketException ignored) {
        } catch (IOException | InvalidPacketPrefix e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                serverSocket.close();
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        try {
            stopped = true;
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

