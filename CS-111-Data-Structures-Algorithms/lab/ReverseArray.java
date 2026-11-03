import java.util.Arrays;

/**
 * Exercise 2: Reverse an array in place with two pointers.
 * Swaps: n/2.  Time: Theta(n).  Extra space: O(1).
 */
public class ReverseArray {

    static void reverse(int[] a) {
        int i = 0, j = a.length - 1;
        while (i < j) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] odd = {1, 2, 3, 4, 5};    // middle element stays in place
        int[] even = {1, 2, 3, 4};
        reverse(odd);
        reverse(even);
        System.out.println(Arrays.toString(odd));   // [5, 4, 3, 2, 1]
        System.out.println(Arrays.toString(even));  // [4, 3, 2, 1]
    }
}
