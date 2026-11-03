/**
 * Challenge 1: Find the missing number in {0..n} using the sum formula.
 * Time: O(n).  Extra space: O(1).
 */
public class MissingNumber {

    /** a holds n distinct numbers from 0..n, one is missing. */
    static int findMissing(int[] a) {
        int n = a.length;
        long expected = (long) n * (n + 1) / 2;     // 0 + 1 + ... + n
        long actual = 0;
        for (int v : a) actual += v;
        return (int) (expected - actual);
    }

    public static void main(String[] args) {
        System.out.println(findMissing(new int[]{3, 0, 1}));            // 2
        System.out.println(findMissing(new int[]{0, 1, 2, 4, 5}));      // 3
    }
}
