package scenarioBased.part3;/*
 * Chapter 12, Exercise 11:
 * Start with the bMoney class, last seen in Exercise 5 in Chapter 11. Overload the insertion
 * (<<) and extraction (>>) operators to perform I/O on bMoney quantities.
 * Perform some sample I/O in main().
 * (In Java, we implement custom print() and read() methods to simulate << and >>.)
 */

import java.util.Scanner;

public class Ch12Ex11_bMoneyIO {

    static class BMoney {
        private double dollars;

        public BMoney() { dollars = 0; }
        public BMoney(double d) { dollars = d; }
        public BMoney(String s) { dollars = parseMoney(s); }

        private static double parseMoney(String s) {
            return Double.parseDouble(s.replaceAll("[$,\\s]", ""));
        }

        // Overloaded << (insertion / output)
        public void print() {
            System.out.printf("$%,.2f", dollars);
        }

        public void println() {
            System.out.printf("$%,.2f%n", dollars);
        }

        // Overloaded >> (extraction / input) with chaining support
        public BMoney read(Scanner sc, String prompt) {
            while (true) {
                System.out.print(prompt);
                String input = sc.nextLine().trim();
                try {
                    dollars = parseMoney(input);
                    return this;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid money format. Use: $1,234.56 or 1234.56");
                }
            }
        }

        // Arithmetic operations
        public BMoney add(BMoney m2)       { return new BMoney(dollars + m2.dollars); }
        public BMoney subtract(BMoney m2)  { return new BMoney(dollars - m2.dollars); }
        public BMoney multiply(double f)   { return new BMoney(dollars * f); }
        public double divideBy(BMoney m2)  { return dollars / m2.dollars; }
        public BMoney divideBy(double d)   { return new BMoney(dollars / d); }

        // Friend-style: factor * bMoney
        public static BMoney multiplyLeft(double f, BMoney m) { return new BMoney(f * m.dollars); }

        public double getDollars() { return dollars; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== bMoney with << >> I/O Operators ===");

        BMoney m1 = new BMoney();
        BMoney m2 = new BMoney();

        // Simulate: cin >> m1 >> m2;
        m1.read(sc, "Enter first money amount:  ");
        m2.read(sc, "Enter second money amount: ");

        // Simulate: cout << "m1 = " << m1 << endl;
        System.out.print("m1 = "); m1.println();
        System.out.print("m2 = "); m2.println();

        System.out.print("m1 + m2 = "); m1.add(m2).println();
        System.out.print("m1 - m2 = "); m1.subtract(m2).println();
        System.out.print("m1 * 3  = "); m1.multiply(3).println();
        System.out.printf("m1 / m2 = %.4f units%n", m1.divideBy(m2));
        System.out.print("m1 / 4  = "); m1.divideBy(4).println();
        System.out.print("2.5 * m1 = "); BMoney.multiplyLeft(2.5, m1).println();

        sc.close();
    }
}
