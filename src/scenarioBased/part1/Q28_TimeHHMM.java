package scenarioBased.part1;/*
/*
 * Question 28 - Chapter 8: Operator Overloading
 * Create a class called Time that stores hours and minutes.
 * Implement add() to add two time values and a display() method
 * to show a time in HH:MM format.
 * (Java does not support operator overloading; methods are used instead.)
 */

import java.util.Scanner;

public class Q28_TimeHHMM {
    static class Time {
        private int hours, minutes;

        Time(int h, int m) {
            int total = h * 60 + m;
            hours = total / 60;
            minutes = total % 60;
        }

        Time add(Time other) {
            return new Time(this.hours + other.hours, this.minutes + other.minutes);
        }

        void display() { System.out.printf("%02d:%02d%n", hours, minutes); }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first time (hours minutes): ");
        Time t1 = new Time(sc.nextInt(), sc.nextInt());
        System.out.print("Enter second time (hours minutes): ");
        Time t2 = new Time(sc.nextInt(), sc.nextInt());
        System.out.print("T1: "); t1.display();
        System.out.print("T2: "); t2.display();
        System.out.print("Sum: "); t1.add(t2).display();
        sc.close();
    }
}
