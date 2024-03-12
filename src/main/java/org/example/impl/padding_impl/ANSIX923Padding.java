package org.example.impl.padding_impl;

import org.example.interfaces.IPadding;

public class ANSIX923Padding implements IPadding {

    @Override
    public byte[] padBlock(byte[] block, int requiredSizeInBits) {
        int requiredSizeInBytes = requiredSizeInBits / 8;
        int paddedBytes = requiredSizeInBytes - block.length;
        byte[] result = new byte[requiredSizeInBytes];
        System.arraycopy(block, 0, result, 0, block.length);

        for (int i = block.length; i < requiredSizeInBytes - 1; i++) {
            result[i] = 0;
        }
        result[requiredSizeInBytes - 1] = (byte) paddedBytes;

        return result;
    }

}
