import org.example.Algorithm.DES;
import org.example.cipher.CipherService;

public class Main {
    public static void main(String[] args) {
        byte[] key = {0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25};
        DES des = new DES(key);

        byte[] source = {5, 3, 103, 25, 76, 30, 12, 4};
        byte[] ciphered = des.encryptBlock(source);
        for (var el : source) {
            System.out.print(el);
            System.out.print(" ");
        }
        System.out.println();

        for (var el : ciphered) {
            System.out.print(el);
            System.out.print(" ");
        }
        System.out.println();

        byte[] decrypted = des.decryptBlock(ciphered);
        for (var el : decrypted) {
            System.out.print(el);
            System.out.print(" ");
        }
        System.out.println();

        CipherService cipherService = new CipherService(key, CipherService.CipherMode.ECB, CipherService.Padding.ZEROS, des);

//        int num = 49478;
//        System.out.println(Integer.toBinaryString(num));
//        int res = BitUtils.lCircularShift(num, 16, 30);
//        System.out.println(res);
//        System.out.println(Integer.toBinaryString(res));
//        long num = 1;
//        num <<= 62;
//        System.out.println(num);
//        System.out.println(Long.toBinaryString(num));


//        byte[] fBlock = BitUtils.getBlock(text, 64, 0);
//        byte[] sBlock = BitUtils.getBlock(text, 64, 8);
//        for (var el : fBlock) {
//            System.out.println(el);
//        }
//        System.out.println();
//        for (var el : sBlock) {
//            System.out.println(el);
//        }
//        System.arraycopy(source, 0, result, result.length, source.length);


//
//        ZerosPadding zerosPadding = new ZerosPadding();
//        byte[] result_zeros = zerosPadding.padBlock(source, 64);
//        System.out.println("ZEROS:");
//        for (byte el : result_zeros) {
//            System.out.print(el);
//            System.out.print(" ");
//        }
//        System.out.println();
//        System.out.println();
//
//
//        byte[] source_rsa = new byte[150];
//        for (int i = 0; i < 150; i++) {
//            source_rsa[i] = (byte) (i + 1);
//        }
//
//        ISO10126Padding iso10126Padding = new ISO10126Padding();
//        byte[] result_rsa = iso10126Padding.padBlock(source_rsa, 2048);
//        System.out.println("ISO10126:");
//        for (byte el : result_rsa) {
//            System.out.print(el);
//            System.out.print(" ");
//        }
//        System.out.println();
//        System.out.print("Padded bytes: ");
//        System.out.println(BitUtils.getUnsignedByte(result_rsa[result_rsa.length - 1]));

//        CipherService cipherService = new CipherService(source, CipherService.CipherMode.ECB, CipherService.Padding.ZEROS);
    }
}