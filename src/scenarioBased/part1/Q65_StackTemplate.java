package scenarioBased.part1;/*
/*
 * Question 65 - Classic Scenario: STACK TEMPLATE
 * Write a generic class Stack that can hold any data type.
 * Include push(), pop(), peek(), and isEmpty() functions.
 * Demonstrate use with Integer, Double, and String stacks.
 */

public class Q65_StackTemplate {
    static class Stack<T> {
        private Object[] items;
        private int top = -1;

        Stack(int capacity) { items = new Object[capacity]; }

        void push(T item) {
            if (top >= items.length - 1) { System.out.println("Stack overflow!"); return; }
            items[++top] = item;
            System.out.println("Pushed: " + item);
        }

        @SuppressWarnings("unchecked")
        T pop() {
            if (isEmpty()) throw new RuntimeException("Stack underflow!");
            return (T) items[top--];
        }

        @SuppressWarnings("unchecked")
        T peek() {
            if (isEmpty()) throw new RuntimeException("Stack is empty!");
            return (T) items[top];
        }

        boolean isEmpty() { return top < 0; }
        int size()        { return top + 1; }
    }

    public static void main(String[] args) {
        System.out.println("--- Integer Stack ---");
        Stack<Integer> intStack = new Stack<>(10);
        intStack.push(100); intStack.push(200); intStack.push(300);
        System.out.println("Peek: " + intStack.peek());
        while (!intStack.isEmpty()) System.out.println("Pop: " + intStack.pop());

        System.out.println("\n--- Double Stack ---");
        Stack<Double> dblStack = new Stack<>(10);
        dblStack.push(3.14); dblStack.push(2.71); dblStack.push(1.41);
        while (!dblStack.isEmpty()) System.out.println("Pop: " + dblStack.pop());

        System.out.println("\n--- String Stack ---");
        Stack<String> strStack = new Stack<>(10);
        strStack.push("Alpha"); strStack.push("Beta"); strStack.push("Gamma");
        while (!strStack.isEmpty()) System.out.println("Pop: " + strStack.pop());
    }
}
