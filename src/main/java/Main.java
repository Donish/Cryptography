import org.example.IBitOperator;
import org.example.impl.BitOperator;

public class Main {
    public static void main(String[] args) {
        BitOperator bitOperator = new BitOperator();

        byte[] source = new byte[3];
        source[0] = 5;
        source[1] = 3;
        source[2] = 103;

        int[] pBlock = {1, 5, 12, 8, 9, 21, 23, 5, 4, 9, 11, 0};

        byte[] permuted = bitOperator.bitPermutation(source, pBlock,
                IBitOperator.bitIndexing.junior_to_senior,
                IBitOperator.firstBit.ZERO);

        for (byte el : source) {
            System.out.println(el);
            System.out.println(Integer.toBinaryString(el));
        }

        for (byte el : permuted) {
            System.out.println(el);
            System.out.println(Integer.toBinaryString(el));
        }

    }
}