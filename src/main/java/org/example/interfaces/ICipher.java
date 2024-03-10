package org.example.interfaces;

import java.util.List;

public interface ICipher {

    public byte[] encrypt(byte[] block);

    public byte[] decrypt(byte[] block);

    public List<byte[]> getRKeys(byte[] key);

}
