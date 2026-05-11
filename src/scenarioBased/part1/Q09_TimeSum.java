package scenarioBased.part1;/*
/*
 * Question 9 - Chapter 4: Structures
 * Create a structure to represent a Time value (hours, minutes, seconds).
 * Write a program that asks the user to enter two time values
 * and calculates their sum.
 */

import java.util.Scanner;

public class Q09_TimeSum {
    static class Time {
        int hours, minutes, seconds;

        Time(int h, int m, int s) {
            int total = h * 3600 + m * 60 + s;
            hours = total / 3600;
            minutes = (total % 3600) / 60;
            seconds = total % 60;
        }

        static Time add(Time a, Time b) {
            return new Time(a.hours + b.hours, a.minutes + b.minutes, a.seconds + b.seconds);
        }

        @Override
        public String toString() {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }
    }

    static Time readTime(Scanner sc, String label) {
        System.out.print(label + " (hh mm ss): ");
        return new Time(sc.nextInt(), sc.nextInt(), sc.nextInt());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Time t1 = readTime(sc, "Enter first time");
        Time t2 = readTime(sc, "Enter second time");
        System.out.println("Sum: " + Time.add(t1, t2));
        sc.close();
    }
}
