package org.example.interfaces;

public interface IPadding {

    public byte[] makePadding(byte[] text, int requiredSizeInBytes);

    public byte[] removePadding(byte[] text);

}
