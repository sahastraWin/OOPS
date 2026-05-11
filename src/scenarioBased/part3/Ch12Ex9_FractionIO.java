package scenarioBased.part3;/*
 * Chapter 12, Exercise 9:
 * Start with Exercise 7 in Chapter 8, "Operator Overloading," and overload the insertion
 * (<<) and extraction (>>) operators for the frac class in the four-function calculator.
 * Note that you can chain the operators, so asking for a fraction, an operator, and a
 * fraction should require only one statement:
 *   cin >> frac1 >> op >> frac2;
 * (In Java, we simulate these with custom print() and read() methods.)
 */

import java.util.Scanner;

public class Ch12Ex9_FractionIO {

    static class Fraction {
        private int num;
        private int den;

        public Fraction() { num = 0; den = 1; }
        public Fraction(int n, int d) {
            if (d == 0) throw new ArithmeticException("Denominator cannot be 0");
            num = n; den = d; reduce();
        }

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
        public Fraction divide(Fraction f2)   { return new Fraction(num * f2.den, den * f2.num); }
        public boolean equals(Fraction f2)    { return num == f2.num && den == f2.den; }

        // Overloaded << (print) - simulates operator<<
        public void print() { System.out.print(num + "/" + den); }

        // Overloaded >> (read) - simulates operator>> with chaining
        // Returns this for chaining (like cin >> f1 >> op >> f2)
        public Fraction read(Scanner sc, String prompt) {
            while (true) {
                System.out.print(prompt);
                String input = sc.nextLine().trim();
                String[] parts = input.split("/");
                if (parts.length != 2) { System.out.println("Format: numerator/denominator"); continue; }
                try {
                    int n = Integer.parseInt(parts[0].trim());
                    int d = Integer.parseInt(parts[1].trim());
                    if (d == 0) { System.out.println("Denominator cannot be 0"); continue; }
                    num = n; den = d; reduce();
                    return this;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid fraction input.");
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Fraction Calculator with << >> Operators ===");
        Fraction zero = new Fraction(0, 1);

        while (true) {
            Fraction f1 = new Fraction();
            Fraction f2 = new Fraction();

            // Chained reads: cin >> frac1 >> op >> frac2
            f1.read(sc, "Enter first fraction (n/d): ");
            System.out.print("Enter operator (+, -, *, /): ");
            char op = sc.nextLine().trim().charAt(0);
            f2.read(sc, "Enter second fraction (n/d): ");

            // Check exit condition (0/1 for both)
            if (f1.equals(zero) && f2.equals(zero)) { System.out.println("Exiting."); break; }

            Fraction result;
            switch (op) {
                case '+': result = f1.add(f2);      break;
                case '-': result = f1.subtract(f2); break;
                case '*': result = f1.multiply(f2); break;
                case '/': result = f1.divide(f2);   break;
                default:  System.out.println("Unknown operator."); continue;
            }

            // Chained print: cout << f1 << op << f2 << " = " << result
            System.out.print("Answer is --- ");
            f1.print(); System.out.print(" " + op + " "); f2.print();
            System.out.print(" = "); result.print(); System.out.println();

            System.out.print("Do another (y/n)? ");
            if (!sc.nextLine().trim().equalsIgnoreCase("y")) break;
        }

        sc.close();
    }
}
