/**
 * Lab 7 - Task 1: From a simple array queue to a circular queue.
 * Compile: javac CircularQueueLab.java     Run: java CircularQueueLab
 */
public class CircularQueueLab {

    // ---------- Task 1A: simple array queue (front and rear only move up) ----------
    static class SimpleArrayQueue {
        private int[] data;
        private int front = 0;    // next item to remove
        private int rear = -1;    // last item inserted

        SimpleArrayQueue(int max) { data = new int[max]; }

        boolean insert(int x) {
            if (rear == data.length - 1) return false;   // looks full
            data[++rear] = x;
            return true;
        }
        int remove() { return data[front++]; }
        int freeCells() { return data.length - (rear + 1) + front; }
    }

    // ---------- Task 1B: circular queue ----------
    static class CircularQueue {
        private int[] data;
        private int front = 0;    // index of oldest item
        private int rear = -1;    // index of newest item
        private int nItems = 0;   // current count

        CircularQueue(int max) { data = new int[max]; }

        public void insert(int x) {
            if (isFull()) throw new RuntimeException("full");
            rear = (rear + 1) % data.length;       // wrap
            data[rear] = x;
            nItems++;
        }
        public int remove() {
            if (isEmpty()) throw new RuntimeException("empty");
            int item = data[front];
            front = (front + 1) % data.length;     // wrap
            nItems--;
            return item;
        }
        public int peek() {
            if (isEmpty()) throw new RuntimeException("empty");
            return data[front];
        }
        public boolean isEmpty() { return nItems == 0; }
        public boolean isFull()  { return nItems == data.length; }
        public int size()        { return nItems; }

        // Task 1D: print the items from front to rear
        public void display() {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < nItems; i++) {
                if (i > 0) sb.append(' ');
                sb.append(data[(front + i) % data.length]);
            }
            System.out.println(sb.append("]  size=" + nItems));
        }

        // Task 1C: one line of the trace table
        public void trace(String op) {
            StringBuilder arr = new StringBuilder();
            // cells that hold live items are shown, the rest as _
            boolean[] live = new boolean[data.length];
            for (int i = 0; i < nItems; i++) live[(front + i) % data.length] = true;
            for (int i = 0; i < data.length; i++)
                arr.append(live[i] ? String.valueOf(data[i]) : "_").append(' ');
            System.out.printf("%-24s front=%d rear=%2d nItems=%d  %s%n",
                    op, front, rear, nItems, arr.toString().trim());
        }
    }

    public static void main(String[] args) {
        // ---- Task 1A ----
        System.out.println("== Task 1A: simple array queue ==");
        SimpleArrayQueue s = new SimpleArrayQueue(5);
        for (int x : new int[]{44, 63, 21, 79, 10}) s.insert(x);
        s.remove(); s.remove(); s.remove(); s.remove();     // free cells 0..3
        boolean ok = s.insert(99);
        System.out.println("insert(99) succeeded? " + ok
                + "   free cells = " + s.freeCells());

        // ---- Task 1B + 1C: the trace from Session 7, slide 13 ----
        System.out.println("\n== Task 1B/1C: circular queue trace ==");
        CircularQueue q = new CircularQueue(5);
        q.trace("new CircularQueue(5)");
        for (int x : new int[]{10, 20, 30, 40}) q.insert(x);
        q.trace("insert 10,20,30,40");
        q.remove(); q.remove(); q.remove();
        q.trace("remove three times");
        q.insert(50);
        q.trace("insert 50");
        q.insert(60);
        q.trace("insert 60 (rear wraps)");
        q.insert(70); q.insert(80);
        q.trace("insert 70, 80");

        // ---- Task 1D: display, full and empty ----
        System.out.println("\n== Task 1D: display, full, empty ==");
        q.display();
        System.out.println("isFull? " + q.isFull());
        try { q.insert(90); }
        catch (RuntimeException e) { System.out.println("insert(90) -> " + e.getMessage()); }
        System.out.print("remove all: ");
        while (!q.isEmpty()) System.out.print(q.remove() + " ");
        System.out.println();
        try { q.remove(); }
        catch (RuntimeException e) { System.out.println("remove()   -> " + e.getMessage()); }
    }
}
