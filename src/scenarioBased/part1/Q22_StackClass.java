package scenarioBased.part1;/*
/*
 * Question 22 - Chapter 7: Arrays and Strings
 * Create a class called Stack that acts as a stack for int values.
 * It should use an array for internal storage and have member functions
 * push(), pop(), and display().
 * Write a main() to demonstrate it.
 */

public class Q22_StackClass {
    static class Stack {
        private int[] data;
        private int top = -1;

        Stack(int capacity) { data = new int[capacity]; }

        void push(int val) {
            if (top >= data.length - 1) { System.out.println("Stack is full!"); return; }
            data[++top] = val;
        }

        int pop() {
            if (top < 0) throw new RuntimeException("Stack is empty!");
            return data[top--];
        }

        boolean isEmpty() { return top < 0; }

        void display() {
            System.out.print("Stack (top→bottom): ");
            for (int i = top; i >= 0; i--)
                System.out.print(data[i] + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Stack s = new Stack(10);
        for (int i = 1; i <= 5; i++) s.push(i * 10);
        s.display();
        System.out.println("Popped: " + s.pop());
        System.out.println("Popped: " + s.pop());
        s.display();
    }
}
