/**
 * Lab Part 1: Singly linked list (Tasks 0-3 and Checkpoint 1).
 * References: Lafore Ch.5 (Simple Linked List); Goodrich & Tamassia Sec. 3.2.
 * Run:  javac SinglyLinkedList.java && java SinglyLinkedList
 */
public class SinglyLinkedList {

    // Task 0: the Node class (nested here so the file is self-contained)
    static class Node {
        int data;
        Node next;               // null for the last node

        Node(int data) {
            this.data = data;
        }
    }

    private Node head;           // entry point; null when the list is empty
    private int size;

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    // Task 1: O(1). New node points to the old first node, then head moves.
    public void insertFirst(int value) {
        Node n = new Node(value);
        n.next = head;           // step 1: link to old first
        head = n;                // step 2: head now points to the new node
        size++;
    }

    // Task 2: O(n) because there is no tail reference.
    public void insertLast(int value) {
        Node n = new Node(value);
        if (isEmpty()) {
            head = n;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;      // walk to the last node
            }
            current.next = n;
        }
        size++;
    }

    // Task 2: O(1).
    public int deleteFirst() {
        if (isEmpty()) {
            throw new RuntimeException("List empty");
        }
        int value = head.data;
        head = head.next;        // old first node is now unreachable
        size--;
        return value;
    }

    // Task 3: O(n) linear search.
    public boolean find(int key) {
        Node current = head;
        while (current != null) {
            if (current.data == key) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Task 3: remove the first node holding key. Returns false if absent.
    public boolean delete(int key) {
        if (isEmpty()) {
            return false;                    // case 1: empty list
        }
        if (head.data == key) {              // case 2: key in the first node
            head = head.next;
            size--;
            return true;
        }
        Node prev = head;                    // case 3: stop BEFORE the target
        while (prev.next != null) {
            if (prev.next.data == key) {
                prev.next = prev.next.next;  // bypass the target node
                size--;
                return true;
            }
            prev = prev.next;
        }
        return false;                        // case 4: not found
    }

    // Task 1: traversal. Prints e.g.  8 -> 5 -> 3 -> null
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Checkpoint 1 demo
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        System.out.println("--- Task 1: insertFirst and display ---");
        list.insertFirst(3);
        list.insertFirst(5);
        list.insertFirst(8);
        list.display();                      // 8 -> 5 -> 3 -> null

        System.out.println("--- Checkpoint 1 ---");
        list = new SinglyLinkedList();
        list.insertFirst(5);
        list.insertLast(9);
        list.insertFirst(2);
        list.display();                      // 2 -> 5 -> 9 -> null
        list.delete(5);
        list.display();                      // 2 -> 9 -> null
        list.deleteFirst();
        list.display();                      // 9 -> null
        System.out.println(list.find(9));    // true
        System.out.println("size = " + list.size());   // 1

        System.out.println("--- Edge cases ---");
        System.out.println(list.delete(42)); // false (not found)
        list.deleteFirst();                  // list is now empty
        System.out.println(list.delete(1));  // false (empty list)
        try {
            list.deleteFirst();              // underflow
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());   // List empty
        }
    }
}
