package scenarioBased.part3;/*
 * Chapter 2, Exercise 1
 * Assuming there are 7.481 gallons in a cubic foot, write a program that asks the user to
 * enter a number of gallons, and then displays the equivalent in cubic feet.
 */

import java.util.Scanner;

public class Ch2_Ex1_GallonsToCubicFeet {
    private static final double GALLONS_PER_CUBIC_FOOT = 7.481;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of gallons: ");
        double gallons = scanner.nextDouble();
        double cubicFeet = gallons / GALLONS_PER_CUBIC_FOOT;
        System.out.printf("%.4f gallons = %.4f cubic feet%n", gallons, cubicFeet);
        scanner.close();
    }
}
