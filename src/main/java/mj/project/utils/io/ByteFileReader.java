package mj.project.utils.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ByteFileReader implements FileReader<byte[]> {

    @Override
    public byte[] read(String path) {
        try {
            return Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException(); //todo: change exception
    }
}
