import org.example.algorithm.DES;
import org.example.cipher.CipherService;

public class Main {
    public static void main(String[] args) {
        byte[] key = {0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25};
        byte[] IV = {45, 32, 78, 127, 0, -23, -56, -98};
        DES des = new DES(key);

        CipherService cipherService = new CipherService(key,
                CipherService.CipherMode.ECB,
                CipherService.Padding.ANSIX923,
                des,
                IV);

        String inputFile = "C:/photo/source.jpg";
        String outputFile = "C:/photo/encrypted.txt";
        String decryptedFile = "C:/photo/decrypted.jpg";

        cipherService.encrypt(inputFile, outputFile);
        cipherService.decrypt(outputFile, decryptedFile);
    }
}