package mj.project.encryption.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import mj.project.encryption.block_ciphers.BlockCipher;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.Serializable;
import java.security.KeyPair;
import java.security.PublicKey;

@Getter
@Setter
@Singleton
public class KeyStorage implements Serializable {

    @Inject
    public KeyStorage() {}

    @JsonIgnore private PublicKey recipientPublicKey = null;
    @JsonIgnore private SecretKey sessionKey = null;
    @JsonIgnore private byte[] localKey = null;
    private KeyPair thisKeyPair = null;
    private BlockCipher blockCipher = BlockCipher.CBC;
}
