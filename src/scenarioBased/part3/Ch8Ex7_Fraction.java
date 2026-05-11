package scenarioBased.part3;/*
 * Chapter 8, Exercise 7:
 * Modify the fraction class in the four-function fraction calculator from Exercise 11 in
 * Chapter 6 so that it uses overloaded operators for addition, subtraction, multiplication,
 * and division. Also overload the == and != comparison operators, and use them to exit
 * from the loop if the user enters 0/1 for the values of the two input fractions.
 * You may want to modify the lowerTerms() function so that it returns the value of
 * its argument reduced to lowest terms.
 */

import java.util.Scanner;

public class Ch8Ex7_Fraction {

    static class Fraction {
        private int num;
        private int den;

        public Fraction() { num = 0; den = 1; }
        public Fraction(int n, int d) {
            if (d == 0) { System.out.println("Denominator cannot be zero!"); System.exit(1); }
            num = n; den = d;
            lowerTerms();
        }

        private static int gcd(int a, int b) {
            a = Math.abs(a); b = Math.abs(b);
            while (b != 0) { int t = b; b = a % b; a = t; }
            return a;
        }

        private void lowerTerms() {
            if (den < 0) { num = -num; den = -den; }
            int g = gcd(Math.abs(num), den);
            num /= g; den /= g;
        }

        public Fraction add(Fraction f2) {
            return new Fraction(num * f2.den + f2.num * den, den * f2.den);
        }

        public Fraction subtract(Fraction f2) {
            return new Fraction(num * f2.den - f2.num * den, den * f2.den);
        }

        public Fraction multiply(Fraction f2) {
            return new Fraction(num * f2.num, den * f2.den);
        }

        public Fraction divide(Fraction f2) {
            return new Fraction(num * f2.den, den * f2.num);
        }

        public boolean equals(Fraction f2) { return num == f2.num && den == f2.den; }
        public boolean notEquals(Fraction f2) { return !equals(f2); }

        public void getFraction(Scanner sc) {
            System.out.print("Enter numerator: ");   num = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Enter denominator: "); den = Integer.parseInt(sc.nextLine().trim());
            lowerTerms();
        }

        public void putFraction() { System.out.println(num + "/" + den); }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Fraction zero = new Fraction(0, 1);
        System.out.println("=== Fraction Calculator (enter 0/1 to quit) ===");

        while (true) {
            Fraction f1 = new Fraction(); f1.getFraction(sc);
            Fraction f2 = new Fraction(); f2.getFraction(sc);

            if (f1.equals(zero) && f2.equals(zero)) break;

            System.out.print("f1 + f2 = "); f1.add(f2).putFraction();
            System.out.print("f1 - f2 = "); f1.subtract(f2).putFraction();
            System.out.print("f1 * f2 = "); f1.multiply(f2).putFraction();
            if (f2.num != 0) { System.out.print("f1 / f2 = "); f1.divide(f2).putFraction(); }
            System.out.println("f1 == f2: " + f1.equals(f2));
        }
        sc.close();
    }
}
