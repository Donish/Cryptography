package org.example.interfaces;

import java.util.List;

public interface ICipherMode {

    public byte[] encryptWithMode(byte[] block, byte[] IV, List<String> parameters, IAlgorithm algorithm);

    public byte[] decryptWithMode(byte[] cipherBlock, byte[] IV, List<String> parameters, IAlgorithm algorithm);

}
