package org.example.impl;

import org.example.IBitOperator;

public class BitOperator implements IBitOperator {

    @Override
    public byte[] bitPermutation(byte[] source, int[] pBlock, bitIndexing indexRule, firstBit startRule) {
        int sourceSize = source.length;
        int bitQuantity = pBlock.length;
        int byteSize = bitQuantity % 8 == 0 ? bitQuantity / 8 : bitQuantity / 8 + 1;
        int offset = 0;
        if (startRule == firstBit.ONE) {
            offset++;
        }

        byte[] result = new byte[byteSize];
        if (indexRule == bitIndexing.junior_to_senior)
            junToSenPermutation(source, result, pBlock, offset);
        else
            senToJunPermutation(source, result, pBlock, offset);

        return result;
    }

    private void junToSenPermutation(byte[] source, byte[] result, int[] pBlock, int offset) {

    }

    private void senToJunPermutation(byte[] source, byte[] result, int[] pBlock, int offset) {
        int firstBitIdx = pBlock.length % 8;

    }
}
