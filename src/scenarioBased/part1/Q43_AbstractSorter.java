package scenarioBased.part1;/*
/*
 * Question 43 - Chapter 11: Virtual Functions and Other Subtleties
 * Design an abstract base class Sorter with an abstract method sort().
 * Derive classes BubbleSorter and SelectionSorter.
 * Each sorts an integer array differently.
 * Show both sorting the same input in main().
 */

import java.util.Arrays;

public class Q43_AbstractSorter {
    abstract static class Sorter {
        abstract void sort(int[] arr);
        String name() { return getClass().getSimpleName(); }
    }

    static class BubbleSorter extends Sorter {
        @Override void sort(int[] arr) {
            int n = arr.length;
            for (int i = 0; i < n - 1; i++)
                for (int j = 0; j < n - i - 1; j++)
                    if (arr[j] > arr[j + 1]) {
                        int t = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = t;
                    }
        }
    }

    static class SelectionSorter extends Sorter {
        @Override void sort(int[] arr) {
            int n = arr.length;
            for (int i = 0; i < n - 1; i++) {
                int minIdx = i;
                for (int j = i + 1; j < n; j++)
                    if (arr[j] < arr[minIdx]) minIdx = j;
                int t = arr[minIdx]; arr[minIdx] = arr[i]; arr[i] = t;
            }
        }
    }

    public static void main(String[] args) {
        int[] original = {64, 25, 12, 22, 11, 90, 3};
        Sorter[] sorters = { new BubbleSorter(), new SelectionSorter() };

        for (Sorter s : sorters) {
            int[] arr = Arrays.copyOf(original, original.length);
            System.out.print(s.name() + " - Before: " + Arrays.toString(arr));
            s.sort(arr);
            System.out.println(" | After: " + Arrays.toString(arr));
        }
    }
}
