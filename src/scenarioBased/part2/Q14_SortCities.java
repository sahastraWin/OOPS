package scenarioBased.part2;/*
 * Question 14: Write a program in Java to accept name of cities from the user
 * and sort them in ascending order.
 * (Use Command Line Arguments)
 * Usage: java Q14_SortCities Mumbai Pune Delhi Nagpur Kolkata
 */

import java.util.Arrays;

public class Q14_SortCities {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Q14_SortCities <City1> <City2> ...");
            System.out.println("Example: java Q14_SortCities Mumbai Pune Delhi");
            return;
        }

        System.out.println("Cities before sorting:");
        for (String city : args) System.out.print(city + "  ");

        // Sort in ascending (alphabetical) order
        Arrays.sort(args, String.CASE_INSENSITIVE_ORDER);

        System.out.println("\n\nCities after sorting (ascending order):");
        for (int i = 0; i < args.length; i++) {
            System.out.println((i + 1) + ". " + args[i]);
        }
    }
}
