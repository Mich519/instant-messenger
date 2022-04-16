package mj.project.utils.io;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import mj.project.configurations.AppConfig;
import mj.project.exceptions.ObjectWriteException;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ObjectFileIO {

    private final ObjectMapper objectMapper;

    @Inject
    public ObjectFileIO(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void writeObject(Object o, String path) {
        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
            com.fasterxml.jackson.databind.ObjectWriter writer = objectMapper.writer(prettyPrinter);
            writer.writeValue(file, o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T readObject(Class<T> clazz, String path) {
        try {
            File file = new File(path);
            return (T) objectMapper.readValue(file, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ObjectWriteException();
    }
}
