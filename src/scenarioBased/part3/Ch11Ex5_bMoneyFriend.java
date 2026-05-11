package scenarioBased.part3;/*
 * Chapter 11, Exercise 5:
 * Start with the program of Exercise 8 in Chapter 8, which overloaded five arithmetic
 * operators for money strings. Add the two operators that couldn't be overloaded in that
 * exercise. These operations:
 *   long double * bMoney   // number times money
 *   long double / bMoney   // number divided by money
 * require friend functions, since an object appears on the right side of the operator
 * while a numerical constant appears on the left. Make sure that the main() program
 * allows the user to enter two money strings and a floating-point value, and then carries
 * out all seven arithmetic operations on appropriate pairs of these values.
 * (In Java, these are implemented as static utility methods, equivalent to friend functions.)
 */

import java.util.Scanner;

public class Ch11Ex5_bMoneyFriend {

    static class BMoney {
        private double dollars;

        public BMoney() { dollars = 0; }
        public BMoney(double d) { dollars = d; }
        public BMoney(String s) { dollars = Double.parseDouble(s.replaceAll("[$,\\s]", "")); }

        // bMoney + bMoney
        public BMoney add(BMoney m2) { return new BMoney(this.dollars + m2.dollars); }
        // bMoney - bMoney
        public BMoney subtract(BMoney m2) { return new BMoney(this.dollars - m2.dollars); }
        // bMoney * double
        public BMoney multiply(double factor) { return new BMoney(this.dollars * factor); }
        // bMoney / bMoney -> double
        public double divideByMoney(BMoney m2) { return this.dollars / m2.dollars; }
        // bMoney / double
        public BMoney divideByDouble(double d) { return new BMoney(this.dollars / d); }

        // "Friend" functions: double * bMoney and double / bMoney (static methods)
        public static BMoney multiplyLeft(double factor, BMoney m) {
            return new BMoney(factor * m.dollars);
        }

        public static BMoney divideLeft(double numerator, BMoney m) {
            if (m.dollars == 0) { System.out.println("Division by zero!"); System.exit(1); }
            return new BMoney(numerator / m.dollars);
        }

        public void getMoney(Scanner sc) {
            System.out.print("Enter money (e.g. 1234.56): ");
            dollars = Double.parseDouble(sc.nextLine().trim().replaceAll("[$,\\s]", ""));
        }

        public void putMoney() { System.out.printf("$%.2f%n", dollars); }
        public double getDollars() { return dollars; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== bMoney: All Seven Arithmetic Operations ===");

        BMoney m1 = new BMoney(); m1.getMoney(sc);
        BMoney m2 = new BMoney(); m2.getMoney(sc);
        System.out.print("Enter a floating-point factor: ");
        double factor = Double.parseDouble(sc.nextLine().trim());

        System.out.print("m1 + m2 = ");        m1.add(m2).putMoney();
        System.out.print("m1 - m2 = ");        m1.subtract(m2).putMoney();
        System.out.print("m1 * factor = ");     m1.multiply(factor).putMoney();
        System.out.printf("m1 / m2 = %.4f units%n", m1.divideByMoney(m2));
        System.out.print("m1 / factor = ");     m1.divideByDouble(factor).putMoney();
        System.out.print("factor * m1 = ");     BMoney.multiplyLeft(factor, m1).putMoney();
        System.out.print("factor / m1 = ");     BMoney.divideLeft(factor, m1).putMoney();

        sc.close();
    }
}
