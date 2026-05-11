package scenarioBased.part3;/*
 * Chapter 6, Exercise 9
 * Transform the fraction structure from Exercise 8 in Chapter 4 into a fraction class.
 * Member data is the fraction's numerator and denominator.
 * Member functions should accept input from the user in the form 3/5, and output the
 * fraction's value in the same format. Another member function should add two fraction values.
 * Write a main() program that allows the user to repeatedly input two fractions and
 * then displays their sum. After each operation, ask whether the user wants to continue.
 */

import java.util.Scanner;

public class Ch6_Ex9_FractionClass {

    static class Fraction {
        private long num, den;

        Fraction() { num = 0; den = 1; }
        Fraction(long num, long den) { set(num, den); }

        void set(long n, long d) {
            if (d == 0) throw new ArithmeticException("Denominator cannot be zero");
            if (d < 0) { n = -n; d = -d; }
            long g = gcd(Math.abs(n), d);
            num = n / g; den = d / g;
        }

        private static long gcd(long a, long b) {
            while (b != 0) { long t = b; b = a % b; a = t; }
            return a;
        }

        Fraction add(Fraction other) {
            return new Fraction(num * other.den + other.num * den, den * other.den);
        }

        void getFraction(Scanner scanner) {
            System.out.print("Enter fraction (e.g. 3/5): ");
            String s = scanner.next();
            String[] parts = s.split("/");
            set(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
        }

        void putFraction() {
            System.out.printf("%d/%d%n", num, den);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char again;

        do {
            Fraction f1 = new Fraction(), f2 = new Fraction();
            f1.getFraction(scanner);
            f2.getFraction(scanner);
            System.out.print("Sum = ");
            f1.add(f2).putFraction();
            System.out.print("Do another (y/n)? ");
            again = scanner.next().charAt(0);
        } while (again == 'y' || again == 'Y');

        scanner.close();
    }
}
