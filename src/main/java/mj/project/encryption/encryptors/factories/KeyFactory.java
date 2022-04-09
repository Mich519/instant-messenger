package mj.project.encryption.encryptors.factories;

import java.security.Key;

public interface KeyFactory<T extends Key> {
    T create(byte[] input);
}
