package scenarioBased.part1;/*
/*
 * Question 13 - Chapter 5: Functions
 * Write a function that calculates the factorial of a number.
 * Then write a main() that asks the user for numbers and displays
 * their factorials, repeating until the user enters 0.
 */

import java.util.Scanner;

public class Q13_Factorial {
    static long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative input");
        long result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Factorial Calculator (enter 0 to quit)");
        while (true) {
            System.out.print("Enter a number: ");
            int n = sc.nextInt();
            if (n == 0) break;
            if (n < 0) { System.out.println("Please enter a non-negative number."); continue; }
            System.out.println(n + "! = " + factorial(n));
        }
        sc.close();
    }
}
