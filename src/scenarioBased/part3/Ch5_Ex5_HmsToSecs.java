package scenarioBased.part3;/*
 * Chapter 5, Exercise 5
 * Write a function called hms_to_secs() that takes three int values — for hours,
 * minutes, and seconds — as arguments, and returns the equivalent time in seconds (type long).
 * Create a program that exercises this function by repeatedly obtaining a time value in
 * hours, minutes, and seconds from the user (format 12:59:59), calling the function,
 * and displaying the value of seconds it returns.
 */

import java.util.Scanner;

public class Ch5_Ex5_HmsToSecs {

    static long hms_to_secs(int hours, int minutes, int seconds) {
        return (long) hours * 3600 + (long) minutes * 60 + seconds;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char again;

        do {
            System.out.print("Enter time (HH:MM:SS): ");
            String[] parts = scanner.next().split(":");
            int h = Integer.parseInt(parts[0]);
            int m = Integer.parseInt(parts[1]);
            int s = Integer.parseInt(parts[2]);

            System.out.println("Total seconds: " + hms_to_secs(h, m, s));
            System.out.print("Do another (y/n)? ");
            again = scanner.next().charAt(0);
        } while (again == 'y' || again == 'Y');

        scanner.close();
    }
}
