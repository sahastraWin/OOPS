package scenarioBased.part3;/*
 * Chapter 15 (STL/Collections), Exercise 1 (Starred):
 * Write a program that applies the sort() algorithm to an array of floating point values
 * entered by the user, and displays the result.
 * (In Java, we use Arrays.sort() which corresponds to the STL sort() algorithm.)
 */

import java.util.*;

public class Ch15Ex1_SortFloats {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Sort Array of Floats (STL sort equivalent) ===");

        List<Double> numbers = new ArrayList<>();

        System.out.println("Enter floating point numbers (non-numeric to stop):");
        int i = 0;
        while (true) {
            System.out.printf("Number[%d]: ", i++);
            String input = sc.nextLine().trim();
            try {
                numbers.add(Double.parseDouble(input));
            } catch (NumberFormatException e) {
                break;
            }
        }

        if (numbers.isEmpty()) {
            System.out.println("No numbers entered."); return;
        }

        System.out.println("\nBefore sort: " + numbers);

        // Apply sort() algorithm
        Collections.sort(numbers);

        System.out.println("After sort:  " + numbers);

        sc.close();
    }
}
