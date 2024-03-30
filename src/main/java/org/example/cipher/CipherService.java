package org.example.cipher;

import org.example.algorithm.DES;
import org.example.impl.cipher_mode_impl.*;
import org.example.impl.padding_impl.ANSIX923Padding;
import org.example.impl.padding_impl.ISO10126Padding;
import org.example.impl.padding_impl.PKCS7Padding;
import org.example.impl.padding_impl.ZerosPadding;
import org.example.interfaces.IAlgorithm;
import org.example.interfaces.ICipherMode;
import org.example.interfaces.IPadding;
import org.example.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CipherService {

    private final byte[] key;
    private final ICipherMode cipherMode;
    private final IPadding padding;
    private byte[] IV = null;
    private List<String> modeArgs = null;
    private final IAlgorithm algorithm;
    private int byteBlockSize;

    private final int FILE_BLOCK_SIZE = 2097152;

    public enum CipherMode {
        ECB, CBC, PCBC, CFB, OFB, CTR, RandomDelta
    }

    public enum Padding {
        ZEROS, ANSIX923, PKCS7, ISO10126
    }

    //TODO: подавать название алгоритма в конструктор и создавать алгоритм уже в конструкторе
    public CipherService(byte[] key, CipherMode cipherMode, Padding padding, IAlgorithm algorithm) {
        if (key == null) {
            throw new RuntimeException("passed null key to CipherService");
        }
        this.key = key;

        if (padding == Padding.ZEROS) {
            this.padding = new ZerosPadding();
        } else if (padding == Padding.ANSIX923) {
            this.padding = new ANSIX923Padding();
        } else if (padding == Padding.PKCS7) {
            this.padding = new PKCS7Padding();
        } else if (padding == Padding.ISO10126) {
            this.padding = new ISO10126Padding();
        } else {
            throw new RuntimeException("passed incorrect padding to CipherService");
        }

        if (cipherMode == CipherMode.ECB) {
            this.cipherMode = new ECBMode();
        } else if (cipherMode == CipherMode.CBC) {
            this.cipherMode = new CBCMode();
        } else if (cipherMode == CipherMode.PCBC) {
            this.cipherMode = new PCBCMode();
        } else if (cipherMode == CipherMode.CFB) {
            this.cipherMode = new CFBMode();
        } else if (cipherMode == CipherMode.OFB) {
            this.cipherMode = new OFBMode();
        } else if (cipherMode == CipherMode.CTR) {
            this.cipherMode = new CTRMode();
        } else if (cipherMode == CipherMode.RandomDelta) {
            this.cipherMode = new RDMode();
        } else {
            throw new RuntimeException("passed incorrect cipherMode to CipherService");
        }

        if (algorithm == null) {
            throw new RuntimeException("passed null algorithm to CipherService");
        }
        this.algorithm = algorithm;
        if (algorithm instanceof DES) {
            this.byteBlockSize = 8;
        }
    }

    public CipherService(byte[] key,
                         CipherMode cipherMode,
                         Padding padding,
                         IAlgorithm algorithm,
                         byte[] IV,
                         List<String> modeArgs) {
        this(key, cipherMode, padding, algorithm);

        if (IV == null || modeArgs == null) {
            throw new RuntimeException("passed null to Cipher");
        }
        this.IV = IV.clone();
        this.modeArgs = new ArrayList<>(modeArgs);
    }

    private byte[] encryptFileBlock(byte[] text) {
        text = padding.makePadding(text, byteBlockSize);
        return cipherMode.encryptWithMode(text, IV, modeArgs, algorithm, byteBlockSize);
    }

    public void encrypt(String inputFilename, String outputFilename) {
        File inputFile = new File(inputFilename);
        if (!inputFile.exists()) {
            throw new RuntimeException("file " + inputFile + " doesn't exist");
        }
        long fileSize = inputFile.length();
        byte[] fileBlock;
        byte[] cipheredBlock;

        for (long bytesRead = 0L; bytesRead < fileSize; bytesRead += FILE_BLOCK_SIZE) {
            fileBlock = FileUtils.readFileBlock(inputFilename, FILE_BLOCK_SIZE, bytesRead);
            cipheredBlock = encryptFileBlock(fileBlock);
            FileUtils.writeFileBlock(outputFilename, cipheredBlock);
        }
    }

    private byte[] decryptFileBlock(byte[] text) {
        text = padding.removePadding(text);
        return cipherMode.decryptWithMode(text, IV, modeArgs, algorithm, byteBlockSize);
    }

    public void decrypt(String inputFilename, String outputFilename) {
        File inputFile = new File(inputFilename);
        if (!inputFile.exists()) {
            throw new RuntimeException("file " + inputFilename + " doesn't exist");
        }

        long fileSize = inputFile.length();
        byte[] fileBlock;
        byte[] decryptedBlock;

        for (long bytesRead = 0L; bytesRead < fileSize; bytesRead += FILE_BLOCK_SIZE + byteBlockSize) {
            fileBlock = FileUtils.readFileBlock(inputFilename, FILE_BLOCK_SIZE + byteBlockSize, bytesRead);
            decryptedBlock = decryptFileBlock(fileBlock);
            FileUtils.writeFileBlock(outputFilename, decryptedBlock);
        }
    }
}
