package scenarioBased.part3;/*
 * Chapter 3, Exercise 7
 * Write a program that calculates how much money you'll end up with if you invest
 * an amount of money at a fixed interest rate, compounded yearly.
 * Have the user furnish the initial amount, the number of years, and the yearly interest rate in percent.
 * Example:
 *   Enter initial amount: 3000
 *   Enter number of years: 10
 *   Enter interest rate (percent per year): 5.5
 *   At the end of 10 years, you will have 5124.43 dollars.
 *
 * At end of year 1: 3000 + (3000 * 0.055) = 3165
 * At end of year 2: 3165 + (3165 * 0.055) = 3339.08
 * ... and so on. A for loop makes the calculation easy.
 */

import java.util.Scanner;

public class Ch3_Ex7_CompoundInterest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter initial amount: ");
        double amount = scanner.nextDouble();
        System.out.print("Enter number of years: ");
        int years = scanner.nextInt();
        System.out.print("Enter interest rate (percent per year): ");
        double rate = scanner.nextDouble() / 100.0;

        for (int y = 1; y <= years; y++) {
            amount += amount * rate;
        }

        System.out.printf("At the end of %d years, you will have %.2f dollars.%n", years, amount);
        scanner.close();
    }
}
