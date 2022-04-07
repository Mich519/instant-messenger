package mj.project.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Utils {

    public static byte[] concatByteArrays(byte[] a, byte[] b) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            outputStream.write(a);
            outputStream.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}
