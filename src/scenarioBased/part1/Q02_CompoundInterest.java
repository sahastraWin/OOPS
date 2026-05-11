package scenarioBased.part1;
/*
 * Question 2 - Chapter 2: Java Programming Basics
 * Write a program that calculates how much money you'll end up with
 * if you invest an amount at a fixed interest rate, compounded yearly.
 * Have the user provide the initial amount, number of years, and yearly interest rate.
 */

import java.util.Scanner;

public class Q02_CompoundInterest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter initial investment amount: ");
        double principal = sc.nextDouble();
        System.out.print("Enter number of years: ");
        int years = sc.nextInt();
        System.out.print("Enter yearly interest rate (e.g. 5 for 5%): ");
        double rate = sc.nextDouble() / 100.0;

        double amount = principal * Math.pow(1 + rate, years);
        System.out.printf("After %d year(s), your investment will be: %.2f%n", years, amount);
        sc.close();
    }
}
