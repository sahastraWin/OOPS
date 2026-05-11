package scenarioBased.part3;/*
 * Chapter 4, Exercise 9
 * Create a structure called time. Its three members, all type int, should be called
 * hours, minutes, and seconds. Write a program that prompts the user to enter a time
 * value in hours, minutes, and seconds. Store the time in a variable of type struct time,
 * and finally print out the total number of seconds:
 *   long totalsecs = t1.hours*3600 + t1.minutes*60 + t1.seconds
 */

import java.util.Scanner;

public class Ch4_Ex9_Time {

    static class Time {
        int hours, minutes, seconds;

        Time(int hours, int minutes, int seconds) {
            this.hours = hours;
            this.minutes = minutes;
            this.seconds = seconds;
        }

        long toSeconds() {
            return (long) hours * 3600 + (long) minutes * 60 + seconds;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter time (HH:MM:SS): ");
        String[] parts = scanner.next().split(":");

        Time t1 = new Time(
            Integer.parseInt(parts[0]),
            Integer.parseInt(parts[1]),
            Integer.parseInt(parts[2])
        );

        System.out.println("Total seconds: " + t1.toSeconds());
        scanner.close();
    }
}
