package scenarioBased.part3;/*
 * Chapter 14 (Templates & Exceptions), Exercise 9:
 * Modify the exception class in Exercise 8 (adapted from ARROVER3) so that the error
 * message in the catch block reports the value of the index that caused the exception.
 * (The exception object must carry the bad index value so the catch block can display it.)
 */

public class Ch14Ex9_SafeArrayIndexReport {

    // Exception that carries the bad index value
    static class OutOfRangeException extends RuntimeException {
        private final int badIndex;
        private final int lowerBound;
        private final int upperBound;

        public OutOfRangeException(int idx, int lo, int hi) {
            // The message is set in getMessage()
            super(); // We'll override getMessage()
            badIndex    = idx;
            lowerBound  = lo;
            upperBound  = hi;
        }

        @Override
        public String getMessage() {
            return String.format(
                "Out-of-range index: %d  (valid range: %d to %d)",
                badIndex, lowerBound, upperBound
            );
        }

        public int getBadIndex()   { return badIndex; }
        public int getLowerBound() { return lowerBound; }
        public int getUpperBound() { return upperBound; }
    }

    static class SafeArray {
        private int[] arr;
        private final int lower;
        private final int upper;

        public SafeArray(int lo, int hi) {
            lower = lo; upper = hi;
            arr   = new int[hi - lo + 1];
        }

        public int get(int idx) {
            if (idx < lower || idx > upper) throw new OutOfRangeException(idx, lower, upper);
            return arr[idx - lower];
        }

        public void set(int idx, int val) {
            if (idx < lower || idx > upper) throw new OutOfRangeException(idx, lower, upper);
            arr[idx - lower] = val;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== SafeArray: Exception Reports Bad Index ===");
        SafeArray sa = new SafeArray(10, 20);

        // Fill valid elements
        for (int i = 10; i <= 20; i++) sa.set(i, i);

        // Test various indices
        int[] testIndices = {10, 15, 20, 9, 21, 0, -5, 100};
        for (int idx : testIndices) {
            try {
                int val = sa.get(idx);
                System.out.printf("sa[%3d] = %d%n", idx, val);
            } catch (OutOfRangeException e) {
                System.out.println("Exception caught — " + e.getMessage());
                System.out.printf("  (Bad index was: %d)%n", e.getBadIndex());
            }
        }
    }
}
