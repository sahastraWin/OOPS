package scenarioBased.part3;/*
 * Chapter 5, Exercise 8
 * Write a function called swap() that interchanges two int values passed to it by the
 * calling program. (The function swaps the values of the variables in the calling program.)
 * You'll need to decide how to pass the arguments. Create a main() program to exercise the function.
 * Note: Java primitives are pass-by-value; use an int[] array to simulate pass-by-reference.
 */

import java.util.Scanner;

public class Ch5_Ex8_Swap {

    static void swap(int[] a, int[] b) {
        int temp = a[0];
        a[0] = b[0];
        b[0] = temp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter two integers to swap: ");
        int[] a = {scanner.nextInt()};
        int[] b = {scanner.nextInt()};

        System.out.printf("Before swap: a=%d, b=%d%n", a[0], b[0]);
        swap(a, b);
        System.out.printf("After swap:  a=%d, b=%d%n", a[0], b[0]);
        scanner.close();
    }
}
