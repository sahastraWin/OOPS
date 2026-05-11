package scenarioBased.part3;/*
 * Chapter 6, Exercise 3
 * Create a class called time that has separate int member data for hours, minutes, and seconds.
 * One constructor should initialize this data to 0, and another should initialize it to fixed values.
 * Another member function should display it in 11:59:59 format.
 * The final member function should add two objects of type time passed as arguments.
 * A main() program should create two initialized time objects (const?) and one that isn't initialized.
 * It should add the two initialized values together, leaving the result in the third variable.
 * Finally it should display the value of this third variable.
 */

public class Ch6_Ex3_TimeClass {

    static class Time {
        private int hours, minutes, seconds;

        Time()                              { hours = 0; minutes = 0; seconds = 0; }
        Time(int h, int m, int s)           { hours = h; minutes = m; seconds = s; }

        void display() {
            System.out.printf("%02d:%02d:%02d%n", hours, minutes, seconds);
        }

        Time add(Time other) {
            long totalSecs = toSeconds() + other.toSeconds();
            return fromSeconds(totalSecs);
        }

        private long toSeconds() { return (long) hours * 3600 + minutes * 60 + seconds; }

        private static Time fromSeconds(long totalSecs) {
            int h = (int) (totalSecs / 3600);
            int m = (int) ((totalSecs % 3600) / 60);
            int s = (int) (totalSecs % 60);
            return new Time(h, m, s);
        }
    }

    public static void main(String[] args) {
        final Time t1 = new Time(2, 45, 30);
        final Time t2 = new Time(1, 20, 50);
        Time t3 = new Time();

        t3 = t1.add(t2);
        System.out.print("Sum of times: ");
        t3.display();
    }
}
