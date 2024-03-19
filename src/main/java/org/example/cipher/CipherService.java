package org.example.cipher;

import org.example.impl.cipher_mode_impl.*;
import org.example.impl.padding_impl.ANSIX923Padding;
import org.example.impl.padding_impl.ISO10126Padding;
import org.example.impl.padding_impl.PKCS7Padding;
import org.example.impl.padding_impl.ZerosPadding;
import org.example.interfaces.IAlgorithm;
import org.example.interfaces.ICipherMode;
import org.example.interfaces.IPadding;

import java.util.ArrayList;
import java.util.List;

public class CipherService {

    protected byte[] key;
    protected ICipherMode cipherMode;
    protected IPadding padding;
    protected byte[] IV = null;
    protected List<String> modeArgs = null;
    protected IAlgorithm algorithm;

    public enum CipherMode {
        ECB, CBC, PCBC, CFB, OFB, CTR, RandomDelta
    }

    public enum Padding {
        ZEROS, ANSIX923, PKCS7, ISO10126
    }

    public CipherService(byte[] key, CipherMode cipherMode, Padding padding, IAlgorithm algorithm) {
        if (key == null) {
            throw new RuntimeException("passed null key to CipherService");
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
            throw new RuntimeException("passed incorrect padding to CipherService");
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
            throw new RuntimeException("passed incorrect cipherMode to CipherService");
        }

        if (algorithm == null) {
            throw new RuntimeException("passed null algorithm to CipherService");
        }
        this.algorithm = algorithm;
    }

    public CipherService(byte[] key,
                         CipherMode cipherMode,
                         Padding padding,
                         IAlgorithm algorithm,
                         byte[] IV,
                         List<String> modeArgs) {
        this(key, cipherMode, padding, algorithm);

        if (IV == null || modeArgs == null) {
            throw new RuntimeException("passed null to Cipher");
        }
        this.IV = IV.clone();
        this.modeArgs = new ArrayList<>(modeArgs);
    }

    public byte[] encrypt(byte[] text) {
        return new byte[0];
    }

    public void encrypt(String inputFilename, String outputFilename) {
    }


    public byte[] decrypt(byte[] text) {
        return new byte[0];
    }

    public void decrypt(String inputFilename, String outputFilename) {
    }

}
