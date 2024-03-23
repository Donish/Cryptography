package org.example.impl.padding_impl;

import org.example.interfaces.IPadding;
import org.example.utils.BitUtils;

import java.util.Arrays;

public class PKCS7Padding implements IPadding {

    @Override
    public byte[] padBlock(byte[] block, int requiredSizeInBits) {
        int requiredSizeInBytes = requiredSizeInBits / 8;
        int paddedBytes = requiredSizeInBytes - block.length;
        byte[] result = new byte[requiredSizeInBytes];
        System.arraycopy(block, 0, result, 0, block.length);

        for (int i = block.length; i < requiredSizeInBytes; i++) {
            result[i] = (byte) paddedBytes;
        }

        return result;
    }

    @Override
    public byte[] removePadding(byte[] block) {
        int count = BitUtils.getUnsignedByte(block[block.length - 1]);
        return Arrays.copyOf(block, block.length - count);
    }

}
