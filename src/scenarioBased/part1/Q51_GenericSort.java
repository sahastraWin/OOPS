package scenarioBased.part1;/*
/*
 * Question 51 - Chapter 14: Templates and Exceptions
 * Create a generic function sort() that can sort an array of any
 * Comparable type. Test it with arrays of integers and doubles.
 */

import java.util.Arrays;

public class Q51_GenericSort {
    static <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T temp = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = temp;
                }
    }

    public static void main(String[] args) {
        Integer[] ints = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Before: " + Arrays.toString(ints));
        sort(ints);
        System.out.println("After:  " + Arrays.toString(ints));

        Double[] doubles = {3.14, 1.41, 2.71, 0.57, 1.73};
        System.out.println("\nBefore: " + Arrays.toString(doubles));
        sort(doubles);
        System.out.println("After:  " + Arrays.toString(doubles));

        String[] strings = {"banana", "apple", "cherry", "date"};
        System.out.println("\nBefore: " + Arrays.toString(strings));
        sort(strings);
        System.out.println("After:  " + Arrays.toString(strings));
    }
}
