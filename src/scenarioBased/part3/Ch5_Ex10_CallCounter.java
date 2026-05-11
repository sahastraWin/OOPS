package scenarioBased.part3;/*
 * Chapter 5, Exercise 10
 * Write a function that, when you call it, displays a message telling how many times it
 * has been called: "I have been called 3 times", for instance.
 * Write a main() program that calls this function at least 10 times.
 * Try implementing this using a static variable (analogous to C++ static local variable).
 * In Java, a static field on the class serves the same purpose.
 */

public class Ch5_Ex10_CallCounter {

    private static int callCount = 0;

    static void reportCalls() {
        callCount++;
        System.out.println("I have been called " + callCount + " time" + (callCount == 1 ? "" : "s"));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 12; i++) {
            reportCalls();
        }
    }
}
