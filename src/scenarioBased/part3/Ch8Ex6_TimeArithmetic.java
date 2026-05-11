package scenarioBased.part3;/*
 * Chapter 8, Exercise 6:
 * Add to the time class of Exercise 5 the ability to subtract two time values using the
 * overloaded (-) operator, and to multiply a time value by a number of type float,
 * using the overloaded (*) operator.
 */

public class Ch8Ex6_TimeArithmetic {

    static class Time {
        private int hours;
        private int minutes;
        private int seconds;

        public Time() { hours = minutes = seconds = 0; }
        public Time(int h, int m, int s) { hours = h; minutes = m; seconds = s; normalize(); }
        public Time(Time other) { this.hours = other.hours; this.minutes = other.minutes; this.seconds = other.seconds; }

        private void normalize() {
            if (seconds < 0) { minutes--; seconds += 60; }
            if (seconds >= 60) { minutes += seconds / 60; seconds %= 60; }
            if (minutes < 0) { hours--; minutes += 60; }
            if (minutes >= 60) { hours += minutes / 60; minutes %= 60; }
            if (hours < 0) hours = 0;
        }

        // Overloaded - operator
        public Time subtract(Time t2) {
            int total1 = this.hours * 3600 + this.minutes * 60 + this.seconds;
            int total2 = t2.hours * 3600 + t2.minutes * 60 + t2.seconds;
            int diff = total1 - total2;
            if (diff < 0) { System.out.println("Warning: negative time, using 0."); diff = 0; }
            return new Time(diff / 3600, (diff % 3600) / 60, diff % 60);
        }

        // Overloaded * operator (time * float)
        public Time multiply(float factor) {
            int total = (int)((this.hours * 3600 + this.minutes * 60 + this.seconds) * factor);
            return new Time(total / 3600, (total % 3600) / 60, total % 60);
        }

        // Overloaded + operator
        public Time add(Time t2) {
            return new Time(this.hours + t2.hours, this.minutes + t2.minutes, this.seconds + t2.seconds);
        }

        public void putTime() { System.out.printf("%02d:%02d:%02d%n", hours, minutes, seconds); }
    }

    public static void main(String[] args) {
        Time t1 = new Time(3, 45, 20);
        Time t2 = new Time(1, 30, 10);

        System.out.print("t1 = "); t1.putTime();
        System.out.print("t2 = "); t2.putTime();

        System.out.print("t1 - t2 = "); t1.subtract(t2).putTime();
        System.out.print("t1 * 2.5 = "); t1.multiply(2.5f).putTime();
        System.out.print("t1 + t2 = "); t1.add(t2).putTime();
    }
}
