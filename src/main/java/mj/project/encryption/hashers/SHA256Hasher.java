package mj.project.encryption.hashers;

import org.apache.commons.codec.digest.DigestUtils;

public class SHA256Hasher implements Hasher {

    @Override
    public byte[] hash(byte[] input) {
        return DigestUtils.sha256(input);
    }
}
