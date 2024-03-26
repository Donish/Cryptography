import org.example.Algorithm.DES;
import org.example.cipher.CipherService;
import org.example.utils.FileUtils;

public class Main {
    public static void main(String[] args) {

        byte[] key = {0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25};
        DES des = new DES(key);

        CipherService cipherService = new CipherService(key, CipherService.CipherMode.ECB, CipherService.Padding.ZEROS, des);
        String inputFile = "source.jpg";
        String outputFile = "encrypted.txt";
        String decryptedFile = "decrypted.jpg";

        String inFilePath = FileUtils.getFilePath(inputFile);
        String out = FileUtils.getFilePath(outputFile);
        String dec = FileUtils.getFilePath(decryptedFile);
        cipherService.encrypt(inFilePath, out);
        cipherService.decrypt(out, dec);


    }
}