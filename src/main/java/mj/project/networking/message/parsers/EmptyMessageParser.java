package mj.project.networking.message.parsers;

import mj.project.networking.message.Message;

public class EmptyMessageParser implements MessageParser{
    @Override
    public Message parse(Message message) {
        return message;
    }
}
