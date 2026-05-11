package scenarioBased.part3;/*
 * Chapter 8, Exercise 5:
 * Augment the time class referred to in Exercise 3 to include overloaded increment (++)
 * and decrement (--) operators that operate in both prefix and postfix notation and return
 * values. Add statements to main() to test these operators.
 * (Increment/decrement adds/subtracts one second from the time value.)
 */

public class Ch8Ex5_TimeIncrDecr {

    static class Time {
        private int hours;
        private int minutes;
        private int seconds;

        public Time() { hours = minutes = seconds = 0; }
        public Time(int h, int m, int s) { hours = h; minutes = m; seconds = s; normalize(); }

        // Copy constructor
        public Time(Time other) { this.hours = other.hours; this.minutes = other.minutes; this.seconds = other.seconds; }

        private void normalize() {
            if (seconds >= 60) { minutes += seconds / 60; seconds %= 60; }
            if (seconds < 0)   { minutes -= 1; seconds += 60; }
            if (minutes >= 60) { hours += minutes / 60; minutes %= 60; }
            if (minutes < 0)   { hours -= 1; minutes += 60; }
        }

        // Prefix increment: ++t (increment then return)
        public Time prefixIncrement() {
            seconds++;
            normalize();
            return this;
        }

        // Postfix increment: t++ (return then increment)
        public Time postfixIncrement() {
            Time temp = new Time(this);
            seconds++;
            normalize();
            return temp;
        }

        // Prefix decrement
        public Time prefixDecrement() {
            seconds--;
            normalize();
            return this;
        }

        // Postfix decrement
        public Time postfixDecrement() {
            Time temp = new Time(this);
            seconds--;
            normalize();
            return temp;
        }

        public void putTime() { System.out.printf("%02d:%02d:%02d%n", hours, minutes, seconds); }
    }

    public static void main(String[] args) {
        Time t = new Time(1, 59, 59);
        System.out.print("Initial: "); t.putTime();

        Time t2 = t.prefixIncrement();
        System.out.print("After prefix ++: t = "); t.putTime();
        System.out.print("Returned value:  "); t2.putTime();

        Time t3 = t.postfixIncrement();
        System.out.print("After postfix ++: t = "); t.putTime();
        System.out.print("Returned value (old): "); t3.putTime();

        Time t4 = t.prefixDecrement();
        System.out.print("After prefix --: t = "); t.putTime();
        System.out.print("Returned value:  "); t4.putTime();

        Time t5 = t.postfixDecrement();
        System.out.print("After postfix --: t = "); t.putTime();
        System.out.print("Returned value (old): "); t5.putTime();
    }
}
