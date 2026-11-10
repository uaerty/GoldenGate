/**
 * Lab Part 3: A stack (LIFO) built on a singly linked list (Task 5).
 * The top of the stack is the head of the list, because that end is O(1).
 * References: Lafore Ch.5 (A Stack Implemented by a Linked List);
 *             Goodrich & Tamassia Sec. 6.1.3.
 * Run:  javac LinkedStack.java && java LinkedStack
 */
public class LinkedStack {

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head;                       // head = top of the stack
    private int size;

    public void push(int x) {                // O(1) same as insertFirst
        Node n = new Node(x);
        n.next = head;
        head = n;
        size++;
    }

    public int pop() {                       // same as deleteFirst
        if (isEmpty()) {
            throw new RuntimeException("Stack empty");   // underflow
        }
        int x = head.data;
        head = head.next;
        size--;
        return x;
    }

    public int peek() {                      // read the top, do not remove
        if (isEmpty()) {
            throw new RuntimeException("Stack empty");
        }
        return head.data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {                      // O(1) using a counter
        return size;
    }

    public static void main(String[] args) {
        LinkedStack st = new LinkedStack();

        System.out.println("--- Task 5 checkpoint ---");
        st.push(10);
        st.push(20);
        st.push(30);
        System.out.println(st.pop());        // 30
        System.out.println(st.peek());       // 20
        System.out.println(st.pop());        // 20
        System.out.println(st.pop());        // 10
        System.out.println(st.isEmpty());    // true
        try {
            st.pop();                        // underflow
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());   // Stack empty
        }

        System.out.println("--- Trace from the lecture ---");
        st.push(5);
        st.push(8);
        st.push(3);
        System.out.println("pop  -> " + st.pop());    // 3
        System.out.println("peek -> " + st.peek());   // 8
        System.out.println("pop  -> " + st.pop());    // 8
        System.out.println("size -> " + st.size());   // 1
    }
}
