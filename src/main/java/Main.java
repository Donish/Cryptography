import org.example.interfaces.IBitUtils;
import org.example.padding_impl.ANSIX923Padding;
import org.example.padding_impl.ISO10126Padding;
import org.example.padding_impl.PKCS7Padding;
import org.example.padding_impl.ZerosPadding;
import org.example.utils.BitUtils;

public class Main {
    public static void main(String[] args) {
        BitUtils bitUtils = new BitUtils();

        byte[] source = new byte[3];
        source[0] = 5;
        source[1] = 3;
        source[2] = 103;

        ZerosPadding zerosPadding = new ZerosPadding();
        byte[] result_zeros = zerosPadding.padBlock(source, 64);
        System.out.println("ZEROS:");
        for (byte el : result_zeros) {
            System.out.print(el);
            System.out.print(" ");
        }
        System.out.println();
        System.out.println();


        byte[] source_rsa = new byte[150];
        for (int i = 0; i < 150; i++) {
            source_rsa[i] = (byte) (i + 1);
        }

        ISO10126Padding iso10126Padding = new ISO10126Padding();
        byte[] result_rsa = iso10126Padding.padBlock(source_rsa, 2048);
        System.out.println("ISO10126:");
        for (byte el : result_rsa) {
            System.out.print(el);
            System.out.print(" ");
        }
        System.out.println();
        System.out.print("Padded bytes: ");
        System.out.println(BitUtils.getUnsignedByte(result_rsa[result_rsa.length - 1]));

    }
}