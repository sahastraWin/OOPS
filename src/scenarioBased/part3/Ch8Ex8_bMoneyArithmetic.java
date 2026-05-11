package scenarioBased.part3;/*
 * Chapter 8, Exercise 8:
 * Modify the bMoney class from Exercise 12 in Chapter 7, "Arrays and Strings," to include
 * the following arithmetic operations, performed with overloaded operators:
 *   bMoney = bMoney + bMoney
 *   bMoney = bMoney - bMoney
 *   bMoney = bMoney * long double   (price per widget times number of widgets)
 *   long double = bMoney / bMoney   (total price divided by price per widget)
 *   bMoney = bMoney / long double   (total price divided by number of widgets)
 *
 * The / operator is overloaded twice. Notice that the * operator is overloaded once
 * because bMoney * bMoney doesn't represent anything real.
 * Do NOT include conversion operators for bMoney to long double or long double to bMoney,
 * to make it impossible to compile illegal operations.
 */

import java.util.Scanner;

public class Ch8Ex8_bMoneyArithmetic {

    static class BMoney {
        private double money;

        public BMoney() { money = 0.0; }
        public BMoney(double m) { money = m; }
        public BMoney(String s) { money = mstold(s); }

        public static double mstold(String s) {
            return Double.parseDouble(s.replaceAll("[$,\\s]", ""));
        }

        public static String ldtoms(double amount) {
            return String.format("$%.2f", amount);
        }

        // bMoney + bMoney
        public BMoney add(BMoney m2) { return new BMoney(this.money + m2.money); }

        // bMoney - bMoney
        public BMoney subtract(BMoney m2) { return new BMoney(this.money - m2.money); }

        // bMoney * double (price * quantity)
        public BMoney multiply(double factor) { return new BMoney(this.money * factor); }

        // bMoney / bMoney (returns double: how many units fit)
        public double divideByMoney(BMoney m2) {
            if (m2.money == 0) { System.out.println("Division by zero!"); System.exit(1); }
            return this.money / m2.money;
        }

        // bMoney / double (split amount)
        public BMoney divideByDouble(double divisor) {
            if (divisor == 0) { System.out.println("Division by zero!"); System.exit(1); }
            return new BMoney(this.money / divisor);
        }

        public void getMoney(Scanner sc) {
            System.out.print("Enter money (e.g. $1234.56): ");
            money = mstold(sc.nextLine().trim());
        }

        public void putMoney() { System.out.println(ldtoms(money)); }
        public double getValue() { return money; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== bMoney Arithmetic ===");

        while (true) {
            BMoney price = new BMoney(); price.getMoney(sc);
            BMoney total = new BMoney(); total.getMoney(sc);
            System.out.print("Enter quantity (float): ");
            double qty = Double.parseDouble(sc.nextLine().trim());

            System.out.print("price + total = "); price.add(total).putMoney();
            System.out.print("total - price = "); total.subtract(price).putMoney();
            System.out.print("price * qty = ");   price.multiply(qty).putMoney();
            System.out.printf("total / price = %.4f units%n", total.divideByMoney(price));
            System.out.print("total / qty = ");   total.divideByDouble(qty).putMoney();

            System.out.print("Continue? (y/n): ");
            if (!sc.nextLine().trim().equalsIgnoreCase("y")) break;
        }
        sc.close();
    }
}
