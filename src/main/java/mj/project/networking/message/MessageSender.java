package mj.project.networking.message;


import java.io.IOException;
import java.io.ObjectOutputStream;

public class MessageSender {
    public void send(Message message, ObjectOutputStream out) {
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
