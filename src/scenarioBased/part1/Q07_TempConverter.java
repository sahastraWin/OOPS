package scenarioBased.part1;/*
/*
 * Question 7 - Chapter 3: Loops and Decisions
 * Create a program that converts Celsius to Fahrenheit (or vice versa)
 * repeatedly until the user enters a sentinel value to quit.
 * Enter 999 to quit.
 */

import java.util.Scanner;

public class Q07_TempConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Temperature Converter (enter 999 to quit)");
        System.out.println("Choose conversion: 1=C to F  2=F to C");
        int choice = sc.nextInt();

        while (true) {
            System.out.print("Enter temperature (999 to quit): ");
            double temp = sc.nextDouble();
            if (temp == 999) break;
            if (choice == 1) {
                System.out.printf("%.2f C = %.2f F%n", temp, (temp * 9.0 / 5) + 32);
            } else {
                System.out.printf("%.2f F = %.2f C%n", temp, (temp - 32) * 5.0 / 9);
            }
        }
        System.out.println("Goodbye!");
        sc.close();
    }
}
