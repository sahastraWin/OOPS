package scenarioBased.part3;/*
 * Chapter 7, Exercise 4
 * Start with a program that allows the user to input a number of integers and stores them
 * in an int array. Write a function called maxint() that goes through the array element by
 * element, looking for the largest one. The function should take the address of the array
 * and the number of elements in it, and return the index number of the largest element.
 * The program should call this function and then display the largest element and its index number.
 */

import java.util.Scanner;

public class Ch7_Ex4_MaxInt {

    static int maxint(int[] arr, int size) {
        int maxIdx = 0;
        for (int i = 1; i < size; i++) {
            if (arr[i] > arr[maxIdx]) maxIdx = i;
        }
        return maxIdx;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many integers? ");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.printf("Enter integer #%d: ", i + 1);
            arr[i] = scanner.nextInt();
        }

        int maxIdx = maxint(arr, n);
        System.out.printf("Largest element: %d (at index %d)%n", arr[maxIdx], maxIdx);
        scanner.close();
    }
}
