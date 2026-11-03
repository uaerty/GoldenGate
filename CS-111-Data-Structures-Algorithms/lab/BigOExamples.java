/**
 * Exercise 6: What is the Big-O? Run each loop and count the steps.
 * Try n = 100, 200, 400 and see how the count grows.
 */
public class BigOExamples {

    static long snippet1(int n) {               // i += 2        -> O(n)
        long steps = 0;
        for (int i = 0; i < n; i += 2) steps++;
        return steps;
    }

    static long snippet2(int n) {               // nested to n   -> O(n^2)
        long steps = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) steps++;
        return steps;
    }

    static long snippet3(int n) {               // i *= 2        -> O(log n)
        long steps = 0;
        for (int i = 1; i < n; i *= 2) steps++;
        return steps;
    }

    static long snippet4(int n) {               // j < i         -> O(n^2)
        long steps = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < i; j++) steps++;
        return steps;
    }

    static long snippet5(int n) {               // two loops     -> O(n)
        long steps = 0;
        for (int i = 0; i < n; i++) steps++;
        for (int i = 0; i < n; i++) steps++;
        return steps;
    }

    public static void main(String[] args) {
        System.out.printf("%-6s %-9s %-9s %-9s %-9s %-9s%n",
                "n", "1:n/2", "2:n^2", "3:log n", "4:n^2/2", "5:2n");
        for (int n : new int[]{100, 200, 400}) {
            System.out.printf("%-6d %-9d %-9d %-9d %-9d %-9d%n", n,
                    snippet1(n), snippet2(n), snippet3(n), snippet4(n), snippet5(n));
        }
    }
}
