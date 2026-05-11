package scenarioBased.part3;/*
 * Chapter 9, Exercise 12:
 * Amazing as it may seem, the old British pounds-shillings-pence money notation
 * (£9.19.11) isn't the whole story. A penny was further divided into halfpennies and
 * farthings, with a farthing being worth 1/4 of a penny:
 *   1/8 penny = a halffarthing
 *   1/4 penny = a farthing
 *   1/2 penny = a halfpenny (ha'penny)
 *   3/4 penny = a halfpenny plus a farthing
 * The I/O format can be something like £1.1.1-1/4 or £9.19.11-7/8.
 *
 * Derive a new class called sterfrac from sterling. It should be able to perform the four
 * arithmetic operations on sterling quantities that include eighths of a penny. Its only
 * member data is an int indicating the number of eighths; you can call it eighths.
 */

import java.util.Scanner;

public class Ch9Ex12_Sterfrac {

    static class Sterling {
        protected long pounds;
        protected int shillings;
        protected int pence;

        public Sterling() {}
        public Sterling(long p, int s, int pe) { normalizeFromPence(p * 240 + s * 12 + pe); }

        protected void normalizeFromPence(long totalPence) {
            if (totalPence < 0) totalPence = 0;
            pence     = (int)(totalPence % 12);
            shillings = (int)((totalPence / 12) % 20);
            pounds    = totalPence / 240;
        }

        public long toTotalPence() { return pounds * 240 + shillings * 12 + pence; }

        public void putSterling() { System.out.printf("\u00a3%d.%d.%d", pounds, shillings, pence); }
    }

    static class Sterfrac extends Sterling {
        private int eighths; // number of eighths of a penny (0-7)

        public Sterfrac() { super(); eighths = 0; }
        public Sterfrac(long p, int s, int pe, int e) {
            super(p, s, pe);
            this.eighths = e;
            normalizeEighths();
        }

        private void normalizeEighths() {
            if (eighths >= 8) {
                long extraPence = eighths / 8;
                eighths %= 8;
                normalizeFromPence(toTotalPence() + extraPence);
            }
        }

        private long toTotalEighths() { return toTotalPence() * 8 + eighths; }

        private static Sterfrac fromEighths(long totalEighths) {
            if (totalEighths < 0) totalEighths = 0;
            int e  = (int)(totalEighths % 8);
            long p = totalEighths / 8;
            int pe = (int)(p % 12);
            int s  = (int)((p / 12) % 20);
            long lb= p / 240;
            return new Sterfrac(lb, s, pe, e);
        }

        public Sterfrac add(Sterfrac sf2) {
            return fromEighths(this.toTotalEighths() + sf2.toTotalEighths());
        }

        public Sterfrac subtract(Sterfrac sf2) {
            return fromEighths(this.toTotalEighths() - sf2.toTotalEighths());
        }

        public Sterfrac multiply(double factor) {
            return fromEighths(Math.round(this.toTotalEighths() * factor));
        }

        public double divide(Sterfrac sf2) {
            return (double) this.toTotalEighths() / sf2.toTotalEighths();
        }

        private String eighthsToFraction() {
            if (eighths == 0) return "";
            int g = gcd(eighths, 8);
            return "-" + (eighths/g) + "/" + (8/g);
        }

        private int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }

        public void getSterFrac(Scanner sc) {
            System.out.print("Enter in format pounds.shillings.pence-n/8 (e.g. 9.19.11-7/8): ");
            String input = sc.nextLine().trim();
            String[] parts = input.split("-");
            String[] moneyParts = parts[0].split("\\.");
            pounds    = Long.parseLong(moneyParts[0]);
            shillings = moneyParts.length > 1 ? Integer.parseInt(moneyParts[1]) : 0;
            pence     = moneyParts.length > 2 ? Integer.parseInt(moneyParts[2]) : 0;
            if (parts.length > 1) {
                String[] fraction = parts[1].split("/");
                eighths = Integer.parseInt(fraction[0]) * (8 / Integer.parseInt(fraction[1]));
            } else {
                eighths = 0;
            }
        }

        @Override
        public void putSterling() {
            super.putSterling();
            System.out.println(eighthsToFraction());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Sterfrac: Sterling with Fractional Pennies ===");

        Sterfrac sf1 = new Sterfrac(); sf1.getSterFrac(sc);
        Sterfrac sf2 = new Sterfrac(); sf2.getSterFrac(sc);

        System.out.print("sf1 = "); sf1.putSterling();
        System.out.print("sf2 = "); sf2.putSterling();
        System.out.print("sf1 + sf2 = "); sf1.add(sf2).putSterling();
        System.out.print("sf1 - sf2 = "); sf1.subtract(sf2).putSterling();
        System.out.print("sf1 * 2.0 = "); sf1.multiply(2.0).putSterling();
        System.out.printf("sf1 / sf2 = %.4f%n", sf1.divide(sf2));

        sc.close();
    }
}
