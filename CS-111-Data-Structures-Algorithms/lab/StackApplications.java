import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Lab Part 4: Stack applications (Tasks 6 and 7 and the postfix extension).
 * Uses java.util.ArrayDeque, Java's recommended stack (the old Stack class is legacy).
 * References: Lafore Ch.4 (Reversing a Word, Delimiter Matching, Postfix);
 *             Goodrich & Tamassia Sec. 6.1.4 and 6.1.5.
 * Run:  javac StackApplications.java && java StackApplications
 */
public class StackApplications {

    // Task 6: push every letter, then pop them: LIFO reverses the order O(n).
    public static String reverse(String s) {
        Deque<Character> st = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            st.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.append(st.pop());
        }
        return sb.toString();
    }

    // Task 7: opener -> push; closer -> pop and compare; stack must end empty.
    public static boolean balanced(String s) {
        Deque<Character> st = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                st.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (st.isEmpty()) {
                    return false;                // closer with nothing open
                }
                char open = st.pop();
                if (!matches(open, c)) {
                    return false;                // wrong kind of bracket
                }
            }
        }
        return st.isEmpty();                     // leftover openers => false
    }

    static boolean matches(char o, char c) {
        return (o == '(' && c == ')')
            || (o == '[' && c == ']')
            || (o == '{' && c == '}');
    }

    // Extension: evaluate a postfix expression with single-digit or multi-digit
    // integers separated by spaces, e.g. "5 1 2 + 4 * + 3 -" gives 14.
    public static int evalPostfix(String expr) {
        Deque<Integer> st = new ArrayDeque<>();
        for (String tok : expr.trim().split("\\s+")) {
            switch (tok) {
                case "+": case "-": case "*": case "/": {
                    int right = st.pop();        // first pop = right operand
                    int left = st.pop();
                    switch (tok) {
                        case "+": st.push(left + right); break;
                        case "-": st.push(left - right); break;
                        case "*": st.push(left * right); break;
                        default:  st.push(left / right); break;
                    }
                    break;
                }
                default:
                    st.push(Integer.parseInt(tok));
            }
        }
        return st.pop();
    }

    public static void main(String[] args) {
        System.out.println("--- Task 6: reverse ---");
        for (String w : new String[] {"STACK", "level", ""}) {
            System.out.println("\"" + w + "\" -> \"" + reverse(w) + "\"");
        }

        System.out.println("--- Task 7: balanced brackets ---");
        String[] tests = {"(a+b)*c", "[(a+b)*c]", "(a+b]", "((a+b)", "a+b)", "{[()]}", "([)]"};
        for (String t : tests) {
            System.out.println(t + "  ->  " + balanced(t));
        }

        System.out.println("--- Extension: postfix ---");
        System.out.println("3 4 + 2 *            = " + evalPostfix("3 4 + 2 *"));              // 14
        System.out.println("5 1 2 + 4 * + 3 -    = " + evalPostfix("5 1 2 + 4 * + 3 -"));      // 14
    }
}
