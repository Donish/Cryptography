package org.example.interfaces;

import java.util.List;

public interface ICipher {

    byte[] encrypt(byte[] text);

    byte[] decrypt(byte[] text);

    List<byte[]> getRKeys(byte[] key);

}
