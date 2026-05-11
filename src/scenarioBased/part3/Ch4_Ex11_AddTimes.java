package scenarioBased.part3;/*
 * Chapter 4, Exercise 11
 * Use the time structure from Exercise 9, and write a program that obtains two time
 * values from the user in 12:59:59 format, stores them in struct time variables,
 * converts each one to seconds (type int), adds these quantities, converts the result
 * back to hours-minutes-seconds, stores the result in a time structure, and finally
 * displays the result in 12:59:59 format.
 */

import java.util.Scanner;

public class Ch4_Ex11_AddTimes {

    static class Time {
        int hours, minutes, seconds;

        Time(int h, int m, int s) { this.hours = h; this.minutes = m; this.seconds = s; }

        Time(long totalSecs) {
            this.hours   = (int) (totalSecs / 3600);
            this.minutes = (int) ((totalSecs % 3600) / 60);
            this.seconds = (int) (totalSecs % 60);
        }

        long toSeconds() { return (long) hours * 3600 + minutes * 60 + seconds; }

        @Override
        public String toString() { return String.format("%02d:%02d:%02d", hours, minutes, seconds); }
    }

    private static Time parse(String s) {
        String[] p = s.split(":");
        return new Time(Integer.parseInt(p[0]), Integer.parseInt(p[1]), Integer.parseInt(p[2]));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first time (HH:MM:SS): ");
        Time t1 = parse(scanner.next());
        System.out.print("Enter second time (HH:MM:SS): ");
        Time t2 = parse(scanner.next());

        Time result = new Time(t1.toSeconds() + t2.toSeconds());
        System.out.println("Sum = " + result);
        scanner.close();
    }
}
