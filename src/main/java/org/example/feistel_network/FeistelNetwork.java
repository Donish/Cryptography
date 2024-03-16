package org.example.feistel_network;

import org.example.interfaces.ICipher;
import org.example.interfaces.ICipherConversion;
import org.example.interfaces.IRoundKeyGenerator;
import org.example.utils.BitUtils;

import java.util.Arrays;
import java.util.List;

//TODO: наследовать от Cipher или от ICipher
public class FeistelNetwork implements ICipher {

    protected final byte[] key;
    protected final List<byte[]> RKeys;
    protected final ICipherConversion feistelConversion;
    protected final IRoundKeyGenerator keyGenerator;
    protected final int roundsCount;

    public FeistelNetwork(byte[] key,
                          IRoundKeyGenerator keyGenerator,
                          ICipherConversion cipherConversion,
                          int roundsCount) {
        if (cipherConversion == null || keyGenerator == null || key == null) {
            throw new RuntimeException("null passed to FeistelNetwork");
        }

        this.key = key;
        this.feistelConversion = cipherConversion;
        this.keyGenerator = keyGenerator;
        this.RKeys = getRKeys(key);
        this.roundsCount = roundsCount;
    }

    @Override
    public byte[] encrypt(byte[] block) {
        byte[] leftBlock = Arrays.copyOfRange(block, 0, block.length / 2);
        byte[] rightBlock = Arrays.copyOfRange(block, block.length / 2, block.length);
        byte[] leftPrev;

        for(int i = 0; i < this.roundsCount; i++) {
            leftPrev = leftBlock.clone();
            leftBlock = rightBlock.clone();
            rightBlock = BitUtils.xorArrays(leftPrev, feistelConversion.convert(rightBlock, this.RKeys.get(i)));
        }

        byte[] result = new byte[block.length];
        System.arraycopy(rightBlock, 0, result, 0, rightBlock.length);
        System.arraycopy(leftBlock, 0, result, leftBlock.length, result.length);
        return result;
    }

    @Override
    public byte[] decrypt(byte[] block) {
        byte[] leftBlock = Arrays.copyOfRange(block, 0, block.length / 2);
        byte[] rightBlock = Arrays.copyOfRange(block, block.length / 2, block.length);
        byte[] leftPrev;

        for (int i = 0; i < this.roundsCount; i++) {
            leftPrev = leftBlock.clone();
            leftBlock = rightBlock.clone();
            rightBlock = BitUtils.xorArrays(leftPrev, feistelConversion.convert(rightBlock, this.RKeys.get(this.roundsCount - 1 - i)));
        }

        byte[] result = new byte[block.length];
        System.arraycopy(rightBlock, 0, result, 0, rightBlock.length);
        System.arraycopy(leftBlock, 0, result, leftBlock.length, result.length);
        return result;
    }

    @Override
    public List<byte[]> getRKeys(byte[] key) {
        return this.keyGenerator.generateRKeys(key);
    }
}
