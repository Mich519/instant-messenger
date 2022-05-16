package mj.project.gui.event_handlers;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import mj.project.configurations.AppConfig;
import mj.project.encryption.data.KeyStorage;
import mj.project.encryption.services.SessionKeyService;
import mj.project.networking.message.Message;
import mj.project.networking.message.MessageService;
import mj.project.networking.message.MessageType;
import mj.project.networking.sockets.ClientSocketService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AttachFileButtonEventHandler implements EventHandler<Event> {

    private final Pane logsContainer;
    private final SessionKeyService sessionKeyService;
    private final KeyStorage keyStorage;
    private final MessageService messageService;
    private final ClientSocketService clientSocketService;

    public AttachFileButtonEventHandler(Pane logsContainer, SessionKeyService sessionKeyService, KeyStorage keyStorage, MessageService messageService, ClientSocketService clientSocketService) {
        this.logsContainer = logsContainer;
        this.sessionKeyService = sessionKeyService;
        this.keyStorage = keyStorage;
        this.messageService = messageService;
        this.clientSocketService = clientSocketService;
    }

    @Override
    public void handle(Event event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Specific files", AppConfig.ALLOWED_FILE_EXTENSIONS);
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showOpenDialog(logsContainer.getScene().getWindow());
        byte[] fileBytes = null;
        try {
            fileBytes = Files.readAllBytes(selectedFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fileBytes == null) {
            throw new RuntimeException();
        }
        List<byte[]> packetList = new ArrayList<>();
        for (int i = 0; i < fileBytes.length; i += AppConfig.MAX_BYTES_PER_PACKET) {
            byte[] packet = Arrays.copyOfRange(fileBytes, i, Math.min(fileBytes.length, i + AppConfig.MAX_BYTES_PER_PACKET));
            byte[] encryptedPacket = sessionKeyService.encrypt(packet, keyStorage.getSessionKey());
            packetList.add(encryptedPacket);
        }
        Message message = messageService.createMessage(packetList, MessageType.FILE);
        clientSocketService.sendMessage(message);
    }
}
