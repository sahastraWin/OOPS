package scenarioBased.part3;/*
 * Chapter 14 (Templates & Exceptions), Exercise 3 (Starred):
 * Add exceptions to the queue template in Exercise 2. Throw two exceptions:
 *   1. If the capacity of the queue is exceeded (overflow)
 *   2. If the program tries to remove an item from an empty queue (underflow)
 *
 * Add a count data member; increment when inserting, decrement when removing.
 * Throw an exception if count exceeds capacity, or if it becomes less than 0.
 *
 * Make the main() part interactive so the user can put values on a queue and take them off.
 * After an exception, the program should allow the user to recover without corrupting contents.
 */

import java.util.Scanner;

public class Ch14Ex3_QueueExceptions {

    // Custom exception classes
    static class QueueOverflowException extends RuntimeException {
        public QueueOverflowException() { super("Queue Overflow: queue is full!"); }
    }

    static class QueueUnderflowException extends RuntimeException {
        public QueueUnderflowException() { super("Queue Underflow: queue is empty!"); }
    }

    static class Queue<T> {
        private Object[] data;
        private int head, tail, count, capacity;

        public Queue(int capacity) {
            this.capacity = capacity;
            data = new Object[capacity];
            head = tail = count = 0;
        }

        public void enqueue(T item) {
            if (count >= capacity) throw new QueueOverflowException();
            data[tail] = item;
            tail = (tail + 1) % capacity;
            count++;
        }

        @SuppressWarnings("unchecked")
        public T dequeue() {
            if (count <= 0) throw new QueueUnderflowException();
            T item = (T) data[head];
            head = (head + 1) % capacity;
            count--;
            return item;
        }

        public boolean isEmpty() { return count == 0; }
        public boolean isFull()  { return count == capacity; }

        public void display() {
            System.out.printf("Queue [%d/%d]: ", count, capacity);
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
        Scanner sc = new Scanner(System.in);
        Queue<Integer> q = new Queue<>(5);
        System.out.println("=== Queue with Exceptions (capacity=5) ===");

        while (true) {
            System.out.println("\ne=Enqueue  d=Dequeue  s=Show  q=Quit");
            System.out.print("Choice: ");
            char choice = sc.nextLine().trim().toLowerCase().charAt(0);

            if (choice == 'q') break;

            try {
                switch (choice) {
                    case 'e':
                        System.out.print("Enter integer: ");
                        int val = Integer.parseInt(sc.nextLine().trim());
                        q.enqueue(val);
                        System.out.println("Enqueued: " + val);
                        break;
                    case 'd':
                        int removed = q.dequeue();
                        System.out.println("Dequeued: " + removed);
                        break;
                    case 's':
                        q.display();
                        break;
                    default:
                        System.out.println("Unknown command.");
                }
            } catch (QueueOverflowException e) {
                System.out.println("ERROR: " + e.getMessage());
                System.out.println("(Queue state preserved — you can continue.)");
            } catch (QueueUnderflowException e) {
                System.out.println("ERROR: " + e.getMessage());
                System.out.println("(Queue state preserved — you can continue.)");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input — please enter an integer.");
            }
        }

        sc.close();
    }
}
