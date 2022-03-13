package mj.project.encryption;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

public class SessionKeyService {

    public SecretKey createSessionKey() {
        byte[] randomBytes = new byte[32];
        SecureRandom random = new SecureRandom();
        random.nextBytes(randomBytes);
        return new SecretKeySpec(randomBytes, "AES");
    }

    public byte[] getEncoded(SecretKey sessionKey, PrivateKey privateKey) {
        RSAService rsaService = new RSAService();
        return rsaService.encode(sessionKey.getEncoded(), privateKey);
    }

    public SecretKey getDecoded(byte[] encodedSessionKey, PublicKey publicKey) {
        RSAService rsaService = new RSAService();
        byte[] decodedSessionKey = rsaService.decode(encodedSessionKey, publicKey);
        return new SecretKeySpec(decodedSessionKey, "AES");
    }
}
