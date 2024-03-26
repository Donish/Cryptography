package org.example.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class FileUtils {

    public String getFilePath(String filename) {
        return Objects.requireNonNull(getClass().getClassLoader().getResource(".")).getPath() + filename;
    }

    public static byte[] readFileBlock(String filePath, int fileBlockSize, long offset) {
        try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
            file.seek(offset);
            int bytesRead = 0;
            long fileLength = file.length();
            List<Byte> block = new ArrayList<>();

            while (bytesRead < fileBlockSize && file.getFilePointer() < fileLength) {
                block.add(file.readByte());
                bytesRead++;
            }

            byte[] result = new byte[bytesRead];
            IntStream.range(0, bytesRead).forEach(i -> result[i] = block.get(i));
            return result;

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void writeFileBlock(String filePath, byte[] toWrite) {
        try (RandomAccessFile file = new RandomAccessFile(filePath, "rw")) {
            file.seek(file.length());
            file.write(toWrite);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
