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

        byte[] source2 = new byte[3];
        source2[0] = 10;
        source2[1] = 23;
        source2[2] = 56;
        List<byte[]> list = new ArrayList<>();
        list.add(source);
        list.add(source2);

        byte[] text = new byte[10];
        for (int i = 0; i < 10; i++) {
            text[i] = (byte) (i + 1);
        }

        int num = -8;
        System.out.println(Integer.toBinaryString(num));
        int other = num >> 1;
        System.out.println(other);
        System.out.println(Integer.toBinaryString(other));

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