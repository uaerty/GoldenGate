import java.util.Arrays;

/**
 * Exercise 5b: Insert into a sorted array (keeps it sorted).
 * Best case 0 shifts (x is largest). Worst case n shifts (x is smallest). Time: O(n).
 */
public class InsertIntoSortedArray {

    /** Inserts x into a (sorted, first 'size' items, size < a.length). Returns shifts. */
    static int insert(int[] a, int size, int x) {
        int shifts = 0;
        int i = size - 1;
        while (i >= 0 && a[i] > x) {
            a[i + 1] = a[i];                    // shift larger items right
            i--;
            shifts++;
        }
        a[i + 1] = x;
        return shifts;
    }

    public static void main(String[] args) {
        int[] a = new int[10];
        int[] start = {5, 8, 12, 20};
        System.arraycopy(start, 0, a, 0, start.length);
        int size = start.length;

        int shifts = insert(a, size, 10);
        size++;
        System.out.println(Arrays.toString(Arrays.copyOf(a, size))
                + "  shifts = " + shifts);      // [5, 8, 10, 12, 20]  shifts = 2
    }
}
