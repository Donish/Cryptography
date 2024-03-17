package org.example.cipher;

import org.example.impl.cipher_mode_impl.*;
import org.example.impl.padding_impl.ANSIX923Padding;
import org.example.impl.padding_impl.ISO10126Padding;
import org.example.impl.padding_impl.PKCS7Padding;
import org.example.impl.padding_impl.ZerosPadding;
import org.example.interfaces.ICipher;
import org.example.interfaces.ICipherMode;
import org.example.interfaces.IPadding;

import java.util.ArrayList;
import java.util.List;

public class Cipher implements ICipher {

    protected byte[] key;
    protected ICipherMode cipherMode;
    protected IPadding padding;
    protected byte[] IV = null;
    protected List<String> modeArgs = null;

    public enum CipherMode {
        ECB, CBC, PCBC, CFB, OFB, CTR, RandomDelta
    }

    public enum Padding {
        ZEROS, ANSIX923, PKCS7, ISO10126
    }

    public Cipher(byte[] key, CipherMode cipherMode, Padding padding) {
        if (key == null) {
            throw new RuntimeException("passed null key to Cipher");
        }
        this.key = key;

        if (padding == Padding.ZEROS) {
            this.padding = new ZerosPadding();
        } else if (padding == Padding.ANSIX923) {
            this.padding = new ANSIX923Padding();
        } else if (padding == Padding.PKCS7) {
            this.padding = new PKCS7Padding();
        } else if (padding == Padding.ISO10126) {
            this.padding = new ISO10126Padding();
        } else {
            throw new RuntimeException("passed incorrect padding to Cipher");
        }

        if (cipherMode == CipherMode.ECB) {
            this.cipherMode = new ECBMode();
        } else if (cipherMode == CipherMode.CBC) {
            this.cipherMode = new CBCMode();
        } else if (cipherMode == CipherMode.PCBC) {
            this.cipherMode = new PCBCMode();
        } else if (cipherMode == CipherMode.CFB) {
            this.cipherMode = new CFBMode();
        } else if (cipherMode == CipherMode.OFB) {
            this.cipherMode = new OFBMode();
        } else if (cipherMode == CipherMode.CTR) {
            this.cipherMode = new CTRMode();
        } else if (cipherMode == CipherMode.RandomDelta) {
            this.cipherMode = new RandomDeltaMode();
        } else {
            throw new RuntimeException("passed incorrect cipherMode to Cipher");
        }
    }

    public Cipher(byte[] key, CipherMode cipherMode, Padding padding, byte[] IV, List<String> modeArgs) {
        this(key, cipherMode, padding);

        if (IV == null || modeArgs == null) {
            throw new RuntimeException("passed null to Cipher");
        }
        this.IV = IV.clone();
        this.modeArgs = new ArrayList<>(modeArgs);
    }

    @Override
    public byte[] encrypt(byte[] text) {
        return new byte[0];
    }

    public void encrypt(String inputFilename, String outputFilename) {
    }

    @Override
    public byte[] decrypt(byte[] text) {
        return new byte[0];
    }

    public void decrypt(String inputFilename, String outputFilename) {
    }

    @Override
    public List<byte[]> getRKeys(byte[] key) {
        return null;
    }
}
