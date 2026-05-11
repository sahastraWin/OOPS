package scenarioBased.part1;/*
/*
 * Question 40 - Chapter 10: Pointers
 * Write a function that takes an array of integers and its size,
 * sorts the array using bubble sort, and returns the sorted array.
 * Demonstrate in main().
 */

import java.util.Arrays;

public class Q40_BubbleSort {
    static int[] bubbleSort(int[] arr) {
        int[] sorted = Arrays.copyOf(arr, arr.length);
        int n = sorted.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (sorted[j] > sorted[j + 1]) {
                    int temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
        return sorted;
    }

    public static void main(String[] args) {
        int[] original = {64, 25, 12, 22, 11, 90, 45, 3, 77, 38};
        System.out.println("Original: " + Arrays.toString(original));
        int[] sorted = bubbleSort(original);
        System.out.println("Sorted:   " + Arrays.toString(sorted));
    }
}
