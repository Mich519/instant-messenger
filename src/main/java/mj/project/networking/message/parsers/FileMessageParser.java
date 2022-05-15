package mj.project.networking.message.parsers;

import mj.project.configurations.AppConfig;
import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.SessionKeyService;
import mj.project.networking.message.MessageFactory;
import mj.project.networking.message.MessageType;
import mj.project.networking.message.Message;

import javax.inject.Inject;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FileMessageParser implements MessageParser {

    private final SessionKeyService sessionKeyService;
    private final KeyStorage keyStorage;

    private final MessageFactory messageFactory;

    @Inject
    public FileMessageParser(SessionKeyService sessionKeyService, KeyStorage keyStorage, MessageFactory messageFactory) {
        this.sessionKeyService = sessionKeyService;
        this.keyStorage = keyStorage;
        this.messageFactory = messageFactory;
    }

    @Override
    public Message parse(Message message) {
        try (FileOutputStream output = new FileOutputStream(AppConfig.TEMP_FILE_DIR_PATH + "\\filename", true)) {
            message.getContent().stream()
                    .map(packetBytes -> sessionKeyService.decrypt(packetBytes, keyStorage.getSessionKey()))
                    .forEach(packetBytesDecrypted -> {
                        try {
                            output.write(packetBytesDecrypted);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messageFactory.createMessage(List.of(), MessageType.EMPTY);
    }
}