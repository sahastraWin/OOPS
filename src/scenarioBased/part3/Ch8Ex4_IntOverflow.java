package scenarioBased.part3;/*
 * Chapter 8, Exercise 4 (Starred):
 * Create a class Int based on Exercise 1 in Chapter 6. Overload four integer arithmetic
 * operators (+, -, *, and /) so that they operate on objects of type Int.
 * If the result of any such arithmetic operation exceeds the normal range of ints
 * (in a 32-bit environment) — from 2,147,483,648 to -2,147,483,647 — have the operator
 * print a warning and terminate the program.
 * Hint: To facilitate checking for overflow, perform the calculations using type long double
 * (use long in Java). Write a program to test this class.
 */

public class Ch8Ex4_IntOverflow {

    static class Int {
        private int value;
        private static final long MAX_INT =  2_147_483_647L;
        private static final long MIN_INT = -2_147_483_648L;

        public Int() { value = 0; }
        public Int(int v) { value = v; }

        private static int checkOverflow(long result) {
            if (result > MAX_INT || result < MIN_INT) {
                System.out.println("OVERFLOW ERROR: Result " + result + " exceeds int range. Terminating.");
                System.exit(1);
            }
            return (int) result;
        }

        public Int add(Int other) {
            return new Int(checkOverflow((long) this.value + other.value));
        }

        public Int subtract(Int other) {
            return new Int(checkOverflow((long) this.value - other.value));
        }

        public Int multiply(Int other) {
            return new Int(checkOverflow((long) this.value * other.value));
        }

        public Int divide(Int other) {
            if (other.value == 0) {
                System.out.println("Division by zero! Terminating.");
                System.exit(1);
            }
            return new Int(this.value / other.value);
        }

        public void display() { System.out.println(value); }
        public int getValue() { return value; }
    }

    public static void main(String[] args) {
        Int a = new Int(1_000_000);
        Int b = new Int(2_000);

        System.out.print("a + b = "); a.add(b).display();
        System.out.print("a - b = "); a.subtract(b).display();
        System.out.print("a * b = "); a.multiply(b).display();
        System.out.print("a / b = "); a.divide(b).display();

        // Test overflow
        System.out.println("\nTesting overflow with large values...");
        Int big = new Int(Integer.MAX_VALUE);
        Int one = new Int(1);
        big.add(one); // Should trigger overflow warning
    }
}
