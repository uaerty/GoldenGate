/**
 * Lab 8 - Task 4: Divide and conquer - binary search, Towers of Hanoi, merge sort.
 * Compile: javac DivideAndConquerLab.java     Run: java DivideAndConquerLab
 */
import java.util.Arrays;

public class DivideAndConquerLab {

    // ---------- Task 4A: recursive binary search with a trace ----------
    static int probes;

    static int find(int[] a, int key, int lo, int hi) {
        probes++;
        if (lo > hi) return -1;                        // not found
        int mid = (lo + hi) / 2;
        System.out.println("lo=" + lo + " hi=" + hi + " mid=" + mid + " a[mid]=" + a[mid]);
        if (a[mid] == key) return mid;                 // found
        if (key < a[mid]) return find(a, key, lo, mid - 1);
        return find(a, key, mid + 1, hi);
    }

    // ---------- Task 4B: Towers of Hanoi ----------
    static int moves;

    static void hanoi(int n, char from, char via, char to) {
        if (n == 0) return;                            // base case
        hanoi(n - 1, from, to, via);                   // 1. clear the way
        moves++;
        System.out.println("Disk " + n + ": " + from + " -> " + to);
        hanoi(n - 1, via, from, to);                   // 3. restack
    }

    static int countHanoi(int n) {
        if (n == 0) return 0;
        return 2 * countHanoi(n - 1) + 1;
    }

    // ---------- Task 4C: merge sort ----------
    static int mergeCount;

    static void show(int level, String what, int[] a, int lo, int hi) {
        System.out.println("  ".repeat(level) + what + " "
            + Arrays.toString(Arrays.copyOfRange(a, lo, hi + 1)));
    }

    static void mergeSort(int[] a, int lo, int hi, int level) {
        if (lo >= hi) return;                          // base case
        int mid = (lo + hi) / 2;
        show(level, "split", a, lo, hi);
        mergeSort(a, lo, mid, level + 1);
        mergeSort(a, mid + 1, hi, level + 1);
        merge(a, lo, mid, hi);                         // combine
        show(level, "merge", a, lo, hi);
    }

    static void merge(int[] a, int lo, int mid, int hi) {
        int[] t = new int[hi - lo + 1];
        int i = lo, j = mid + 1, k = 0;
        while (i <= mid && j <= hi) {
            mergeCount++;
            t[k++] = a[i] <= a[j] ? a[i++] : a[j++];
        }
        while (i <= mid) t[k++] = a[i++];
        while (j <= hi)  t[k++] = a[j++];
        for (k = 0; k < t.length; k++) a[lo + k] = t[k];
    }

    public static void main(String[] args) {
        System.out.println("== Task 4A: binary search for 44 ==");
        int[] sorted = {4, 9, 15, 21, 30, 36, 44, 51};
        probes = 0;
        System.out.println("found at index " + find(sorted, 44, 0, sorted.length - 1)
                + " after " + probes + " calls");
        probes = 0;
        System.out.println("search 5: index " + find(sorted, 5, 0, sorted.length - 1)
                + " after " + probes + " calls");

        System.out.println("\n== Task 4B: Towers of Hanoi, 3 disks ==");
        moves = 0;
        hanoi(3, 'A', 'B', 'C');
        System.out.println("Total moves: " + moves);
        System.out.println("n  moves(2^n - 1)");
        for (int n = 1; n <= 5; n++)
            System.out.println(n + "  " + countHanoi(n));

        System.out.println("\n== Task 4C: merge sort ==");
        int[] data = {38, 27, 43, 3, 9, 82, 10, 5};
        mergeCount = 0;
        mergeSort(data, 0, data.length - 1, 0);
        System.out.println("Sorted: " + Arrays.toString(data));
        System.out.println("Comparisons in merge: " + mergeCount);

        System.out.println("\n== Task 4D: how much work? ==");
        System.out.printf("%-10s %-14s %-12s%n", "n", "linear (max)", "binary (max)");
        for (int n : new int[]{8, 1000, 1_000_000}) {
            int binary = 1;
            for (int m = n; m > 1; m /= 2) binary++;
            System.out.printf("%-10d %-14d %-12d%n", n, n, binary);
        }
    }
}
