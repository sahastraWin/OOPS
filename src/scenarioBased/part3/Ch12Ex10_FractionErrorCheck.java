package scenarioBased.part3;/*
 * Chapter 12, Exercise 10:
 * Add error checking to the extraction (>>) operator of the frac class in Exercise 9.
 * With error checking it's probably better to prompt for the first fraction, then for the
 * operator, and then for the second fraction, rather than using a single statement.
 * This makes the format more comprehensible when it is interspersed with error messages.
 *
 * Enter first fraction: 5/0
 *   Denominator cannot be 0
 *   Enter fraction again: 5/1
 * Enter operator (+, -, *, /): +
 * Enter second fraction: one third
 *   Input error
 *   Enter fraction again: 1/3
 * Answer is ----------------------- 16/3
 * Do another (y/n)?
 *
 * Check for ios error flags and also for a denominator of 0. If there's an error, prompt
 * the user to enter the fraction again.
 */

import java.util.Scanner;

public class Ch12Ex10_FractionErrorCheck {

    static Scanner sc = new Scanner(System.in);

    static class Fraction {
        private int num;
        private int den;

        public Fraction() { num = 0; den = 1; }
        public Fraction(int n, int d) { num = n; den = d; reduce(); }

        private static int gcd(int a, int b) {
            a = Math.abs(a); b = Math.abs(b);
            while (b != 0) { int t = b; b = a % b; a = t; }
            return a == 0 ? 1 : a;
        }

        private void reduce() {
            if (den < 0) { num = -num; den = -den; }
            int g = gcd(Math.abs(num), den);
            num /= g; den /= g;
        }

        public Fraction add(Fraction f2)      { return new Fraction(num * f2.den + f2.num * den, den * f2.den); }
        public Fraction subtract(Fraction f2) { return new Fraction(num * f2.den - f2.num * den, den * f2.den); }
        public Fraction multiply(Fraction f2) { return new Fraction(num * f2.num, den * f2.den); }
        public Fraction divide(Fraction f2)   {
            if (f2.num == 0) throw new ArithmeticException("Cannot divide by zero fraction");
            return new Fraction(num * f2.den, den * f2.num);
        }

        // Error-checked read with retry
        public static Fraction readChecked(String prompt) {
            while (true) {
                System.out.print(prompt);
                String input = sc.nextLine().trim();
                // Check for non-numeric / wrong format
                if (!input.contains("/")) {
                    System.out.println("  Input error — use format: numerator/denominator");
                    System.out.print("  Enter fraction again: ");
                    continue;
                }
                String[] parts = input.split("/");
                try {
                    int n = Integer.parseInt(parts[0].trim());
                    int d = Integer.parseInt(parts[1].trim());
                    if (d == 0) {
                        System.out.println("  Denominator cannot be 0");
                        System.out.print("  Enter fraction again: ");
                        // Consume the "Enter fraction again:" prompt by looping
                        // Re-prompt using the retry sub-prompt
                        while (true) {
                            input = sc.nextLine().trim();
                            if (!input.contains("/")) { System.out.println("  Input error"); System.out.print("  Enter fraction again: "); continue; }
                            parts = input.split("/");
                            try {
                                n = Integer.parseInt(parts[0].trim());
                                d = Integer.parseInt(parts[1].trim());
                                if (d == 0) { System.out.println("  Denominator cannot be 0"); System.out.print("  Enter fraction again: "); continue; }
                                return new Fraction(n, d);
                            } catch (NumberFormatException ex) {
                                System.out.println("  Input error"); System.out.print("  Enter fraction again: ");
                            }
                        }
                    }
                    return new Fraction(n, d);
                } catch (NumberFormatException e) {
                    System.out.println("  Input error");
                    System.out.print("  Enter fraction again: ");
                }
            }
        }

        public void print() { System.out.print(num + "/" + den); }
        public String toString() { return num + "/" + den; }
    }

    public static void main(String[] args) {
        System.out.println("=== Fraction Calculator with Error Checking ===");

        while (true) {
            Fraction f1 = Fraction.readChecked("Enter first fraction: ");

            System.out.print("Enter operator (+, -, *, /): ");
            char op = sc.nextLine().trim().charAt(0);

            Fraction f2 = Fraction.readChecked("Enter second fraction: ");

            try {
                Fraction result;
                switch (op) {
                    case '+': result = f1.add(f2);      break;
                    case '-': result = f1.subtract(f2); break;
                    case '*': result = f1.multiply(f2); break;
                    case '/': result = f1.divide(f2);   break;
                    default:  System.out.println("Unknown operator."); continue;
                }
                System.out.print("Answer is ----------------------- ");
                result.print(); System.out.println();
            } catch (ArithmeticException e) {
                System.out.println("Math error: " + e.getMessage());
            }

            System.out.print("Do another (y/n)? ");
            if (!sc.nextLine().trim().equalsIgnoreCase("y")) break;
        }

        sc.close();
    }
}
