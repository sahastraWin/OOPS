package scenarioBased.part1;/*
/*
 * Question 53 - Chapter 14: Templates and Exceptions
 * Design a generic class Queue (first-in, first-out) and demonstrate
 * its use with multiple data types.
 * Include exception handling for empty-queue dequeue operations.
 */

public class Q53_GenericQueue {
    static class EmptyQueueException extends RuntimeException {
        EmptyQueueException() { super("Queue is empty!"); }
    }

    static class Queue<T> {
        private Object[] data;
        private int front, back, size;

        Queue(int capacity) {
            data = new Object[capacity];
            front = 0; back = -1; size = 0;
        }

        void enqueue(T val) {
            if (size >= data.length) throw new RuntimeException("Queue is full!");
            back = (back + 1) % data.length;
            data[back] = val;
            size++;
        }

        @SuppressWarnings("unchecked")
        T dequeue() {
            if (isEmpty()) throw new EmptyQueueException();
            T val = (T) data[front];
            front = (front + 1) % data.length;
            size--;
            return val;
        }

        boolean isEmpty() { return size == 0; }
        int size()        { return size; }
    }

    public static void main(String[] args) {
        // Integer queue
        Queue<Integer> intQ = new Queue<>(5);
        for (int i = 1; i <= 4; i++) intQ.enqueue(i * 10);
        System.out.print("Integer Queue dequeue: ");
        while (!intQ.isEmpty()) System.out.print(intQ.dequeue() + " ");

        // String queue
        Queue<String> strQ = new Queue<>(5);
        strQ.enqueue("First"); strQ.enqueue("Second"); strQ.enqueue("Third");
        System.out.print("\nString Queue dequeue: ");
        while (!strQ.isEmpty()) System.out.print(strQ.dequeue() + " ");

        // Test exception
        System.out.println("\nTesting empty dequeue:");
        try { strQ.dequeue(); }
        catch (EmptyQueueException e) { System.out.println("Caught: " + e.getMessage()); }
    }
}
