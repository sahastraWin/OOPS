package scenarioBased.part3;/*
 * Chapter 14 (Templates & Exceptions), Exercise 1 (Starred):
 * Write a template function that returns the average of all the elements of an array.
 * The arguments to the function should be the array name and the size of the array (type int).
 * In main(), exercise the function with arrays of type int, long, double, and char.
 * (In Java, we use generics with Number to simulate C++ function templates.)
 */

public class Ch14Ex1_ArrayAverage {

    // Generic average function for Number types (simulates C++ template)
    @SuppressWarnings("unchecked")
    static <T extends Number> double average(T[] arr, int size) {
        if (size <= 0) throw new IllegalArgumentException("Array size must be > 0");
        double sum = 0;
        for (int i = 0; i < size; i++) sum += arr[i].doubleValue();
        return sum / size;
    }

    // Overload for int primitive array
    static double average(int[] arr, int size) {
        double sum = 0;
        for (int i = 0; i < size; i++) sum += arr[i];
        return sum / size;
    }

    // Overload for long primitive array
    static double average(long[] arr, int size) {
        double sum = 0;
        for (int i = 0; i < size; i++) sum += arr[i];
        return sum / size;
    }

    // Overload for double primitive array
    static double average(double[] arr, int size) {
        double sum = 0;
        for (int i = 0; i < size; i++) sum += arr[i];
        return sum / size;
    }

    // For char: compute average ASCII value
    static double average(char[] arr, int size) {
        double sum = 0;
        for (int i = 0; i < size; i++) sum += arr[i];
        return sum / size;
    }

    public static void main(String[] args) {
        System.out.println("=== Generic Array Average (Template Function) ===");

        // int array
        int[] intArr = {10, 20, 30, 40, 50};
        System.out.printf("int array average:    %.2f%n", average(intArr, intArr.length));

        // long array
        long[] longArr = {100L, 200L, 300L, 400L, 500L};
        System.out.printf("long array average:   %.2f%n", average(longArr, longArr.length));

        // double array
        double[] dblArr = {1.5, 2.5, 3.5, 4.5, 5.5};
        System.out.printf("double array average: %.2f%n", average(dblArr, dblArr.length));

        // char array (average of ASCII values)
        char[] charArr = {'A', 'B', 'C', 'D', 'E'}; // ASCII 65-69
        System.out.printf("char array average:   %.2f (ASCII)%n", average(charArr, charArr.length));

        // Integer wrapper array (uses generic version)
        Integer[] intWrap = {2, 4, 6, 8, 10};
        System.out.printf("Integer[] average:    %.2f%n", average(intWrap, intWrap.length));
    }
}
