package scenarioBased.part3;/*
 * Chapter 3, Exercise 2
 * Write a temperature-conversion program that gives the user the option of converting
 * Fahrenheit to Celsius or Celsius to Fahrenheit. Then carry out the conversion.
 * Use floating-point numbers.
 * Example:
 *   Type 1 to convert Fahrenheit to Celsius,
 *        2 to convert Celsius to Fahrenheit: 1
 *   Enter temperature in Fahrenheit: 70
 *   In Celsius that's 21.111111
 */

import java.util.Scanner;

public class Ch3_Ex2_TempConversion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Type 1 to convert Fahrenheit to Celsius,\n     2 to convert Celsius to Fahrenheit: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.print("Enter temperature in Fahrenheit: ");
            double f = scanner.nextDouble();
            double c = (f - 32.0) * 5.0 / 9.0;
            System.out.printf("In Celsius that's %f%n", c);
        } else if (choice == 2) {
            System.out.print("Enter temperature in Celsius: ");
            double c = scanner.nextDouble();
            double f = c * 9.0 / 5.0 + 32.0;
            System.out.printf("In Fahrenheit that's %f%n", f);
        } else {
            System.out.println("Invalid choice.");
        }
        scanner.close();
    }
}
