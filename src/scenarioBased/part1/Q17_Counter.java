package scenarioBased.part1;/*
/*
 * Question 17 - Chapter 6: Objects and Classes
 * Create a class called Counter that holds a single integer count,
 * starts at 0, can be incremented with a member function, and reports its value.
 * Use it in main() to count user keystrokes.
 */

import java.util.Scanner;

public class Q17_Counter {
    static class Counter {
        private int count = 0;

        void increment() { count++; }
        int getCount() { return count; }
        void reset() { count = 0; }
    }

    public static void main(String[] args) throws Exception {
        Counter counter = new Counter();
        Scanner sc = new Scanner(System.in);
        System.out.println("Press ENTER to count keystrokes. Type 'q' and ENTER to quit.");
        while (true) {
            String line = sc.nextLine();
            if (line.equalsIgnoreCase("q")) break;
            counter.increment();
            System.out.println("Count: " + counter.getCount());
        }
        System.out.println("Final count: " + counter.getCount());
        sc.close();
    }
}
