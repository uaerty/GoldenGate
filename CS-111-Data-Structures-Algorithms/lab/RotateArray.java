import java.util.Arrays;

/**
 * Challenge 3: Rotate an array to the right by k positions.
 * Reverse all, then reverse each part. Time: O(n).  Extra space: O(1).
 */
public class RotateArray {

    static void reverse(int[] a, int i, int j) {
        while (i < j) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            i++;
            j--;
        }
    }

    static void rotate(int[] a, int k) {
        int n = a.length;
        if (n == 0) return;
        k = k % n;
        reverse(a, 0, n - 1);       // reverse all
        reverse(a, 0, k - 1);       // reverse first k
        reverse(a, k, n - 1);       // reverse the rest
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        rotate(a, 3);
        System.out.println(Arrays.toString(a));     // [5, 6, 7, 1, 2, 3, 4]
    }
}
