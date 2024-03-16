package org.example.interfaces;

import java.util.List;

public interface ICipher {

    public byte[] encrypt(byte[] text);

    public byte[] decrypt(byte[] text);

    public List<byte[]> getRKeys(byte[] key);

}
