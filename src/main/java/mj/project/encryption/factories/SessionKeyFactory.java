package mj.project.encryption.factories;

import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class SessionKeyFactory implements KeyFactory<SecretKeySpec> {

    private static final String ALGORITHM = "AES";

    @Override
    public SecretKeySpec create(byte[] randomBytes) {
        SecureRandom random = new SecureRandom();
        random.nextBytes(randomBytes);
        return new SecretKeySpec(randomBytes, ALGORITHM);
    }
}
