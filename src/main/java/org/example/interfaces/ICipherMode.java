package org.example.interfaces;

import java.util.List;

public interface ICipherMode {

    public byte[] encryptWithMode(byte[] text, byte[] IV, IPadding padding, List<String> parameters, ICipher cipher);

    public byte[] decryptWithMode(byte[] cipherText, byte[] IV, IPadding padding, List<String> parameters, ICipher cipher);

}
