package scenarioBased.part1;/*
/*
 * Question 50 - Chapter 14: Templates and Exceptions
 * Write a template class called Stack that works with any data type.
 * It should support push(), pop(), and isEmpty().
 * Demonstrate with stacks of Integer, Float, and String.
 * (Java uses generics as the equivalent of C++ templates.)
 */

public class Q50_GenericStack {
    static class Stack<T> {
        private Object[] data;
        private int top = -1;

        Stack(int capacity) { data = new Object[capacity]; }

        void push(T val) {
            if (top >= data.length - 1) throw new RuntimeException("Stack overflow!");
            data[++top] = val;
        }

        @SuppressWarnings("unchecked")
        T pop() {
            if (isEmpty()) throw new RuntimeException("Stack is empty!");
            return (T) data[top--];
        }

        @SuppressWarnings("unchecked")
        T peek() {
            if (isEmpty()) throw new RuntimeException("Stack is empty!");
            return (T) data[top];
        }

        boolean isEmpty() { return top < 0; }
        int size()        { return top + 1; }
    }

    public static void main(String[] args) {
        // Integer stack
        Stack<Integer> intStack = new Stack<>(5);
        intStack.push(10); intStack.push(20); intStack.push(30);
        System.out.print("Integer Stack: ");
        while (!intStack.isEmpty()) System.out.print(intStack.pop() + " ");

        // Float stack
        Stack<Float> floatStack = new Stack<>(5);
        floatStack.push(1.1f); floatStack.push(2.2f); floatStack.push(3.3f);
        System.out.print("\nFloat Stack: ");
        while (!floatStack.isEmpty()) System.out.print(floatStack.pop() + " ");

        // String stack
        Stack<String> strStack = new Stack<>(5);
        strStack.push("Hello"); strStack.push("World"); strStack.push("Java");
        System.out.print("\nString Stack: ");
        while (!strStack.isEmpty()) System.out.print(strStack.pop() + " ");
        System.out.println();
    }
}
