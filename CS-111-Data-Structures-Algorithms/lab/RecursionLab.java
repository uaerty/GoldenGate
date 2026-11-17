/**
 * Lab 8 - Task 3: Recursion basics, call stack, cost and pitfalls.
 * Compile: javac RecursionLab.java     Run: java RecursionLab
 */
public class RecursionLab {

    // ---------- Task 3A: factorial with a visible call stack ----------
    static void say(int depth, String msg) {
        System.out.println("  ".repeat(depth) + msg);
    }

    static long factorial(int n, int depth) {
        say(depth, "call factorial(" + n + ")");
        if (n == 0) {                                  // base case
            say(depth, "base case: return 1");
            return 1;
        }
        long r = n * factorial(n - 1, depth + 1);      // recursive case
        say(depth, "return " + r);
        return r;
    }

    // ---------- Task 3B: array sum and power ----------
    static int sum(int[] a, int n) {
        if (n == 0) return 0;                          // sum of zero numbers
        return a[n - 1] + sum(a, n - 1);               // last item + the rest
    }

    static long power(long x, int n) {
        if (n == 0) return 1;
        return x * power(x, n - 1);
    }

    // ---------- Task 3C: Fibonacci, naive versus memoized ----------
    static long calls;

    static long fib(int n) {
        calls++;
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
    }

    static long fibMemo(int n, long[] memo) {
        calls++;
        if (n <= 1) return n;
        if (memo[n] != 0) return memo[n];
        memo[n] = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
        return memo[n];
    }

    // ---------- Task 3D: recursion run amok ----------
    static int depth = 0;

    static void noBaseCase(int n) {
        depth++;
        noBaseCase(n + 1);                             // never stops
    }

    public static void main(String[] args) {
        System.out.println("== Task 3A: factorial(4) call stack ==");
        System.out.println("Result: " + factorial(4, 0));

        System.out.println("\n== Task 3B: sum and power ==");
        int[] a = {4, 8, 15, 16};
        System.out.println("sum(a, 4) = " + sum(a, 4));
        System.out.println("sum(a, 2) = " + sum(a, 2));
        System.out.println("power(2, 10) = " + power(2, 10));

        System.out.println("\n== Task 3C: Fibonacci calls ==");
        System.out.printf("%-4s %-10s %-14s %-12s%n", "n", "fib(n)", "naive calls", "memo calls");
        for (int n : new int[]{5, 10, 20, 30}) {
            calls = 0;
            long v = fib(n);
            long naive = calls;
            calls = 0;
            fibMemo(n, new long[n + 1]);
            System.out.printf("%-4d %-10d %-14d %-12d%n", n, v, naive, calls);
        }

        System.out.println("\n== Task 3D: no base case ==");
        try {
            noBaseCase(0);
        } catch (StackOverflowError e) {
            System.out.println("Caught StackOverflowError after a deep chain of calls");
            System.out.println("Depth reached > 1000? " + (depth > 1000));
        }
    }
}
