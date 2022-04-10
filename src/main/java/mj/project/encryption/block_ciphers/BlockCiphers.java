package mj.project.encryption.block_ciphers;

public class BlockCiphers {

    public static BlockCipher newCBCBlockCipher() {
        return new CBCBlockCipher();
    }

    public static BlockCipher newECBBlockCipher() {
        return new ECBBlockCipher();
    }
}
