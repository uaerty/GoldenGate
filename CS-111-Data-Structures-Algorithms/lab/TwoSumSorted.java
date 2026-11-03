import java.util.Arrays;

/**
 * Challenge 4: Two-sum on a sorted array using two pointers.
 * Time: O(n).  Extra space: O(1).
 */
public class TwoSumSorted {

    /** Returns the two indices whose values add up to target, or null. */
    static int[] twoSum(int[] a, int target) {
        int i = 0, j = a.length - 1;
        while (i < j) {
            int sum = a[i] + a[j];
            if (sum == target) return new int[]{i, j};
            if (sum < target) i++;                  // need a bigger sum
            else j--;                               // need a smaller sum
        }
        return null;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 4, 6, 8, 11};
        System.out.println(Arrays.toString(twoSum(a, 10)));     // [2, 3]  (4 + 6)
        System.out.println(Arrays.toString(twoSum(a, 100)));    // null
    }
}
