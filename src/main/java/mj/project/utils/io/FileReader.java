package mj.project.utils.io;

import java.io.Serializable;

public interface FileReader<T extends Serializable> {

    T read(String path);
}
