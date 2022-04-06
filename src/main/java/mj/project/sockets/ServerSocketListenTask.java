package mj.project.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerSocketListenTask implements Runnable {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private volatile boolean stopped = false;
    private final int port;

    public ServerSocketListenTask(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        while (!stopped) {
            try {
                serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null && !stopped) {
                    out.println(inputLine);

                }

            } catch (SocketException ignored) {
            } catch (IOException e) {
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

