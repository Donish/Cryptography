package org.example.impl.cipher_mode_impl;

import org.example.interfaces.ICipher;
import org.example.interfaces.ICipherMode;
import org.example.interfaces.IPadding;
import org.example.utils.BitUtils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class ECBMode implements ICipherMode {
    @Override
    public byte[] encryptWithMode(byte[] text, byte[] IV, IPadding padding, List<String> parameters, ICipher cipher) {
        int blockSizeInBits = Integer.parseInt(parameters.get(0));
        int blockSizeInBytes = blockSizeInBits / 8;
        int blocksCount = text.length % blockSizeInBytes == 0 ?
                text.length / blockSizeInBytes :
                text.length / blockSizeInBytes + 1;
        boolean isFit = text.length / blockSizeInBytes == blocksCount;
        byte[] block;
        List<Future<byte[]>> futures = new ArrayList<>();
        byte[] result;

        List<byte[]> list = new ArrayList<>(); // temp

        for (int i = 0; i < blocksCount; i++) {
            block = BitUtils.getBlock(text, blockSizeInBits, i * blockSizeInBytes);
            if (!isFit) {
                block = padding.padBlock(block, blockSizeInBits);
            }

            list.add(cipher.encrypt(block));
            //TODO: многопоточно шифруем блок и кладем результат во future, затем вытаскиваем в массив байтов
        }

        result = list.stream()
                .collect(ByteArrayOutputStream::new,
                        (b, e) -> b.write(e, 0, e.length),
                        (a, b) -> {}).toByteArray();

        return result;
    }

    @Override
    public byte[] decryptWithMode(byte[] cipherText, byte[] IV, IPadding padding, List<String> parameters, ICipher cipher) {
        int blockSizeInBits = Integer.parseInt(parameters.get(0));
        int blockSizeInBytes = blockSizeInBits / 8;
        int blocksCount = cipherText.length % blockSizeInBytes == 0 ?
                cipherText.length / blockSizeInBytes :
                cipherText.length / blockSizeInBytes + 1;
        boolean isFit = cipherText.length / blockSizeInBytes == blocksCount;
        byte[] block;
        List<Future<byte[]>> futures = new ArrayList<>();
        byte[] result;

        List<byte[]> list = new ArrayList<>(); // temp

        for (int i = 0; i < blocksCount; i++) {
            block = BitUtils.getBlock(cipherText, blockSizeInBits, i * blockSizeInBytes);
            if (!isFit) {
                block = padding.padBlock(block, blockSizeInBits);
            }

            list.add(cipher.decrypt(block));
        }

        result = list.stream()
                .collect(ByteArrayOutputStream::new,
                        (b, e) -> b.write(e, 0, e.length),
                        (a, b) -> {}).toByteArray();

        return result;
    }
}
