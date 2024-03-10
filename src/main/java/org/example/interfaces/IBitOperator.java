package org.example.interfaces;

public interface IBitOperator {

    public enum BitIndexing {
        SENIOR_TO_JUNIOR,
        JUNIOR_TO_SENIOR
    }

    public enum FirstBit {
        ZERO,
        ONE
    }

    public byte[] bitPermutation(byte[] source, int[] pBlock, BitIndexing indexRule, FirstBit startRule);

}
