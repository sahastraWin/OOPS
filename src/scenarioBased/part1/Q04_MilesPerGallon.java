package scenarioBased.part1;/*
/*
 * Question 4 - Chapter 2: Java Programming Basics
 * Create a program that allows a user to enter the number of miles driven
 * and the number of gallons of gas used.
 * Then calculate and display the miles per gallon obtained.
 */

import java.util.Scanner;

public class Q04_MilesPerGallon {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter miles driven: ");
        double miles = sc.nextDouble();
        System.out.print("Enter gallons of gas used: ");
        double gallons = sc.nextDouble();

        if (gallons <= 0) {
            System.out.println("Gallons must be greater than zero.");
        } else {
            System.out.printf("Miles per gallon: %.2f%n", miles / gallons);
        }
        sc.close();
    }
}
