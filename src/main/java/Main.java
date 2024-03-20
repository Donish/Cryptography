import org.example.DES.DES;
import org.example.cipher.CipherService;
import org.example.impl.padding_impl.ISO10126Padding;
import org.example.impl.padding_impl.ZerosPadding;
import org.example.utils.BitUtils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        BitUtils bitUtils = new BitUtils();

        byte[] source = new byte[3];
        source[0] = 5;
        source[1] = 3;
        source[2] = 103;

//        int num = 49478;
//        System.out.println(Integer.toBinaryString(num));
//        int res = BitUtils.lCircularShift(num, 16, 30);
//        System.out.println(res);
//        System.out.println(Integer.toBinaryString(res));
        long num = 1;
        num <<= 62;
        System.out.println(num);
        System.out.println(Long.toBinaryString(num));


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