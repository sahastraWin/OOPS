package scenarioBased.part2;/*
 * Question 23: Write a java program that reads line of integers.
 * Display each integer and also display sum of all integers.
 */

import java.util.Scanner;

public class Q23_IntegerSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sum = 0;
        int count = 0;

        System.out.println("Enter integers one per line (type 'done' or a non-integer to stop):");
        System.out.println("-------------------------------------------");

        while (sc.hasNextInt()) {
            int num = sc.nextInt();
            System.out.println("Read: " + num);
            sum += num;
            count++;
        }

        System.out.println("-------------------------------------------");
        System.out.println("Total integers entered: " + count);
        System.out.println("Sum of all integers: " + sum);

        if (count > 0) {
            System.out.printf("Average: %.2f%n", (double) sum / count);
        }
    }
}
