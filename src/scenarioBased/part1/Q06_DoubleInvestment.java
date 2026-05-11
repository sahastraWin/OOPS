package scenarioBased.part1;/*
package scenarioBased.part1;/*
/*
 * Question 6 - Chapter 3: Loops and Decisions
 * Write a program that calculates how many years it will take to double
 * an investment at a fixed interest rate compounded yearly.
 * The user inputs the initial amount and interest rate.
 */

import java.util.Scanner;

public class Q06_DoubleInvestment {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter initial investment: ");
        double principal = sc.nextDouble();
        System.out.print("Enter yearly interest rate (e.g. 5 for 5%): ");
        double rate = sc.nextDouble() / 100.0;

        double target = principal * 2;
        double amount = principal;
        int years = 0;
        while (amount < target) {
            amount *= (1 + rate);
            years++;
        }
        System.out.printf("It will take %d year(s) to double your investment.%n", years);
        sc.close();
    }
}
