package scenarioBased.part3;/*
 * Chapter 7, Exercise 12:
 * Create a class called bMoney. It should store money amounts as long doubles (use double in Java).
 * Use the function mstold() to convert a money string entered as input into a double,
 * and the function ldtoms() to convert the double to a money string for display.
 * You can call the input and output member functions getMoney() and putMoney().
 * Write another member function that adds two bMoney amounts; you can call it madd().
 * Adding bMoney objects is easy: Just add the double member data amounts in two bMoney objects.
 * Write a main() program that repeatedly asks the user to enter two money strings,
 * and then displays the sum as a money string.
 */

import java.util.Scanner;

public class Ch7Ex12_bMoney {

    static class BMoney {
        private double money;

        public BMoney() {
            this.money = 0.0;
        }

        public BMoney(String s) {
            this.money = mstold(s);
        }

        // Convert money string like "$1,234.56" or "1234.56" to double
        public static double mstold(String s) {
            // Remove $ sign, commas, and whitespace
            String cleaned = s.replaceAll("[$,\\s]", "");
            try {
                return Double.parseDouble(cleaned);
            } catch (NumberFormatException e) {
                System.out.println("Invalid money format: " + s);
                return 0.0;
            }
        }

        // Convert double to money string like "$1,234.56"
        public static String ldtoms(double amount) {
            long dollars = (long) Math.abs(amount);
            long cents = Math.round((Math.abs(amount) - dollars) * 100);
            String sign = amount < 0 ? "-" : "";

            // Format dollars with commas
            StringBuilder sb = new StringBuilder();
            String dollarStr = Long.toString(dollars);
            int len = dollarStr.length();
            for (int i = 0; i < len; i++) {
                if (i > 0 && (len - i) % 3 == 0) sb.append(',');
                sb.append(dollarStr.charAt(i));
            }
            return String.format("%s$%s.%02d", sign, sb.toString(), cents);
        }

        public void getMoney(Scanner sc) {
            System.out.print("Enter money amount (e.g. $1,234.56): ");
            String s = sc.nextLine().trim();
            this.money = mstold(s);
        }

        public void putMoney() {
            System.out.println(ldtoms(this.money));
        }

        public void madd(BMoney m1, BMoney m2) {
            this.money = m1.money + m2.money;
        }

        public double getValue() { return money; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== bMoney Addition Program ===");

        while (true) {
            BMoney m1 = new BMoney();
            BMoney m2 = new BMoney();
            BMoney sum = new BMoney();

            m1.getMoney(sc);
            m2.getMoney(sc);
            sum.madd(m1, m2);

            System.out.print("Sum: ");
            sum.putMoney();

            System.out.print("Continue? (y/n): ");
            if (!sc.nextLine().trim().equalsIgnoreCase("y")) break;
        }
        sc.close();
    }
}
