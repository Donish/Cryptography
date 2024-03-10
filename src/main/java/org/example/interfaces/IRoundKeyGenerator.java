package org.example.interfaces;

import java.util.List;

public interface IRoundKeyGenerator {

    public List<byte[]> generateRKeys(byte[] key);

}
