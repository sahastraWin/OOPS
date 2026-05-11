package scenarioBased.part3;/*
 * Chapter 12, Exercise 5:
 * Create a time class that includes integer member values for hours, minutes, and seconds.
 * Make a member function get_time() that gets a time value from the user, and a function
 * put_time() that displays a time in 12:59:59 format. Add error checking to the get_time()
 * function to minimize user mistakes. This function should request hours, minutes, and
 * seconds separately, and check each one for ios error status flags and the correct range.
 * Hours should be between 0 and 23, and minutes and seconds between 0 and 59.
 * Don't input these values as strings and then convert them; read them directly as integers.
 *
 * In main(), use a loop to repeatedly get a time value from the user with get_time() and
 * then display it with put_time():
 *   Enter hours: 11
 *   Enter minutes: 59
 *   Enter seconds: 59
 *   time = 11:59:59
 *   Do another (y/n)?
 */

import java.util.Scanner;

public class Ch12Ex5_TimeErrorCheck {

    static Scanner sc = new Scanner(System.in);

    static class Time {
        private int hours;
        private int minutes;
        private int seconds;

        // Read validated integer in a range
        private static int readIntInRange(String prompt, int min, int max) {
            while (true) {
                System.out.print(prompt);
                String input = sc.nextLine().trim();
                try {
                    int val = Integer.parseInt(input);
                    if (val >= min && val <= max) return val;
                    System.out.printf("Value must be between %d and %d%n", min, max);
                } catch (NumberFormatException e) {
                    System.out.println("Incorrect input — please enter an integer.");
                }
            }
        }

        public void get_time() {
            hours   = readIntInRange("Enter hours: ", 0, 23);
            minutes = readIntInRange("Enter minutes: ", 0, 59);
            seconds = readIntInRange("Enter seconds: ", 0, 59);
        }

        public void put_time() {
            System.out.printf("time = %d:%02d:%02d%n", hours, minutes, seconds);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Time Input with Error Checking ===");
        Time t = new Time();

        while (true) {
            t.get_time();
            t.put_time();
            System.out.print("Do another (y/n)? ");
            if (!sc.nextLine().trim().equalsIgnoreCase("y")) break;
        }

        sc.close();
    }
}
