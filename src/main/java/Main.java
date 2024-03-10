import org.example.impl.BitOperator;

public class Main {
    public static void main(String[] args) {
        BitOperator bitOperator = new BitOperator();

        byte[] source = new byte[3];
        source[0] = 5;
        source[1] = 3;
        source[2] = 103;

        int[] pBlock = {4, 7, 6, 3, 1, 5, 8, 7, 6, 4, 6, 6, 6, 6, 6, 6};

        byte[] permuted = bitOperator.bitPermutation(source, pBlock,
                BitOperator.BitIndexing.SENIOR_TO_JUNIOR,
                BitOperator.FirstBit.ONE);

        for (byte el : permuted) {
            System.out.println(el);
            System.out.println(Integer.toBinaryString(el));
        }

    }
}