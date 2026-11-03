/**
 * Exercise 4b: Binary search (recursive version). The array MUST be sorted.
 * Recursion depth is about log2(n). Time: O(log n).
 */
public class BinarySearchRecursive {

    static int search(int[] a, int key, int lo, int hi) {
        if (lo > hi) return -1;                 // base case: empty range
        int mid = lo + (hi - lo) / 2;
        if (a[mid] == key) return mid;
        if (a[mid] < key)
            return search(a, key, mid + 1, hi);
        return search(a, key, lo, mid - 1);
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7, 9, 11, 13};
        System.out.println("Key 9  -> " + search(a, 9, 0, a.length - 1));   // 4
        System.out.println("Key 4  -> " + search(a, 4, 0, a.length - 1));   // -1
    }
}
