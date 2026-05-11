package scenarioBased.part1;/*
/*
 * Question 16 - Chapter 5: Functions
 * Model a simple stack (last-in, first-out) using a global array
 * and functions push() and pop().
 * Demonstrate it in main() by pushing several values and popping them.
 */

public class Q16_SimpleStack {
    static final int MAX = 100;
    static int[] stack = new int[MAX];
    static int top = -1;

    static void push(int val) {
        if (top >= MAX - 1) { System.out.println("Stack overflow!"); return; }
        stack[++top] = val;
        System.out.println("Pushed: " + val);
    }

    static int pop() {
        if (top < 0) throw new RuntimeException("Stack underflow!");
        return stack[top--];
    }

    public static void main(String[] args) {
        push(10); push(20); push(30); push(40); push(50);
        System.out.println("\nPopping values:");
        while (top >= 0)
            System.out.println("Popped: " + pop());
    }
}
