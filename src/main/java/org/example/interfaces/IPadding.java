package org.example.interfaces;

public interface IPadding {

    public byte[] padBlock(byte[] block, int requiredSizeInBits);

    public byte[] removePadding(byte[] block);

}
