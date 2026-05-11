package scenarioBased.part1;/*

 * Question 1 - Chapter 2: Java Programming Basics
 * Write a program that converts distances.
 * The user enters a distance in light-years, and the program
 * converts it to miles (1 light-year = 5.88 × 10^12 miles).
 */

import java.util.Scanner;

public class Q01_LightYearsToMiles {
	static final double MILES_PER_LIGHT_YEAR = 5.88e12;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter distance in light-years: ");
		double lightYears = sc.nextDouble();
		double miles = lightYears * MILES_PER_LIGHT_YEAR;
		System.out.printf("%.2f light-year(s) = %.2e miles%n", lightYears, miles);
		sc.close();
	}
}
