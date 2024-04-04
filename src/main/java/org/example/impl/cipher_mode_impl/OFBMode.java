package org.example.impl.cipher_mode_impl;

import org.example.interfaces.IAlgorithm;
import org.example.interfaces.ICipherMode;

import java.util.List;

public class OFBMode implements ICipherMode {

    @Override
    public byte[] encryptWithMode(byte[] text, byte[] IV, List<String> parameters, IAlgorithm algorithm, int blockSize) {
        return new byte[0];
    }

    @Override
    public byte[] decryptWithMode(byte[] cipheredText, byte[] IV, List<String> parameters, IAlgorithm algorithm, int blockSize) {
        return new byte[0];
    }
}
