import java.util.Arrays;

/**
 * Exercise 1: Statistics of an array (min, max, average).
 * Time: Theta(n) - one pass.  Extra space: O(1).
 */
public class MinMaxAverage {

    static void stats(int[] a) {
        if (a.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        int min = a[0], max = a[0];
        long sum = 0;
        for (int v : a) {
            if (v < min) min = v;
            if (v > max) max = v;
            sum += v;
        }
        double avg = (double) sum / a.length;
        System.out.println(Arrays.toString(a)
                + " -> min=" + min + ", max=" + max + ", avg=" + avg);
    }

    public static void main(String[] args) {
        stats(new int[]{4, 9, 2, 7});   // min 2, max 9, avg 5.5
        stats(new int[]{5});            // single item
        stats(new int[]{3, 3, 3});      // duplicates
        // stats(new int[]{});          // empty -> exception
    }
}
