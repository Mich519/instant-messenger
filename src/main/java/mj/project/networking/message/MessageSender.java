package mj.project.networking.message;

import mj.project.configurations.AppConfig;

import java.io.IOException;
import java.io.ObjectOutput;

public class MessageSender {
    public void send(Message message, ObjectOutput out) {
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
