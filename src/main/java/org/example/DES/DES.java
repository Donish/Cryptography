package org.example.DES;

import org.example.feistel_network.FeistelNetwork;
import org.example.impl.conversion_impl.DESConversion;
import org.example.impl.round_key_impl.DESKeyGenerator;
import org.example.interfaces.IAlgorithm;
import org.example.utils.BitUtils;

public final class DES implements IAlgorithm {
    private final int byteBlockSize = 8;
    private final int[] StartPBlock =
            {58, 50, 42, 34, 26, 18, 10, 2,
                    60, 52, 44, 36, 28, 20, 12, 4,
                    62, 54, 46, 38, 30, 22, 14, 6,
                    64, 56, 48, 40, 32, 24, 16, 8,
                    57, 49, 41, 33, 25, 17, 9, 1,
                    59, 51, 43, 35, 27, 19, 11, 3,
                    61, 53, 45, 37, 29, 21, 13, 5,
                    63, 55, 47, 39, 31, 23, 15, 7};

    private final int[] FinitePBlock =
            {40, 8, 48, 16, 56, 24, 64, 32,
                    39, 7, 47, 15, 55, 23, 63, 31,
                    38, 6, 46, 14, 54, 22, 62, 30,
                    37, 5, 45, 13, 53, 21, 61, 29,
                    36, 4, 44, 12, 52, 20, 60, 28,
                    35, 3, 43, 11, 51, 19, 59, 27,
                    34, 2, 42, 10, 50, 18, 58, 26,
                    33, 1, 41, 9, 49, 17, 57, 25};

    private final FeistelNetwork feistelNetwork;

    public DES(byte[] key) {
        if (!isValidKey(key)) {
            throw new RuntimeException("passed invalid key to DES");
        }
        this.feistelNetwork = new FeistelNetwork(key, new DESKeyGenerator(), new DESConversion(), 16);
    }

    private boolean isValidKey(byte[] key) {
        for (int i = 0; i < byteBlockSize; i++) {
            if ((BitUtils.countUnits(key[i]) & 1) == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public byte[] encryptBlock(byte[] block) {
        block = BitUtils.bitPermutation(block, StartPBlock, BitUtils.BitIndexing.SENIOR_TO_JUNIOR, BitUtils.FirstBit.ONE);
        block = feistelNetwork.encrypt(block);
        block = BitUtils.bitPermutation(block, FinitePBlock, BitUtils.BitIndexing.SENIOR_TO_JUNIOR, BitUtils.FirstBit.ONE);
        return block;
    }

    @Override
    public byte[] decryptBlock(byte[] cipherBlock) {
        cipherBlock = BitUtils.bitPermutation(cipherBlock, StartPBlock, BitUtils.BitIndexing.SENIOR_TO_JUNIOR, BitUtils.FirstBit.ONE);
        cipherBlock = feistelNetwork.decrypt(cipherBlock);
        cipherBlock = BitUtils.bitPermutation(cipherBlock, FinitePBlock, BitUtils.BitIndexing.SENIOR_TO_JUNIOR, BitUtils.FirstBit.ONE);
        return cipherBlock;
    }
}
