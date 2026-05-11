package scenarioBased.part3;/*
 * Chapter 8, Exercise 3 (Starred):
 * Modify the time class from Exercise 3 in Chapter 6 so that instead of a function
 * add_time() it uses the overloaded + operator to add two times.
 * Write a program to test this class.
 */

import java.util.Scanner;

public class Ch8Ex3_Time {

    static class Time {
        private int hours;
        private int minutes;
        private int seconds;

        public Time() { hours = minutes = seconds = 0; }
        public Time(int h, int m, int s) { hours = h; minutes = m; seconds = s; normalize(); }

        private void normalize() {
            minutes += seconds / 60; seconds %= 60;
            hours += minutes / 60;   minutes %= 60;
        }

        // Overloaded + operator
        public Time add(Time t2) {
            return new Time(this.hours + t2.hours, this.minutes + t2.minutes, this.seconds + t2.seconds);
        }

        public void getTime(Scanner sc) {
            System.out.print("Hours: ");   hours   = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Minutes: "); minutes = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Seconds: "); seconds = Integer.parseInt(sc.nextLine().trim());
            normalize();
        }

        public void putTime() {
            System.out.printf("%02d:%02d:%02d%n", hours, minutes, seconds);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Time Addition with Overloaded + ===");

        System.out.println("Enter time 1:");
        Time t1 = new Time(); t1.getTime(sc);
        System.out.println("Enter time 2:");
        Time t2 = new Time(); t2.getTime(sc);

        Time t3 = t1.add(t2);
        System.out.print("t1 + t2 = "); t3.putTime();
        sc.close();
    }
}
