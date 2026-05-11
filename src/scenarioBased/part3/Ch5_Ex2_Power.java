package scenarioBased.part3;/*
 * Chapter 5, Exercise 2
 * Raising a number n to a power p is the same as multiplying n by itself p times.
 * Write a function called power() that takes a double value for n and an int value for p,
 * and returns the result as a double value. Use a default argument of 2 for p, so that
 * if this argument is omitted, the number n will be squared.
 *
 * Chapter 5, Exercise 7 (Extension):
 * Create a series of overloaded functions with the same name power() that, in addition to
 * double, also work with types char, int, long, and float.
 * Write a main() program that exercises these overloaded functions with all argument types.
 */

import java.util.Scanner;

public class Ch5_Ex2_Power {

    // Double version (base)
    static double power(double n, int p) {
        if (p == 0) return 1.0;
        double result = 1.0;
        for (int i = 0; i < Math.abs(p); i++) result *= n;
        return p < 0 ? 1.0 / result : result;
    }

    // Default p=2
    static double power(double n) { return power(n, 2); }

    // Overloads for other types
    static double power(int n, int p) { return power((double) n, p); }
    static double power(long n, int p) { return power((double) n, p); }
    static double power(float n, int p) { return power((double) n, p); }
    static double power(char n, int p) { return power((double) n, p); }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter base (double) and exponent (int): ");
        double base = scanner.nextDouble();
        int exp = scanner.nextInt();

        System.out.printf("power(%.2f, %d) = %.4f%n", base, exp, power(base, exp));
        System.out.printf("power(%.2f) [squared] = %.4f%n", base, power(base));

        // Demonstrate overloads
        System.out.printf("power(int 3, 4) = %.0f%n", power(3, 4));
        System.out.printf("power(long 5L, 3) = %.0f%n", power(5L, 3));
        System.out.printf("power(float 2.5f, 3) = %.4f%n", power(2.5f, 3));
        System.out.printf("power(char 'A'=65, 2) = %.0f%n", power('A', 2));
        scanner.close();
    }
}
