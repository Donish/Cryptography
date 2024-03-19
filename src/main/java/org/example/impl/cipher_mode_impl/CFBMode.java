package org.example.impl.cipher_mode_impl;

import org.example.interfaces.ICipher;
import org.example.interfaces.ICipherMode;
import org.example.interfaces.IPadding;

import java.util.List;

public class CFBMode implements ICipherMode {
    @Override
    public byte[] encryptWithMode(byte[] text, byte[] IV, IPadding padding, List<String> parameters, ICipher cipher) {
        return new byte[0];
    }

    @Override
    public byte[] decryptWithMode(byte[] cipherText, byte[] IV, IPadding padding, List<String> parameters, ICipher cipher) {
        return new byte[0];
    }
}
