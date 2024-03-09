import org.example.impl.BitOperator;

public class Main {
    public static void main(String[] args) {
        BitOperator bitOperator = new BitOperator();

        byte[] source = new byte[3];
        source[0] = 5;
        source[1] = -3;
        source[2] = 103;
        System.out.println(Integer.toBinaryString(-5));
        System.out.println(Integer.toBinaryString(5));

        int[] pBlock = {1, 5, 12, 8, 15, 21, 23, 5, 4, 9, 11, 0};

        byte[] permuted = bitOperator.bitPermutation(source, pBlock,
                BitOperator.bitIndexing.junior_to_senior,
                BitOperator.firstBit.ZERO);

        for (byte el : permuted) {
            System.out.println(el);
            System.out.println(Integer.toBinaryString(el));
        }

    }
}