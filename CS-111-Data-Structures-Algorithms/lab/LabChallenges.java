/**
 * Lab 7 and 8 - Challenge tasks (student starter file).
 * Replace every TODO, then compile and run. Expected output is on the challenge slide.
 * Compile: javac LabChallenges.java     Run: java LabChallenges
 */
import java.util.ArrayDeque;
import java.util.Queue;

public class LabChallenges {

    // Challenge 1: Josephus game. n people stand in a circle numbered 1..n.
    // Every k-th person is removed until one is left. Return the survivor.
    // Hint: use a Queue. Move k-1 people from the front to the back, then remove the next one.
    static int josephus(int n, int k) {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) q.offer(i);
        // TODO 1: while more than one person is left, rotate k-1 times, then poll once
        return q.peek();
    }

    // Challenge 2: fast power in O(log n) calls.
    // x^n = (x^(n/2))^2 when n is even, and x * (x^(n/2))^2 when n is odd.
    static long fastPower(long x, int n) {
        // TODO 2: base case n == 0, then one recursive call on n / 2
        return -1;
    }

    // Challenge 3: reverse a queue using recursion (no extra structure).
    // Idea: remove the front, reverse the rest, then add the front at the back.
    static void reverse(Queue<Integer> q) {
        // TODO 3: base case: empty queue; recursive case as in the idea above
    }

    // Challenge 4: count the digits of a positive number recursively.
    static int digits(int n) {
        // TODO 4: base case n < 10; otherwise 1 + digits(n / 10)
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("josephus(7, 3) = " + josephus(7, 3));
        System.out.println("fastPower(2, 10) = " + fastPower(2, 10));
        System.out.println("fastPower(3, 5) = " + fastPower(3, 5));

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= 5; i++) q.offer(i);
        reverse(q);
        System.out.println("reversed queue = " + q);

        System.out.println("digits(90210) = " + digits(90210));
    }
}
