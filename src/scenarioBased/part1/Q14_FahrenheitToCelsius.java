package scenarioBased.part1;/*
/*
 * Question 14 - Chapter 5: Functions
 * Create a function that takes a temperature in Fahrenheit and returns
 * the equivalent in Celsius.
 * Write a program where the user enters temperatures repeatedly
 * and sees conversions.
 */

import java.util.Scanner;

public class Q14_FahrenheitToCelsius {
    static double toCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5.0 / 9;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Fahrenheit to Celsius Converter (enter 9999 to quit)");
        while (true) {
            System.out.print("Enter temperature in Fahrenheit: ");
            double f = sc.nextDouble();
            if (f == 9999) break;
            System.out.printf("%.2f F = %.2f C%n", f, toCelsius(f));
        }
        sc.close();
    }
}
