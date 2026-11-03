import java.util.Arrays;

/**
 * Exercise 2b: Second largest distinct value in one pass (no sorting).
 * Time: Theta(n).  Extra space: O(1).
 */
public class SecondLargest {

    /** Returns the second largest distinct value, or null if there is none. */
    static Integer secondLargest(int[] a) {
        Integer first = null, second = null;
        for (int v : a) {
            if (first == null || v > first) {
                second = first;
                first = v;
            } else if (v != first && (second == null || v > second)) {
                second = v;
            }
        }
        return second;
    }

    public static void main(String[] args) {
        int[][] tests = {{4, 9, 2, 7}, {5, 5, 5}, {8}, {1, 2}};
        for (int[] t : tests) {
            System.out.println(Arrays.toString(t) + " -> " + secondLargest(t));
        }
        // {4, 9, 2, 7} -> 7,  {5, 5, 5} -> null
    }
}
