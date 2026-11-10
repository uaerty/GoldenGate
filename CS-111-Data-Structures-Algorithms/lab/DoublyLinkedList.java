/**
 * Lab Part 2: Doubly linked list with head AND tail (Task 4).
 * References: Lafore Ch.5 (Doubly Linked Lists); Goodrich & Tamassia Sec. 3.4.
 * Run:  javac DoublyLinkedList.java && java DoublyLinkedList
 */
public class DoublyLinkedList {

    static class DNode {
        int data;
        DNode prev, next;

        DNode(int data) {
            this.data = data;
        }
    }

    private DNode head, tail;

    public boolean isEmpty() {
        return head == null;
    }

    // Fixes 3 links (4 if the list was empty): n.next, head.prev, head (and tail).
    public void insertFirst(int value) {
        DNode n = new DNode(value);
        n.next = head;
        if (head != null) {
            head.prev = n;
        } else {
            tail = n;                // list was empty
        }
        head = n;
    }

    // Mirror image of insertFirst. O(1) thanks to the tail reference.
    public void insertLast(int value) {
        DNode n = new DNode(value);
        n.prev = tail;
        if (tail != null) {
            tail.next = n;
        } else {
            head = n;                // list was empty
        }
        tail = n;
    }

    public int deleteFirst() {
        if (isEmpty()) {
            throw new RuntimeException("List empty");
        }
        int v = head.data;
        if (head == tail) {          // only one node
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        return v;
    }

    // O(1) here; it was O(n) in the singly linked list.
    public int deleteLast() {
        if (isEmpty()) {
            throw new RuntimeException("List empty");
        }
        int v = tail.data;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;        // tail moves back one node
            tail.next = null;
        }
        return v;
    }

    public void displayForward() {
        DNode current = head;
        System.out.print("null <-> ");
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public void displayBackward() {
        DNode current = tail;
        System.out.print("null <-> ");
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.prev;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        System.out.println("--- Task 4: both ends ---");
        list.insertLast(1);
        list.insertLast(2);
        list.insertLast(3);
        list.displayForward();               // 1 <-> 2 <-> 3
        list.displayBackward();              // 3 <-> 2 <-> 1

        list.insertFirst(0);
        list.displayForward();               // 0 <-> 1 <-> 2 <-> 3

        System.out.println("deleteLast  -> " + list.deleteLast());    // 3
        System.out.println("deleteFirst -> " + list.deleteFirst());   // 0
        list.displayForward();               // 1 <-> 2
        list.displayBackward();              // 2 <-> 1

        System.out.println("--- Edge cases ---");
        list.deleteLast();
        list.deleteLast();                   // list is now empty
        list.displayForward();               // null <-> null
        try {
            list.deleteFirst();
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());          // List empty
        }
        list.insertFirst(7);                 // single node: head == tail
        list.displayForward();
        list.displayBackward();
    }
}
