package scenarioBased.part3;/*
 * Chapter 8, Exercise 11:
 * Remember the sterling structure? Turn it into a class, with pounds (type long),
 * shillings (type int), and pence (type int) data items. Create the following member functions:
 * - no-argument constructor
 * - one-argument constructor (type double, for converting from decimal pounds)
 * - three-argument constructor (taking pounds, shillings, and pence)
 * - getSterling() to get an amount in pounds, shillings, and pence from the user, format £9.19.11
 * - putSterling() to display an amount in pounds, shillings, and pence, format £9.19.11
 * - addition (sterling + sterling) using overloaded + operator
 * - subtraction (sterling - sterling) using overloaded - operator
 * - multiplication (sterling * double) using overloaded * operator
 * - division (sterling / sterling) using overloaded / operator
 * - division (sterling / double) using overloaded / operator
 * - operator double (to convert to double)
 */

import java.util.Scanner;

public class Ch8Ex11_Sterling {

    static class Sterling {
        private long pounds;
        private int shillings; // 20 shillings per pound
        private int pence;     // 12 pence per shilling

        public Sterling() { pounds = 0; shillings = 0; pence = 0; }

        // One-argument: from decimal pounds
        public Sterling(double decimalPounds) {
            long totalPence = Math.round(decimalPounds * 240); // 1 pound = 20*12 = 240 pence
            normalize(totalPence);
        }

        public Sterling(long p, int s, int pe) { normalize(p * 240 + s * 12 + pe); }

        private void normalize(long totalPence) {
            if (totalPence < 0) totalPence = 0;
            pence     = (int)(totalPence % 12);
            shillings = (int)((totalPence / 12) % 20);
            pounds    = totalPence / 240;
        }

        private long toTotalPence() { return pounds * 240 + shillings * 12 + pence; }

        // Convert to double
        public double toDouble() { return toTotalPence() / 240.0; }

        // Overloaded operators
        public Sterling add(Sterling s2) { return new Sterling(0, 0, (int)(this.toTotalPence() + s2.toTotalPence())); }
        public Sterling subtract(Sterling s2) { return new Sterling(0, 0, (int)(this.toTotalPence() - s2.toTotalPence())); }
        public Sterling multiply(double factor) { return new Sterling(0, 0, (int)(this.toTotalPence() * factor)); }
        public double divideBy(Sterling s2) { return (double)this.toTotalPence() / s2.toTotalPence(); }
        public Sterling divideBy(double d) { return new Sterling(0, 0, (int)(this.toTotalPence() / d)); }

        public void getSterling(Scanner sc) {
            System.out.print("Enter in format pounds.shillings.pence (e.g. 9.19.11): ");
            String input = sc.nextLine().trim();
            String[] parts = input.split("\\.");
            long p = Long.parseLong(parts[0]);
            int s  = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;
            int pe = parts.length > 2 ? Integer.parseInt(parts[2]) : 0;
            normalize(p * 240 + s * 12 + pe);
        }

        public void putSterling() {
            System.out.printf("\u00a3%d.%d.%d%n", pounds, shillings, pence);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Sterling Arithmetic ===");

        Sterling s1 = new Sterling(); s1.getSterling(sc);
        Sterling s2 = new Sterling(); s2.getSterling(sc);

        System.out.print("s1 = "); s1.putSterling();
        System.out.print("s2 = "); s2.putSterling();
        System.out.print("s1 + s2 = "); s1.add(s2).putSterling();
        System.out.print("s1 - s2 = "); s1.subtract(s2).putSterling();
        System.out.print("s1 * 2.5 = "); s1.multiply(2.5).putSterling();
        System.out.printf("s1 / s2 = %.4f%n", s1.divideBy(s2));
        System.out.print("s1 / 3 = "); s1.divideBy(3.0).putSterling();
        System.out.printf("s1 as double = %.4f pounds%n", s1.toDouble());
        sc.close();
    }
}
