package scenarioBased.part1;/*
/*
 * Question 10 - Chapter 4: Structures
 * Create a structure to represent a Date (day, month, year).
 * Write a program that lets the user input a date and then displays it
 * in multiple formats (DD/MM/YYYY and Month DD, YYYY).
 */

import java.util.Scanner;

public class Q10_DateFormats {
    static final String[] MONTHS = {
        "", "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };

    static class Date {
        int day, month, year;

        Date(int d, int m, int y) { day = d; month = m; year = y; }

        String formatDMY() { return String.format("%02d/%02d/%04d", day, month, year); }

        String formatLong() { return MONTHS[month] + " " + day + ", " + year; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter day month year (e.g. 15 8 2024): ");
        Date d = new Date(sc.nextInt(), sc.nextInt(), sc.nextInt());
        System.out.println("DD/MM/YYYY: " + d.formatDMY());
        System.out.println("Long format: " + d.formatLong());
        sc.close();
    }
}
