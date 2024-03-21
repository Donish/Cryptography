package org.example.utils;

import java.util.Arrays;

public class BitUtils {

    public enum BitIndexing {
        SENIOR_TO_JUNIOR,
        JUNIOR_TO_SENIOR
    }

    public enum FirstBit {
        ZERO,
        ONE
    }

    public static byte[] bitPermutation(byte[] source, int[] pBlock, BitIndexing indexRule, FirstBit startRule) {
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

    private static void junToSenPermutation(byte[] source, byte[] result, int[] pBlock, int offset) {
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

    private static void senToJunPermutation(byte[] source, byte[] result, int[] pBlock, int offset) {
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

    public static int getUnsignedByte(byte number) {
        return number & 0xFF;
    }

    public static byte[] getBlock(byte[] text, int bitBlockSize, int idx) {
        int byteBlockSize = bitBlockSize / 8;
        if (text.length - idx < byteBlockSize) {
            return Arrays.copyOfRange(text, idx, text.length);
        }
        return Arrays.copyOfRange(text, idx, idx + byteBlockSize);
    }

    public static byte[] xorArrays(byte[] firstArr, byte[] secondArr) {
        byte[] result = new byte[Math.max(firstArr.length, secondArr.length)];
        byte[] largeArr, smallArr;
        if (firstArr.length > secondArr.length) {
            largeArr = firstArr.clone();
            smallArr = secondArr.clone();
        } else {
            largeArr = secondArr.clone();
            smallArr = firstArr.clone();
        }
        int diff = largeArr.length - smallArr.length;

        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = (byte) (largeArr[i] ^ smallArr[i - diff]);
        }
        if(diff > 0) {
            System.arraycopy(largeArr, 0, result, 0, diff);
        }

        return result;
    }

    public static long byteArrToLong(byte[] array) {
        long result = 0;
        for (byte el : array) {
            result <<= 8;
            result |= el;
        }
        return result;
    }

    public static byte[] longToByteArr(long num, int byteSize) {
        byte[] result = new byte[byteSize];
        for (int i = byteSize - 1; i >= 0; i--) {
            result[i] = (byte) (num & 0xFF);
            num >>>=8;
        }
        return result;
    }

    public static int byteArrToInt(byte[] array) {
        int result = 0;
        for (byte el : array) {
            result <<= 8;
            result |= el;
        }
        return result;
    }

    public static byte[] intToByteArr(int num) {
        byte[] result = new byte[4];
        for (int i = 3; i >= 0; i--) {
            result[i] = (byte) (num & 0xFF);
            num >>>=8;
        }
        return result;
    }

    public static int lCircularShift(int num, int numBitSize, int count) {
        int mask = (1 << numBitSize) - 1;
        count %= numBitSize;

        return ((num << count) & mask) | (num >>> (numBitSize - count));
    }

    public static int rCircularShift(int num, int numBitSize, int count) {
        return 0;
    }

    public static int countUnits(byte num) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (((num >>> i) & 1) == 1) count++;
        }
        return count;
    }
}