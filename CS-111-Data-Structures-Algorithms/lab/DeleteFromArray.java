import java.util.Arrays;

/**
 * Exercise 5: Delete an element by index and count the shifts.
 * Worst case (index 0): n - 1 shifts. Time: O(n).
 */
public class DeleteFromArray {

    /** Deletes a[pos] from the first 'size' items. Returns the number of shifts. */
    static int delete(int[] a, int size, int pos) {
        int shifts = 0;
        for (int i = pos; i < size - 1; i++) {
            a[i] = a[i + 1];                    // shift left to close the gap
            shifts++;
        }
        return shifts;
    }

    public static void main(String[] args) {
        int[] a = {10, 20, 30, 40, 50, 60, 70, 80};
        int size = a.length;

        int shifts = delete(a, size, 0);        // delete first element
        size--;
        System.out.println("Delete a[0]: shifts = " + shifts           // 7
                + ", array = " + Arrays.toString(Arrays.copyOf(a, size)));

        shifts = delete(a, size, size - 1);     // delete last element
        size--;
        System.out.println("Delete last: shifts = " + shifts           // 0
                + ", array = " + Arrays.toString(Arrays.copyOf(a, size)));
    }
}
