package scenarioBased.part1;/*
/*
 * Question 18 - Chapter 6: Objects and Classes
 * Create a class called Time with data members for hours, minutes, and seconds.
 * Include member functions to set the time, add two Time objects,
 * and display the time.
 */

import java.util.Scanner;

public class Q18_TimeClass {
    static class Time {
        private int hours, minutes, seconds;

        void setTime(int h, int m, int s) {
            int total = h * 3600 + m * 60 + s;
            hours = total / 3600;
            minutes = (total % 3600) / 60;
            seconds = total % 60;
        }

        Time add(Time other) {
            Time result = new Time();
            result.setTime(this.hours + other.hours,
                           this.minutes + other.minutes,
                           this.seconds + other.seconds);
            return result;
        }

        void display() {
            System.out.printf("%02d:%02d:%02d%n", hours, minutes, seconds);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Time t1 = new Time(), t2 = new Time();

        System.out.print("Enter first time (h m s): ");
        t1.setTime(sc.nextInt(), sc.nextInt(), sc.nextInt());
        System.out.print("Enter second time (h m s): ");
        t2.setTime(sc.nextInt(), sc.nextInt(), sc.nextInt());

        System.out.print("T1: "); t1.display();
        System.out.print("T2: "); t2.display();
        System.out.print("Sum: "); t1.add(t2).display();
        sc.close();
    }
}
