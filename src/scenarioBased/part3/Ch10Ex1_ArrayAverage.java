package scenarioBased.part3;/*
 * Chapter 10, Exercise 1 (Starred):
 * Write a program that reads a group of numbers from the user and places them in an array
 * of type float. Once the numbers are stored in the array, the program should average them
 * and print the result. Use pointer notation wherever possible.
 * (In Java, we simulate pointer notation using array indices and explicit index manipulation.)
 */

import java.util.Scanner;

public class Ch10Ex1_ArrayAverage {

	// Simulates pointer-based summation
	static float sumArray(float[] arr, int size) {
		float total = 0;
		for (int i = 0; i < size; i++) {
			total += arr[i]; // simulates *(arr + i)
		}
		return total;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int MAX = 100;
		float[] numbers = new float[MAX];
		int count = 0;

		System.out.println("=== Array Average (Pointer Notation) ===");
		System.out.println("Enter numbers (non-numeric to stop):");

		while (count < MAX) {
			System.out.printf("Number[%d]: ", count);
			String input = sc.nextLine().trim();
			try {
				numbers[count++] = Float.parseFloat(input);
			} catch (NumberFormatException e) {
				break;
			}
		}

		if (count == 0) {
			System.out.println("No numbers entered.");
			return;
		}

		float sum = sumArray(numbers, count);
		float average = sum / count;

		System.out.printf("%nNumbers entered: %d%n", count);
		System.out.printf("Sum: %.2f%n", sum);
		System.out.printf("Average: %.4f%n", average);

		sc.close();
	}
}
