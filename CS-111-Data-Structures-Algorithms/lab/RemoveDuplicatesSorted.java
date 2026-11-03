import java.util.Arrays;

/**
 * Challenge 2: Remove duplicates from a sorted array in place (write pointer).
 * Time: O(n).  Extra space: O(1).
 */
public class RemoveDuplicatesSorted {

    /** Returns the new length; the first 'length' items are unique. */
    static int removeDuplicates(int[] a) {
        if (a.length == 0) return 0;
        int write = 1;                              // next position to fill
        for (int read = 1; read < a.length; read++) {
            if (a[read] != a[read - 1]) {
                a[write] = a[read];
                write++;
            }
        }
        return write;
    }

    public static void main(String[] args) {
        int[] a = {1, 1, 2, 2, 2, 3, 5, 5};
        int len = removeDuplicates(a);
        System.out.println(Arrays.toString(Arrays.copyOf(a, len)));     // [1, 2, 3, 5]
    }
}
