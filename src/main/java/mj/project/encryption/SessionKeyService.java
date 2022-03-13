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
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return cipher.doFinal(sessionKey.getEncoded());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public SecretKey getDecoded(byte[] encodedSessionKey, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] decodedSessionKey = cipher.doFinal(encodedSessionKey);
            return new SecretKeySpec(decodedSessionKey, "AES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
