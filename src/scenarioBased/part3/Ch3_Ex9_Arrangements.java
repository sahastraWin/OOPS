package scenarioBased.part3;/*
 * Chapter 3, Exercise 9
 * Suppose you give a dinner party for six guests, but your table seats only four.
 * In how many ways can four of the six guests arrange themselves at the table?
 * The number of possible arrangements of six guests in four chairs is 6*5*4*3 = 360.
 * Write a program that calculates the number of possible arrangements for any number
 * of guests and any number of chairs.
 * (Assume there will never be fewer guests than chairs.)
 */

import java.util.Scanner;

public class Ch3_Ex9_Arrangements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of guests: ");
        int guests = scanner.nextInt();
        System.out.print("Enter number of chairs: ");
        int chairs = scanner.nextInt();

        if (guests < chairs) {
            System.out.println("Error: fewer guests than chairs.");
        } else {
            long arrangements = 1;
            for (int i = 0; i < chairs; i++) {
                arrangements *= (guests - i);
            }
            System.out.printf("Number of possible arrangements: %d%n", arrangements);
        }
        scanner.close();
    }
}
