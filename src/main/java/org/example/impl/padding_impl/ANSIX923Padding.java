package org.example.impl.padding_impl;

import org.example.interfaces.IPadding;
import org.example.utils.BitUtils;

import java.util.Arrays;

public class ANSIX923Padding implements IPadding {

    @Override
    public byte[] makePadding(byte[] text, int requiredSizeInBytes) {
        byte[] result;
        boolean isMultiple = text.length % requiredSizeInBytes == 0;
        if (isMultiple) {
            result = new byte[text.length + requiredSizeInBytes];
        } else {
            result = new byte[text.length + (requiredSizeInBytes - text.length % requiredSizeInBytes)];
        }
        int paddedBytes = requiredSizeInBytes - (text.length % requiredSizeInBytes);
        System.arraycopy(text, 0, result, 0, text.length);

        result[result.length - 1] = (byte) paddedBytes;

        return result;
    }

    @Override
    public byte[] removePadding(byte[] text) {
        int count = BitUtils.getUnsignedByte(text[text.length - 1]);
        return Arrays.copyOf(text, text.length - count);
    }

}
