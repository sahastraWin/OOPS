package scenarioBased.part1;/*
/*
 * Question 27 - Chapter 8: Operator Overloading
 * Create a class called Money that stores a monetary amount in dollars and cents.
 * Implement add(), subtract(), and equals() methods (Java does not support
 * operator overloading, so we use method overloading as the equivalent).
 */

public class Q27_Money {
    static class Money {
        private long totalCents; // store as cents to avoid floating-point issues

        Money(int dollars, int cents) {
            if (cents < 0 || cents >= 100) throw new IllegalArgumentException("Cents must be 0-99");
            totalCents = (long) dollars * 100 + cents;
        }

        Money add(Money other) {
            long result = this.totalCents + other.totalCents;
            return new Money((int)(result / 100), (int)(result % 100));
        }

        Money subtract(Money other) {
            long result = this.totalCents - other.totalCents;
            if (result < 0) throw new ArithmeticException("Result is negative");
            return new Money((int)(result / 100), (int)(result % 100));
        }

        boolean equalTo(Money other) { return this.totalCents == other.totalCents; }

        @Override
        public String toString() {
            return String.format("$%d.%02d", totalCents / 100, totalCents % 100);
        }
    }

    public static void main(String[] args) {
        Money m1 = new Money(10, 75);
        Money m2 = new Money(5, 50);
        System.out.println("M1: " + m1);
        System.out.println("M2: " + m2);
        System.out.println("M1 + M2 = " + m1.add(m2));
        System.out.println("M1 - M2 = " + m1.subtract(m2));
        System.out.println("M1 == M2? " + m1.equalTo(m2));
        System.out.println("M1 == M1? " + m1.equalTo(m1));
    }
}
