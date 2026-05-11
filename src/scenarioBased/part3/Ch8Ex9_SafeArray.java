package scenarioBased.part3;/*
 * Chapter 8, Exercise 9:
 * Augment the safearay class in the ARROVER3 program in this chapter so that the user
 * can specify both the upper and lower bounds of the array (indexes running from 100 to 200,
 * for example). Have the overloaded subscript operator check the index each time the
 * array is accessed to ensure that it is not out of bounds. You'll need to add a two-argument
 * constructor that specifies the upper and lower bounds.
 */

public class Ch8Ex9_SafeArray {

    static class SafeArray {
        private int[] arr;
        private int lowerBound;
        private int upperBound;

        // Default constructor: 0 to 99
        public SafeArray() { this(0, 99); }

        // Two-argument constructor with bounds
        public SafeArray(int lower, int upper) {
            if (lower > upper) throw new IllegalArgumentException("Lower bound must be <= upper bound");
            this.lowerBound = lower;
            this.upperBound = upper;
            this.arr = new int[upper - lower + 1];
        }

        // Overloaded [] - get element
        public int get(int index) {
            checkBounds(index);
            return arr[index - lowerBound];
        }

        // Overloaded [] - set element
        public void set(int index, int value) {
            checkBounds(index);
            arr[index - lowerBound] = value;
        }

        private void checkBounds(int index) {
            if (index < lowerBound || index > upperBound) {
                System.out.printf("Index %d out of bounds [%d, %d]!%n", index, lowerBound, upperBound);
                System.exit(1);
            }
        }

        public void display() {
            for (int i = lowerBound; i <= upperBound; i++) {
                System.out.printf("arr[%d] = %d%n", i, get(i));
            }
        }
    }

    public static void main(String[] args) {
        SafeArray sa = new SafeArray(100, 110);

        // Fill with values
        for (int i = 100; i <= 110; i++) sa.set(i, i * 2);

        System.out.println("=== Safe Array with bounds [100, 110] ===");
        sa.display();

        System.out.println("\nAccessing valid index 105: " + sa.get(105));

        System.out.println("\nAccessing out-of-bounds index 50...");
        sa.get(50); // Should trigger error
    }
}
