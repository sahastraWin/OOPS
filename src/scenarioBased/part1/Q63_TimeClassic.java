package scenarioBased.part1;/*
/*
 * Question 63 - Classic Scenario: TIME CLASS
 * Create a class Time with hours, minutes, and seconds.
 * Implement add() to add two Time values and toString() to display.
 * (Java equivalent of operator overloading for + and <<)
 * Write a main() that creates Time objects, adds them, and displays results.
 */

public class Q63_TimeClassic {
    static class Time {
        private int hours, minutes, seconds;

        Time(int h, int m, int s) {
            int total = h * 3600 + m * 60 + s;
            hours   = total / 3600;
            minutes = (total % 3600) / 60;
            seconds = total % 60;
        }

        Time add(Time other) {
            return new Time(hours + other.hours, minutes + other.minutes, seconds + other.seconds);
        }

        @Override
        public String toString() {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }
    }

    public static void main(String[] args) {
        Time t1 = new Time(2, 45, 30);
        Time t2 = new Time(1, 30, 45);
        Time t3 = new Time(0, 50, 55);

        System.out.println("t1 = " + t1);
        System.out.println("t2 = " + t2);
        System.out.println("t3 = " + t3);
        System.out.println("t1 + t2 = " + t1.add(t2));
        System.out.println("t1 + t2 + t3 = " + t1.add(t2).add(t3));
    }
}
