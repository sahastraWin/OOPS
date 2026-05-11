package scenarioBased.part3;/*
 * Chapter 14 (Templates & Exceptions), Exercise 8:
 * Add an exception class to the ARROVER3 program in Chapter 8 so that an out-of-bounds
 * index will trigger the exception. The catch block can print an error message for the user.
 */

public class Ch14Ex8_SafeArrayException {

    // Custom exception for out-of-bounds array access
    static class ArrayBoundsException extends RuntimeException {
        private int index;
        private int lower;
        private int upper;

        public ArrayBoundsException(int idx, int lo, int hi) {
            super(String.format("Index %d out of bounds [%d, %d]", idx, lo, hi));
            index = idx; lower = lo; upper = hi;
        }

        public int getIndex() { return index; }
    }

    static class SafeArray {
        private int[] arr;
        private int lowerBound;
        private int upperBound;

        public SafeArray() { this(0, 99); }

        public SafeArray(int lower, int upper) {
            this.lowerBound = lower;
            this.upperBound = upper;
            this.arr = new int[upper - lower + 1];
        }

        public int get(int index) {
            if (index < lowerBound || index > upperBound)
                throw new ArrayBoundsException(index, lowerBound, upperBound);
            return arr[index - lowerBound];
        }

        public void set(int index, int value) {
            if (index < lowerBound || index > upperBound)
                throw new ArrayBoundsException(index, lowerBound, upperBound);
            arr[index - lowerBound] = value;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== SafeArray with Exception Handling ===");
        SafeArray sa = new SafeArray(1, 5);

        // Valid accesses
        for (int i = 1; i <= 5; i++) {
            try { sa.set(i, i * 10); System.out.println("Set [" + i + "] = " + i*10); }
            catch (ArrayBoundsException e) { System.out.println("Error: " + e.getMessage()); }
        }

        // Invalid accesses
        int[] badIndices = {0, 6, -1, 100};
        for (int idx : badIndices) {
            try {
                sa.get(idx);
            } catch (ArrayBoundsException e) {
                System.out.println("Caught: " + e.getMessage());
            }
        }

        System.out.println("\nProgram continues normally after all exceptions.");
    }
}
