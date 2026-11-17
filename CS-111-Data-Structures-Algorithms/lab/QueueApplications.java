/**
 * Lab 7 - Task 2: Linked queue, Java's built-in Queue, and a print queue.
 * Compile: javac QueueApplications.java     Run: java QueueApplications
 */
import java.util.ArrayDeque;
import java.util.Queue;

public class QueueApplications {

    // ---------- Task 2A: queue on a linked list ----------
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    static class LinkedQueue {
        private Node head, tail;           // head = front, tail = rear
        private int size = 0;

        public void enqueue(int x) {
            Node n = new Node(x);
            if (tail == null) head = n;    // queue was empty
            else tail.next = n;
            tail = n;
            size++;
        }
        public int dequeue() {
            if (head == null) throw new RuntimeException("empty");
            int x = head.data;
            head = head.next;
            if (head == null) tail = null; // queue is now empty
            size--;
            return x;
        }
        public int first() {
            if (head == null) throw new RuntimeException("empty");
            return head.data;
        }
        public boolean isEmpty() { return head == null; }
        public int size() { return size; }
    }

    public static void main(String[] args) {
        // ---- Task 2A ----
        System.out.println("== Task 2A: linked queue ==");
        LinkedQueue lq = new LinkedQueue();
        lq.enqueue(5); lq.enqueue(15); lq.enqueue(25);
        System.out.println("first = " + lq.first() + ", size = " + lq.size());
        System.out.println("dequeue -> " + lq.dequeue());
        System.out.println("dequeue -> " + lq.dequeue());
        lq.enqueue(35);
        System.out.println("dequeue -> " + lq.dequeue());
        System.out.println("dequeue -> " + lq.dequeue());
        System.out.println("isEmpty = " + lq.isEmpty());

        // ---- Task 2B: java.util.Queue ----
        System.out.println("\n== Task 2B: java.util.Queue ==");
        Queue<String> q = new ArrayDeque<>();
        q.offer("A"); q.offer("B"); q.offer("C");
        System.out.println("peek = " + q.peek());
        System.out.println("poll = " + q.poll());
        System.out.println("poll = " + q.poll());
        System.out.println("size = " + q.size());
        System.out.println("poll = " + q.poll());
        System.out.println("poll = " + q.poll() + "   (empty queue gives null)");
        try { q.remove(); }
        catch (RuntimeException e) {
            System.out.println("remove() throws " + e.getClass().getSimpleName());
        }

        // ---- Task 2C: print queue with jobs arriving while printing ----
        System.out.println("\n== Task 2C: print queue ==");
        Queue<String> jobs = new ArrayDeque<>();
        jobs.offer("report.pdf");
        jobs.offer("photo.png");
        jobs.offer("notes.txt");
        int clock = 0;
        boolean lateJobAdded = false;
        while (!jobs.isEmpty()) {
            String job = jobs.poll();                 // oldest first
            clock += 2;                               // 2 seconds per job
            System.out.println(clock + "s: printed " + job);
            if (!lateJobAdded) {                      // a new job arrives mid-run
                jobs.offer("late.docx");
                lateJobAdded = true;
            }
        }
    }
}
