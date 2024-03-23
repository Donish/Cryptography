package org.example.impl.padding_impl;

import org.example.interfaces.IPadding;

import java.util.Arrays;

public class ZerosPadding implements IPadding {

    @Override
    public byte[] padBlock(byte[] block, int requiredSizeInBits) {
        int requiredSizeInBytes = requiredSizeInBits / 8;
        byte[] result = new byte[requiredSizeInBytes];
        System.arraycopy(block, 0, result, 0, block.length);

        for (int i = block.length; i < requiredSizeInBytes; i++) {
            result[i] = 0;
        }

        return result;
    }

    @Override
    public byte[] removePadding(byte[] block) {
        int idx = 0;
        for (int i = block.length - 1; i >= 0; i--) {
            if (block[i] != 0) {
                idx = i;
                break;
            }
        }
        return Arrays.copyOf(block, idx + 1);
    }

}
