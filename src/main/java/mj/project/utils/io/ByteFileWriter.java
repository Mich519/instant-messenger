package mj.project.utils.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ByteFileWriter implements FileWriter<byte[]> {

    @Override
    public void write(byte[] bytes, String path) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
