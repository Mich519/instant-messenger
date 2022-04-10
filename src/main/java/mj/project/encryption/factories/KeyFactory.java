package mj.project.encryption.factories;

import java.security.Key;

public interface KeyFactory<T extends Key> {
    T create(byte[] input);
}
