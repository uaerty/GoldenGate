import java.util.Arrays;

/**
 * Exercise 7: Time linear and binary search.
 * Run each search 5 times and take the median. The first run is slower (JVM warm-up).
 * Counts are exact; timings are noisy.
 */
public class TimingExperiment {

    static int linearSearch(int[] a, int key) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key) return i;
        }
        return -1;
    }

    static int binarySearch(int[] a, int key) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] == key) return mid;
            if (a[mid] < key) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }

    static long medianTime(boolean linear, int[] a, int key) {
        long[] times = new long[5];
        for (int run = 0; run < times.length; run++) {
            long t0 = System.nanoTime();
            int r = linear ? linearSearch(a, key) : binarySearch(a, key);
            long t1 = System.nanoTime();
            times[run] = t1 - t0;
            if (r == -2) System.out.println();      // stops the JIT removing the call
        }
        Arrays.sort(times);
        return times[times.length / 2];
    }

    public static void main(String[] args) {
        System.out.printf("%-12s %-14s %-14s%n", "n", "linear (ns)", "binary (ns)");
        for (int n : new int[]{100_000, 1_000_000, 10_000_000}) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = i;
            int absent = -1;                        // worst case for both searches
            System.out.printf("%-12d %-14d %-14d%n", n,
                    medianTime(true, a, absent), medianTime(false, a, absent));
        }
    }
}
