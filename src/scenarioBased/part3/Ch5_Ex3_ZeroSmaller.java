package scenarioBased.part3;/*
 * Chapter 5, Exercise 3
 * Write a function called zeroSmaller() that is passed two int arguments by reference
 * and then sets the smaller of the two numbers to 0. Write a main() program to exercise this function.
 * Note: In Java, primitives are passed by value. We simulate pass-by-reference using an int array.
 */

import java.util.Scanner;

public class Ch5_Ex3_ZeroSmaller {

    /** Sets arr[0] or arr[1] to 0, whichever is smaller. */
    static void zeroSmaller(int[] arr) {
        if (arr[0] <= arr[1]) arr[0] = 0;
        else                  arr[1] = 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter two integers: ");
        int[] values = {scanner.nextInt(), scanner.nextInt()};

        zeroSmaller(values);
        System.out.printf("After zeroSmaller: %d, %d%n", values[0], values[1]);
        scanner.close();
    }
}
