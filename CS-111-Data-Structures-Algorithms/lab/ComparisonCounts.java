/**
 * Slide 12: Record your comparison counts.
 * Worst-case comparisons for linear and binary search on sorted arrays.
 * Binary expected value = floor(log2 n) + 1.
 */
public class ComparisonCounts {

    static int linearComparisons(int[] a, int key) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            count++;
            if (a[i] == key) return count;
        }
        return count;
    }

    static int binaryComparisons(int[] a, int key) {
        int count = 0, lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            count++;
            if (a[mid] == key) return count;
            if (a[mid] < key) lo = mid + 1;
            else hi = mid - 1;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] sizes = {1_000, 10_000, 100_000, 1_000_000};
        System.out.printf("%-12s %-10s %-10s%n", "n", "linear", "binary");
        for (int n : sizes) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = i;
            int absent = n;                        // key larger than every item (worst case)
            System.out.printf("%-12d %-10d %-10d%n", n,
                    linearComparisons(a, absent), binaryComparisons(a, absent));
        }
    }
}
