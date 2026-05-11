package scenarioBased.part3;/*
 * Chapter 3, Exercise 10
 * Write another version of the program from Exercise 7 so that, instead of finding the
 * final amount of your investment, you tell the program the final amount and it figures
 * out how many years it will take, at a fixed rate of interest compounded yearly, to reach
 * this amount. Use an integer value for the year. (A while loop is appropriate here.)
 */

import java.util.Scanner;

public class Ch3_Ex10_YearsToGoal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter initial amount: ");
        double amount = scanner.nextDouble();
        System.out.print("Enter target amount: ");
        double target = scanner.nextDouble();
        System.out.print("Enter interest rate (percent per year): ");
        double rate = scanner.nextDouble() / 100.0;

        if (amount >= target) {
            System.out.println("You already have the target amount!");
        } else {
            int years = 0;
            while (amount < target) {
                amount += amount * rate;
                years++;
            }
            System.out.printf("It will take %d years to reach the target.%n", years);
        }
        scanner.close();
    }
}
