/**
 * Exercise 4: Binary search (loop version). The array MUST be sorted.
 * Each step halves the range. Time: O(log n).
 */
public class BinarySearch {

    static long comparisons = 0;

    /** Returns the index of key, or -1 if absent. */
    static int binarySearch(int[] a, int key) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;   // avoids int overflow
            comparisons++;
            if (a[mid] == key) return mid;
            if (a[mid] < key) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = new int[16];
        for (int i = 0; i < a.length; i++) a[i] = i * 2;   // 0, 2, 4, ... 30

        comparisons = 0;
        System.out.println("Key 22: index " + binarySearch(a, 22)
                + ", comparisons = " + comparisons);

        comparisons = 0;
        System.out.println("Key 99 (absent): index " + binarySearch(a, 99)
                + ", comparisons = " + comparisons);
    }
}
