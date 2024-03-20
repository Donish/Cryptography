package org.example.impl.round_key_impl;

import org.example.interfaces.IRoundKeyGenerator;
import org.example.utils.BitUtils;

import java.util.ArrayList;
import java.util.List;

public class DESKeyGenerator implements IRoundKeyGenerator {

    private final int[] PC1First = {57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36};

    private final int[] PC1Second = {63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4};

    private final int[] PC2 =
            {14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10,
            23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32};

    private final int[] Shifts = {1, 1, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

    @Override
    public List<byte[]> generateRKeys(byte[] key) {
        List<byte[]> RKeys = new ArrayList<>();
        byte[] C = BitUtils.bitPermutation(key, PC1First, BitUtils.BitIndexing.SENIOR_TO_JUNIOR, BitUtils.FirstBit.ONE);
        byte[] D = BitUtils.bitPermutation(key, PC1Second, BitUtils.BitIndexing.SENIOR_TO_JUNIOR, BitUtils.FirstBit.ONE);
        int CInt = BitUtils.byteArrToInt(C);
        int DInt = BitUtils.byteArrToInt(D);
        byte[] CD;

        for (int i = 0; i < 16; i++) {
            long temp = 0;
            CInt = BitUtils.lCircularShift(CInt, 28, Shifts[i]);
            DInt = BitUtils.lCircularShift(DInt, 28, Shifts[i]);
            temp |= CInt;
            temp <<= 28;
            temp |= DInt;
            CD = BitUtils.longToByteArr(temp, 7);
            RKeys.add(BitUtils.bitPermutation(CD, PC2, BitUtils.BitIndexing.SENIOR_TO_JUNIOR, BitUtils.FirstBit.ONE));
        }

        return RKeys;
    }
}