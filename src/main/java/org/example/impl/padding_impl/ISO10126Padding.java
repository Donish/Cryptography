package org.example.impl.padding_impl;

import org.example.interfaces.IPadding;
import org.example.utils.BitUtils;

import java.util.Arrays;
import java.util.Random;

public class ISO10126Padding implements IPadding {

    @Override
    public byte[] padBlock(byte[] block, int requiredSizeInBits) {
        Random random = new Random();
        int requiredSizeInBytes = requiredSizeInBits / 8;
        int paddedBytes = requiredSizeInBytes - block.length;
        byte[] result = new byte[requiredSizeInBytes];
        System.arraycopy(block, 0, result, 0, block.length);
        byte[] randomBytes = new byte[paddedBytes];
        random.nextBytes(randomBytes);
        System.arraycopy(randomBytes, 0, result, block.length, randomBytes.length - 1);
        result[requiredSizeInBytes - 1] = (byte) paddedBytes;

        return result;
    }

    @Override
    public byte[] removePadding(byte[] block) {
        int count = BitUtils.getUnsignedByte(block[block.length - 1]);
        return Arrays.copyOf(block, block.length - count);
    }

}
