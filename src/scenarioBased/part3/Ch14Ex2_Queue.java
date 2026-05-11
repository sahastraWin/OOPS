package scenarioBased.part3;/*
 * Chapter 14 (Templates & Exceptions), Exercise 2 (Starred):
 * A queue is a data-storage device (FIFO — first-in, first-out), like the line at a bank
 * teller's window. If you put in 1, 2, 3, you get back 1, 2, 3 in that order.
 *
 * A queue must keep track of two indexes: one to the tail (where new items are added),
 * and one to the head (where old items are removed). The tail follows the head through
 * the array. If either the tail or the head reaches the end of the array, it is reset to
 * the beginning (circular buffer).
 *
 * Write a class template for a queue class. Assume that the programmer using the queue
 * won't make any mistakes (like exceeding capacity or removing from an empty queue).
 * Define several queues of different data types and insert and remove data from them.
 */

public class Ch14Ex2_Queue {

    static class Queue<T> {
        private Object[] data;
        private int head;    // index where next item is removed
        private int tail;    // index where next item is inserted
        private int count;
        private int capacity;

        public Queue(int capacity) {
            this.capacity = capacity;
            data  = new Object[capacity];
            head  = 0;
            tail  = 0;
            count = 0;
        }

        public void enqueue(T item) {
            data[tail] = item;
            tail = (tail + 1) % capacity;
            count++;
        }

        @SuppressWarnings("unchecked")
        public T dequeue() {
            T item = (T) data[head];
            head = (head + 1) % capacity;
            count--;
            return item;
        }

        public boolean isEmpty()  { return count == 0; }
        public boolean isFull()   { return count == capacity; }
        public int     size()     { return count; }

        public void display() {
            System.out.print("Queue [" + count + "/" + capacity + "]: ");
            int idx = head;
            for (int i = 0; i < count; i++) {
                System.out.print(data[idx]);
                if (i < count - 1) System.out.print(", ");
                idx = (idx + 1) % capacity;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Generic Queue Template ===\n");

        // Integer queue
        Queue<Integer> intQ = new Queue<>(5);
        intQ.enqueue(1); intQ.enqueue(2); intQ.enqueue(3);
        System.out.print("After enqueue 1,2,3: "); intQ.display();
        System.out.println("Dequeued: " + intQ.dequeue());
        System.out.print("After dequeue: "); intQ.display();

        // String queue
        Queue<String> strQ = new Queue<>(4);
        strQ.enqueue("Alpha"); strQ.enqueue("Beta"); strQ.enqueue("Gamma");
        System.out.print("\nString queue: "); strQ.display();
        System.out.println("Dequeued: " + strQ.dequeue());
        System.out.print("After dequeue: "); strQ.display();

        // Double queue (circular buffer test)
        Queue<Double> dblQ = new Queue<>(3);
        dblQ.enqueue(1.1); dblQ.enqueue(2.2); dblQ.enqueue(3.3);
        System.out.print("\nDouble queue (full): "); dblQ.display();
        dblQ.dequeue(); dblQ.enqueue(4.4); // circular wrap
        System.out.print("After dequeue + enqueue 4.4 (circular): "); dblQ.display();

        // FIFO order verification
        System.out.println("\nFIFO verification (should print 1, 2, 3):");
        Queue<Integer> fifo = new Queue<>(3);
        fifo.enqueue(1); fifo.enqueue(2); fifo.enqueue(3);
        while (!fifo.isEmpty()) System.out.print(fifo.dequeue() + " ");
        System.out.println();
    }
}
