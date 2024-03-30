import org.example.algorithm.DES;
import org.example.cipher.CipherService;

public class Main {
    public static void main(String[] args) {

        byte[] key = {0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25};
        DES des = new DES(key);

        CipherService cipherService = new CipherService(key, CipherService.CipherMode.ECB, CipherService.Padding.ANSIX923, des);
        String inputFile = "C:/photo/video.mp4";
        String outputFile = "C:/photo/encrypted.txt";
        String decryptedFile = "C:/photo/decrypted.mp4";

//        String inFilePath = "C:/photo/orig.jpg";
//        String out = "C:/photo/enc.txt";
//        String dec = "C:/photo/dec.jpg";

//        String inFilePath = FileUtils.getFilePath(inputFile);
//        String out = FileUtils.getFilePath(outputFile);
//        String dec = FileUtils.getFilePath(decryptedFile);
        cipherService.encrypt(inputFile, outputFile);
        cipherService.decrypt(outputFile, decryptedFile);
    }
}