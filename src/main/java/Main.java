import org.example.interfaces.IBitUtils;
import org.example.utils.BitUtils;

public class Main {
    public static void main(String[] args) {
        BitUtils bitUtils = new BitUtils();

        byte[] source = new byte[3];
        source[0] = 5;
        source[1] = 3;
        source[2] = 103;

        int[] pBlock = {4, 7, 6, 3, 1, 5, 8, 7, 6, 4, 6, 6, 6, 6, 6, 4};

        byte[] permuted = bitUtils.bitPermutation(source, pBlock,
                BitUtils.BitIndexing.JUNIOR_TO_SENIOR,
                BitUtils.FirstBit.ONE);

        for (byte el : permuted) {
            System.out.println(el);
            System.out.println(Integer.toBinaryString(el));
        }

    }
}