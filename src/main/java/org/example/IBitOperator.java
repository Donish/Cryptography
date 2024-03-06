package org.example;

public interface IBitOperator {

    public enum bitIndexing {
        senior_to_junior,
        junior_to_senior
    }

    public enum firstBit {
        ZERO,
        ONE
    }

    public byte[] bitPermutation(byte[] source, int[] pBlock, bitIndexing indexRule, firstBit startRule);

}
