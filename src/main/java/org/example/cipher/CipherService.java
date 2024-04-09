package org.example.cipher;

import org.example.algorithm.DES;
import org.example.impl.cipher_mode_impl.*;
import org.example.impl.padding_impl.*;
import org.example.interfaces.IAlgorithm;
import org.example.interfaces.ICipherMode;
import org.example.interfaces.IPadding;
import org.example.utils.FileUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CipherService {

    private final byte[] key;
    private final ICipherMode cipherMode;
    private final IPadding padding;
    private byte[] IV = null;
    private List<String> modeArgs = null;
    private final IAlgorithm algorithm;
    private int byteBlockSize;

    // TODO: словарь режимов и паддингов

    private final int FILE_BLOCK_SIZE = 2097152;

    public enum CipherMode {
        ECB, CBC, PCBC, CFB, OFB, CTR, RandomDelta
    }

    public enum Padding {
        ZEROS, ANSIX923, PKCS7, ISO10126
    }

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

    public CipherService(byte[] key, CipherMode cipherMode, Padding padding, IAlgorithm algorithm, byte[] IV) {
        this(key, cipherMode, padding, algorithm);
        if (IV == null) {
            throw new RuntimeException("passed null to Cipher");
        }
        this.IV = IV.clone();
    }

    public CipherService(byte[] key,
                         CipherMode cipherMode,
                         Padding padding,
                         IAlgorithm algorithm,
                         byte[] IV,
                         List<String> modeArgs) {
        this(key, cipherMode, padding, algorithm, IV);

        if (modeArgs == null) {
            throw new RuntimeException("passed null to Cipher");
        }
        this.modeArgs = new ArrayList<>(modeArgs);
    }

    private byte[] encryptFileBlock(byte[] text) {
        text = padding.makePadding(text, byteBlockSize);
        return cipherMode.encryptWithMode(text, IV, modeArgs, algorithm, byteBlockSize);
    }

    public void encrypt(String inputFilename, String outputFilename) {
        readFile(inputFilename, outputFilename, true);
//        File inputFile = new File(inputFilename);
//        if (!inputFile.exists()) {
//            throw new RuntimeException("file " + inputFile + " doesn't exist");
//        }
//        long fileSize = inputFile.length();
//        byte[] fileBlock;
//        byte[] cipheredBlock;
//
//        for (long bytesRead = 0L; bytesRead < fileSize; bytesRead += FILE_BLOCK_SIZE) {
//            fileBlock = FileUtils.readFileBlock(inputFilename, FILE_BLOCK_SIZE, bytesRead);
//            cipheredBlock = encryptFileBlock(fileBlock);
//            FileUtils.writeFileBlock(outputFilename, cipheredBlock);
//        }
    }

    private byte[] decryptFileBlock(byte[] text) {
        return padding.removePadding(cipherMode.decryptWithMode(text, IV, modeArgs, algorithm, byteBlockSize));
    }

    public void decrypt(String inputFilename, String outputFilename) {
        readFile(inputFilename, outputFilename, false);
//        File inputFile = new File(inputFilename);
//        if (!inputFile.exists()) {
//            throw new RuntimeException("file " + inputFilename + " doesn't exist");
//        }
//
//        long fileSize = inputFile.length();
//        byte[] fileBlock;
//        byte[] decryptedBlock;
//
//        for (long bytesRead = 0L; bytesRead < fileSize; bytesRead += FILE_BLOCK_SIZE + byteBlockSize) {
//            fileBlock = FileUtils.readFileBlock(inputFilename, FILE_BLOCK_SIZE + byteBlockSize, bytesRead);
//            decryptedBlock = decryptFileBlock(fileBlock);
//            FileUtils.writeFileBlock(outputFilename, decryptedBlock);
//        }
    }

    private void readFile(String fileName, String outputFileName, boolean isEncrypt) {
        Path path = Paths.get(fileName);
        try (AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ)) {
            long fileSize = fileChannel.size();
            long position = 0;

            while (position < fileSize) {
                CompletableFuture<ByteBuffer> readFuture = readChunk(fileChannel, position, isEncrypt);
                ByteBuffer byteBuffer = readFuture.get();

                if (byteBuffer.position() > 0) {
                    byteBuffer.flip();
                    byte[] text = new byte[byteBuffer.remaining()];
                    byteBuffer.get(text);

                    byte[] cipheredChunk = isEncrypt ? encryptFileBlock(text) : decryptFileBlock(text);
                    //TODO: асинхронная запись в файл
                    FileUtils.writeFileBlock(outputFileName, cipheredChunk);
                    byteBuffer.clear();
                } else {
                    break;
                }
                position += isEncrypt ? FILE_BLOCK_SIZE : FILE_BLOCK_SIZE + byteBlockSize;
            }

        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private CompletableFuture<ByteBuffer> readChunk(AsynchronousFileChannel fileChannel, long position, boolean isEncrypt) {
        ByteBuffer byteBuffer = isEncrypt ? ByteBuffer.allocate(FILE_BLOCK_SIZE) : ByteBuffer.allocate(FILE_BLOCK_SIZE + byteBlockSize);

        CompletableFuture<Integer> future = new CompletableFuture<>();
        fileChannel.read(byteBuffer, position, byteBuffer, new CompletionHandler<>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                future.complete(result);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {}
        });

        return future.thenApply(readBytes -> byteBuffer);
    }
}






