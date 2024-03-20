package org.example.DES;

import org.example.cipher.CipherService;
import org.example.feistel_network.FeistelNetwork;
import org.example.impl.conversion_impl.DESConversion;
import org.example.impl.round_key_impl.DESKeyGenerator;
import org.example.interfaces.IAlgorithm;
import org.example.utils.BitUtils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public final class DES implements IAlgorithm {

    private final int bitBlockSize = 64;
    private final int byteBlockSize = 8;
    private final int[] PBlock = {58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7};

    private final FeistelNetwork feistelNetwork;

    public DES(byte[] key) {
        if (!isValidKey(key)) {
            throw new RuntimeException("passed invalid key to DES");
        }
        this.feistelNetwork = new FeistelNetwork(key, new DESKeyGenerator(), new DESConversion(), 16);
    }

    private boolean isValidKey(byte[] key) {
        //TODO: validate key
        return true;
    }

    @Override
    public byte[] encryptBlock(byte[] block) {
//        int blocksCount = text.length % byteBlockSize == 0 ?
//                text.length / byteBlockSize :
//                text.length / byteBlockSize + 1;
//        boolean isFit = text.length / byteBlockSize == blocksCount;
//        byte[] block;
//        List<byte[]> list = new ArrayList<>();
//        for (int i = 0; i < blocksCount; i++) {
//            block = BitUtils.getBlock(text, bitBlockSize, i * byteBlockSize);
//            if (!isFit) {
//                block = padding.padBlock(block, bitBlockSize);
//            }
//            list.add(BitUtils.bitPermutation(block, PBlock, BitUtils.BitIndexing.SENIOR_TO_JUNIOR, BitUtils.FirstBit.ONE));
//        }

//        byte[] permText = list.stream()
//                .collect(ByteArrayOutputStream::new,
//                        (b, e) -> b.write(e, 0, e.length),
//                        (a, b) -> {}).toByteArray();

        block = BitUtils.bitPermutation(block, PBlock, BitUtils.BitIndexing.SENIOR_TO_JUNIOR, BitUtils.FirstBit.ONE);
        block = feistelNetwork.encrypt(block);
        //TODO: ending permutation
        return block;
    }

    @Override
    public byte[] decryptBlock(byte[] cipherText) {
        return new byte[0];
    }
}
