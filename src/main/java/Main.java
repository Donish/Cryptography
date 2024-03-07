import org.example.IBitOperator;
import org.example.impl.BitOperator;

public class Main {
    public static void main(String[] args) {
        BitOperator bitOperator = new BitOperator();

        byte[] source = new byte[1];
        source[0] = 103;

        int[] pBlock = {2, 4, 7, 6, 3, 1, 5, 8, 7, 1, 2, 4, 5, 4, 2, 6, 3};

        byte[] permuted = bitOperator.bitPermutation(source, pBlock,
                IBitOperator.bitIndexing.junior_to_senior,
                IBitOperator.firstBit.ONE);

        for (byte el : permuted) {
            System.out.println(el);
            System.out.println(Integer.toBinaryString(el));
        }

    }
}