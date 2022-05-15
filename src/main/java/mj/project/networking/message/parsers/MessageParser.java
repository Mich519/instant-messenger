package mj.project.networking.message.parsers;

import mj.project.networking.message.Message;

public interface MessageParser {
    Message parse(Message message);
}
