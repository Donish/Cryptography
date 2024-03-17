package org.example.DES;

import org.example.cipher.Cipher;
import org.example.feistel_network.FeistelNetwork;
import org.example.impl.conversion_impl.DESConversion;
import org.example.impl.round_key_impl.DESKeyGenerator;

public final class DES extends Cipher {

    private final FeistelNetwork feistelNetwork;

    public DES(byte[] key) {
        super(key, CipherMode.ECB, Padding.ZEROS);
        this.modeArgs.add("64");
        this.feistelNetwork = new FeistelNetwork(key, new DESKeyGenerator(), new DESConversion(), 16);
    }

    @Override
    public byte[] encrypt(byte[] text) {
        return cipherMode.encryptWithMode(text, this.IV, this.padding, this.modeArgs, this.feistelNetwork);
    }

    @Override
    public byte[] decrypt(byte[] cipherText) {
        return cipherMode.decryptWithMode(cipherText, this.IV, this.padding, this.modeArgs, this.feistelNetwork);
    }
}
