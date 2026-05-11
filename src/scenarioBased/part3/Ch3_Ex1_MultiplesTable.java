package scenarioBased.part3;
/*
 * Chapter 3, Exercise 1
 * Assume you want to generate a table of multiples of any given number.
 * Write a program that allows the user to enter the number and then generates the table,
 * formatting it into 10 columns and 20 lines.
 * Example (Enter a number: 7):
 *    7  14  21  28  35  42  49  56  63  70
 *   77  84  91  98 105 112 119 126 133 140
 *  147 154 161 168 175 182 189 196 203 210
 */

import java.util.Scanner;

public class Ch3_Ex1_MultiplesTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = scanner.nextInt();

        for (int i = 1; i <= 200; i++) {
            System.out.printf("%5d", num * i);
            if (i % 10 == 0) System.out.println();
        }
        scanner.close();
    }
}
