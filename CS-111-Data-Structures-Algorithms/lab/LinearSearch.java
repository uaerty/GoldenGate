/**
 * Exercise 3: Linear search with a comparison counter.
 * Best case: 1 comparison.  Worst case: n comparisons.  Time: O(n).
 */
public class LinearSearch {

    static long comparisons = 0;

    /** Returns the index of key, or -1 if absent. */
    static int linearSearch(int[] a, int key) {
        for (int i = 0; i < a.length; i++) {
            comparisons++;
            if (a[i] == key) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {4, 9, 2, 7, 15, 3};

        comparisons = 0;
        System.out.println("Best case (key 4): index " + linearSearch(a, 4)
                + ", comparisons = " + comparisons);

        comparisons = 0;
        System.out.println("Worst case (key 99): index " + linearSearch(a, 99)
                + ", comparisons = " + comparisons);
    }
}
