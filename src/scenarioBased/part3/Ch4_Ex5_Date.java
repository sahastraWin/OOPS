package scenarioBased.part3;/*
 * Chapter 4, Exercise 5
 * Create a structure of type date that contains three members: the month, the day of the
 * month, and the year, all of type int. Have the user enter a date in the format 12/31/2001,
 * store it in a variable of type struct date, then retrieve the values and print them in the same format.
 */

import java.util.Scanner;

public class Ch4_Ex5_Date {

    static class Date {
        int month, day, year;

        Date(int month, int day, int year) {
            this.month = month;
            this.day = day;
            this.year = year;
        }

        @Override
        public String toString() {
            return String.format("%02d/%02d/%04d", month, day, year);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter date (MM/DD/YYYY): ");
        String input = scanner.next();
        String[] parts = input.split("/");

        Date date = new Date(
            Integer.parseInt(parts[0]),
            Integer.parseInt(parts[1]),
            Integer.parseInt(parts[2])
        );

        System.out.println("Date entered: " + date);
        scanner.close();
    }
}
