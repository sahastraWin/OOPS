package scenarioBased.part3;/*
 * Chapter 8, Exercise 12:
 * Write a program that incorporates both the bMoney class from Exercise 8 and the sterling
 * class from Exercise 11. Write conversion operators to convert between bMoney and sterling,
 * assuming that one pound (£1.0.0) equals fifty dollars ($50.00). This was the approximate
 * exchange rate in the 19th century when the British Empire was at its height and the
 * pounds-shillings-pence format was in use. Write a main() program that allows the user to
 * enter an amount in either currency and then converts it to the other currency and displays
 * the result. Minimize any modifications to the existing bMoney and sterling classes.
 */

import java.util.Scanner;

public class Ch8Ex12_MoneyConversion {

    static final double DOLLARS_PER_POUND = 50.0;

    static class BMoney {
        private double dollars;
        public BMoney() { dollars = 0; }
        public BMoney(double d) { dollars = d; }
        public BMoney(String s) { dollars = Double.parseDouble(s.replaceAll("[$,\\s]", "")); }
        public double getDollars() { return dollars; }
        public void putMoney() { System.out.printf("$%.2f%n", dollars); }
        public void getMoney(Scanner sc) {
            System.out.print("Enter dollars (e.g. $50.00): ");
            dollars = Double.parseDouble(sc.nextLine().trim().replaceAll("[$,\\s]", ""));
        }
    }

    static class Sterling {
        private long pounds; private int shillings; private int pence;
        public Sterling() {}
        public Sterling(double decPounds) { normalize(Math.round(decPounds * 240)); }
        public Sterling(long p, int s, int pe) { normalize(p * 240 + s * 12 + pe); }
        private void normalize(long totalPence) {
            if (totalPence < 0) totalPence = 0;
            pence = (int)(totalPence % 12); shillings = (int)((totalPence / 12) % 20); pounds = totalPence / 240;
        }
        public double toDouble() { return (pounds * 240 + shillings * 12 + pence) / 240.0; }
        public void putSterling() { System.out.printf("\u00a3%d.%d.%d%n", pounds, shillings, pence); }
        public void getSterling(Scanner sc) {
            System.out.print("Enter in format pounds.shillings.pence (e.g. 1.0.0): ");
            String[] p = sc.nextLine().trim().split("\\.");
            normalize(Long.parseLong(p[0]) * 240 + (p.length > 1 ? Integer.parseInt(p[1]) * 12 : 0) + (p.length > 2 ? Integer.parseInt(p[2]) : 0));
        }
    }

    // Conversion methods
    static BMoney sterlingToBMoney(Sterling s) {
        return new BMoney(s.toDouble() * DOLLARS_PER_POUND);
    }

    static Sterling bMoneyToSterling(BMoney m) {
        return new Sterling(m.getDollars() / DOLLARS_PER_POUND);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Currency Converter: bMoney <-> Sterling ===");
        System.out.println("Rate: £1.0.0 = $" + DOLLARS_PER_POUND);

        System.out.println("\n1. Enter amount in dollars:");
        BMoney bm = new BMoney(); bm.getMoney(sc);
        System.out.print("In dollars: "); bm.putMoney();
        System.out.print("Converted to sterling: "); bMoneyToSterling(bm).putSterling();

        System.out.println("\n2. Enter amount in sterling:");
        Sterling st = new Sterling(); st.getSterling(sc);
        System.out.print("In sterling: "); st.putSterling();
        System.out.print("Converted to dollars: "); sterlingToBMoney(st).putMoney();

        sc.close();
    }
}
