package scenarioBased.part3;/*
 * Chapter 8, Exercise 1 (Starred):
 * To the Distance class in the ENGLPLUS program in this chapter, add an overloaded
 * - operator that subtracts two distances. It should allow statements like:
 *   dist3 = dist1 - dist2;
 * Assume that the operator will never be used to subtract a larger number from a
 * smaller one (that is, negative distances are not allowed).
 */

import java.util.Scanner;

public class Ch8Ex1_Distance {

    static class Distance {
        private int feet;
        private double inches;

        public Distance() { feet = 0; inches = 0.0; }
        public Distance(int f, double i) { feet = f; inches = i; normalize(); }

        private void normalize() {
            if (inches >= 12.0) { feet += (int)(inches / 12); inches %= 12.0; }
        }

        // Overloaded + operator
        public Distance add(Distance d2) {
            return new Distance(this.feet + d2.feet, this.inches + d2.inches);
        }

        // Overloaded - operator (no negative distances)
        public Distance subtract(Distance d2) {
            double totalInches1 = this.feet * 12.0 + this.inches;
            double totalInches2 = d2.feet * 12.0 + d2.inches;
            if (totalInches1 < totalInches2) {
                System.out.println("Error: negative distance not allowed.");
                return new Distance(0, 0);
            }
            double diff = totalInches1 - totalInches2;
            return new Distance((int)(diff / 12), diff % 12);
        }

        public void getdist(Scanner sc) {
            System.out.print("Enter feet: "); feet = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Enter inches: "); inches = Double.parseDouble(sc.nextLine().trim());
            normalize();
        }

        public void showdist() {
            System.out.printf("%d'-%5.2f\"%n", feet, inches);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Distance Subtraction ===");

        System.out.println("Enter dist1:");
        Distance d1 = new Distance(); d1.getdist(sc);
        System.out.println("Enter dist2:");
        Distance d2 = new Distance(); d2.getdist(sc);

        Distance sum = d1.add(d2);
        Distance diff = d1.subtract(d2);

        System.out.print("dist1 + dist2 = "); sum.showdist();
        System.out.print("dist1 - dist2 = "); diff.showdist();
        sc.close();
    }
}
