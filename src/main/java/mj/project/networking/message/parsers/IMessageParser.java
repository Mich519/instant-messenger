package mj.project.networking.message.parsers;

import mj.project.networking.message.content.Message;

public interface IMessageParser {
    Message parse(Message message);
}
