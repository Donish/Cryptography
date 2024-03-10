package org.example.cipher;

import org.example.interfaces.ICipher;

import java.util.List;

public class Cipher implements ICipher {

    protected List<byte[]> RKeys;
    protected byte[] key;
    protected CipherMode cipherMode;
    protected Padding padding;
    protected byte[] IV;
    protected List<byte[]> modeArgs;

    public enum CipherMode {
        ECB,
        CBC,
        PCBC,
        CFB,
        OFB,
        CTR,
        RandomDelta
    }

    public enum Padding {
        ZEROS,
        ANSIX923,
        PKCS7,
        ISO10126
    }

    public Cipher(byte[] key,
                  CipherMode cipherMode,
                  Padding padding,
                  byte[] IV,
                  List<byte[]> modeArgs) {
        this.key = key;
        this.cipherMode = cipherMode;
        this.padding = padding;
        this.IV = IV;
        this.modeArgs = modeArgs;
        this.RKeys = getRKeys(this.key);
    }

    @Override
    public byte[] encrypt(byte[] block) {
        return new byte[0];
    }

    public void encrypt(byte[] block, byte[] result) { //TODO: передавать список?

    }

    public void encrypt(String inputFilename, String outputFilename) {

    }

    //TODO: метод, выполняющий основное шифрование и принимающий фиксированный размер блока (64 бита для DES). Вызывать его в остальных методах

    @Override
    public byte[] decrypt(byte[] block) {
        return new byte[0];
    }

    public void decrypt(byte[] block, byte[] result) {

    }

    public void decrypt(String inputFilename, String outputFilename) {

    }

    @Override
    public List<byte[]> getRKeys(byte[] key) {
        return null;
    }
}
