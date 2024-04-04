package org.example.impl.cipher_mode_impl;

import org.example.interfaces.IAlgorithm;
import org.example.interfaces.ICipherMode;
import org.example.utils.BitUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CBCMode implements ICipherMode {

    @Override
    public byte[] encryptWithMode(byte[] text, byte[] IV, List<String> notUsed, IAlgorithm algorithm, int blockSize) {
        byte[] prevBlock = IV.clone();
        byte[] result = new byte[text.length];
        for (int i = 0; i < text.length / blockSize; i++) {
            int idx = i * blockSize;
            byte[] block = Arrays.copyOfRange(text, idx, idx + blockSize);
            byte[] encryptedBlock = algorithm.encryptBlock(BitUtils.xorArrays(prevBlock, block));
            System.arraycopy(encryptedBlock, 0, result, idx, encryptedBlock.length);
            prevBlock = encryptedBlock.clone();
        }
        return result;
    }

    @Override
    public byte[] decryptWithMode(byte[] cipheredText, byte[] IV, List<String> notUsed, IAlgorithm algorithm, int blockSize) {
        byte[] result = new byte[cipheredText.length];
        IntStream.range(0, cipheredText.length / blockSize)
                .parallel()
                .forEach(i -> {
                    int idx = i * blockSize;
                    byte[] prevBlock = (i == 0) ? IV.clone() : Arrays.copyOfRange(cipheredText, idx - blockSize, idx);
                    byte[] block = Arrays.copyOfRange(cipheredText, idx, idx + blockSize);
                    byte[] decryptedBlock = BitUtils.xorArrays(prevBlock, algorithm.decryptBlock(block));
                    System.arraycopy(decryptedBlock, 0, result, idx, decryptedBlock.length);
                });
        return result;
    }
}
