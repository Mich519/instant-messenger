package mj.project.networking;

import mj.project.configurations.AppConfig;
import mj.project.encryption.encryptors.RSAService;
import mj.project.encryption.encryptors.factories.RSAPublicKeyFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PublicKey;

public class ServerSocketListenTask implements Runnable {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private ObjectInputStream in;
    private volatile boolean stopped = false;
    private final int port;

    private void openStreams() {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeStreams() {
        try {
            clientSocket.close();
            serverSocket.close();
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleMessage(String inputLine) {
        /*String extractedPrefix = inputLine.substring(0, PacketType.PREFIX_SIZE + 1);
        if (extractedPrefix.equals(PacketType.PUBLIC_KEY.getPacketPrefix())) {
            byte[] keyBytes = inputLine.substring(PacketType.PREFIX_SIZE).getBytes();
            PublicKey recipientPublicKey = rsaService.createPublicKeyFromBytes(keyBytes);
            AppConfig.getInstance().setRecipientPublicKey(recipientPublicKey);
        }*/
    }

    private void readMessage() {
        try {
            Object o = in.readObject();
            Message m = (Message)o;
            if(m.getMessageType().equals(MessageType.PUBLIC_KEY)) {
                RSAPublicKeyFactory rsaPublicKeyFactory = new RSAPublicKeyFactory();
                PublicKey recipientPublicKey = rsaPublicKeyFactory.create(m.getContent());
                AppConfig.getInstance().setRecipientPublicKey(recipientPublicKey);
            } else if (m.getMessageType().equals(MessageType.TEXT)) {

            }

            System.out.println(m);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        /*try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public ServerSocketListenTask(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        openStreams();
        while (!stopped) {
            readMessage();
        }
        closeStreams();
    }

    public void stop() {
        stopped = true;
        closeStreams();
    }
}

