package org.example.impl;

import org.example.interfaces.IBitOperator;

public class BitOperator implements IBitOperator {

    @Override
    public byte[] bitPermutation(byte[] source, int[] pBlock, BitIndexing indexRule, FirstBit startRule) {
        if (source == null || pBlock == null || indexRule == null || startRule == null) {
            throw new RuntimeException("null arg provided");
        }
        for (int el : pBlock) {
            if ((el == 0 && startRule == FirstBit.ONE) || el < 0 || (startRule == FirstBit.ZERO && el >= source.length * 8) || (startRule == FirstBit.ONE && el > source.length * 8))
                throw new RuntimeException("incorrect pBlock elements");
        }

        int bitQuantity = pBlock.length;
        int byteSize = bitQuantity % 8 == 0 ? bitQuantity / 8 : bitQuantity / 8 + 1;
        int offset = 0;
        if (startRule == FirstBit.ONE) {
            offset++;
        }

        byte[] result = new byte[byteSize];
        if (indexRule == BitIndexing.JUNIOR_TO_SENIOR) junToSenPermutation(source, result, pBlock, offset);
        else senToJunPermutation(source, result, pBlock, offset);

        return result;
    }

    private void junToSenPermutation(byte[] source, byte[] result, int[] pBlock, int offset) {
        byte temp;
        for (int i = 0; i < pBlock.length; i++) {
            int byteIdx = source.length - ((pBlock[i] - offset) / 8) - 1;
            temp = source[byteIdx];
            int bitIdx = (pBlock[i] - offset) % 8;
            int bit = (temp >> bitIdx) & 1;

            int resultByteIdx = result.length - ((pBlock.length - (i + 1)) / 8) - 1;
            result[resultByteIdx] <<= 1;
            result[resultByteIdx] |= (byte) bit;
        }
    }

    private void senToJunPermutation(byte[] source, byte[] result, int[] pBlock, int offset) {
        byte temp;
        for (int i = 0; i < pBlock.length; i++) {
            int byteIdx = (pBlock[i] - offset) / 8;
            temp = source[byteIdx];
            int bitIdx = (pBlock[i] - offset) % 8;
            int bit = (temp >> (7 - bitIdx)) & 1;

            int resultByteIdx = result.length - ((pBlock.length - (i + 1)) / 8) - 1;
            result[resultByteIdx] <<= 1;
            result[resultByteIdx] |= (byte) bit;
        }
    }
}