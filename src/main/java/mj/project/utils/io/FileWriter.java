package mj.project.utils.io;

import java.io.Serializable;

public interface FileWriter<T extends Serializable> {

    void write(T o, String path);
}
