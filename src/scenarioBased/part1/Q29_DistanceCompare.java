package scenarioBased.part1;/*
/*
 * Question 29 - Chapter 8: Operator Overloading
 * Design a class called Distance (feet and inches) and implement
 * lessThan(), greaterThan(), equalTo(), and add() methods.
 * (Java equivalent of overloading <, >, ==, and +)
 * Demonstrate in main() by comparing and adding several distances.
 */

public class Q29_DistanceCompare {
    static class Distance implements Comparable<Distance> {
        private int feet;
        private double inches;

        Distance(int feet, double inches) {
            double total = feet * 12 + inches;
            this.feet = (int)(total / 12);
            this.inches = total % 12;
        }

        double toInches() { return feet * 12 + inches; }

        Distance add(Distance other) {
            return new Distance(0, toInches() + other.toInches());
        }

        boolean lessThan(Distance other)    { return toInches() < other.toInches(); }
        boolean greaterThan(Distance other) { return toInches() > other.toInches(); }
        boolean equalTo(Distance other)     { return Double.compare(toInches(), other.toInches()) == 0; }

        @Override public int compareTo(Distance other) { return Double.compare(toInches(), other.toInches()); }

        @Override public String toString() { return feet + "' " + String.format("%.2f\"", inches); }
    }

    public static void main(String[] args) {
        Distance d1 = new Distance(6, 3);
        Distance d2 = new Distance(4, 11.5);
        Distance d3 = new Distance(6, 3);

        System.out.println("d1 = " + d1);
        System.out.println("d2 = " + d2);
        System.out.println("d1 > d2? " + d1.greaterThan(d2));
        System.out.println("d1 < d2? " + d1.lessThan(d2));
        System.out.println("d1 == d3? " + d1.equalTo(d3));
        System.out.println("d1 + d2 = " + d1.add(d2));
    }
}
