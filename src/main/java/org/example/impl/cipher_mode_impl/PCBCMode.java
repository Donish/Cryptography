package org.example.impl.cipher_mode_impl;

import org.example.interfaces.IAlgorithm;
import org.example.interfaces.ICipherMode;
import org.example.utils.BitUtils;

import java.util.Arrays;
import java.util.List;

public class PCBCMode implements ICipherMode {

    @Override
    public byte[] encryptWithMode(byte[] text, byte[] IV, List<String> notUsed, IAlgorithm algorithm, int blockSize) {
        byte[] result = new byte[text.length];
        byte[] prevBlock = IV;
        int blocksCount = text.length / blockSize;

        for (int i = 0; i < blocksCount; i++) {
            int idx = i * blockSize;
            byte[] block = Arrays.copyOfRange(text, idx, idx + blockSize);
            byte[] encryptedBlock = algorithm.encryptBlock(BitUtils.xorArrays(prevBlock, block));
            System.arraycopy(encryptedBlock, 0, result, idx, encryptedBlock.length);
            prevBlock = BitUtils.xorArrays(block, encryptedBlock);
        }

        return result;
    }

    @Override
    public byte[] decryptWithMode(byte[] cipheredText, byte[] IV, List<String> notUsed, IAlgorithm algorithm, int blockSize) {
        byte[] result = new byte[cipheredText.length];
        byte[] prevBlock = IV;
        int blocksCount = cipheredText.length / blockSize;

        for (int i = 0; i < blocksCount; i++) {
            int idx = i * blockSize;
            byte[] block = Arrays.copyOfRange(cipheredText, idx, idx + blockSize);
            byte[] decryptedBlock = BitUtils.xorArrays(algorithm.decryptBlock(block), prevBlock);
            System.arraycopy(decryptedBlock, 0, result, idx, decryptedBlock.length);
            prevBlock = BitUtils.xorArrays(block, decryptedBlock);
        }

        return result;
    }
}
