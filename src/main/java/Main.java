import org.example.IBitOperator;
import org.example.impl.BitOperator;

public class Main {
    public static void main(String[] args) {
        BitOperator bitOperator = new BitOperator();

        byte[] source = new byte[3];
        source[0] = 5;
        source[1] = 3;
        source[2] = 103;

        int[] pBlock = {22, 23, 21, 20, 22, 23, 20, 1, 5, 12, 8, 9, 21, 23, 5, 4, 9, 11, 1};

        byte[] permuted = bitOperator.bitPermutation(source, pBlock,
                IBitOperator.bitIndexing.senior_to_junior,
                IBitOperator.firstBit.ZERO);

        for (byte el : permuted) {
            System.out.println(el);
            System.out.println(Integer.toBinaryString(el));
        }

    }
}