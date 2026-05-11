package scenarioBased.part1;/*
/*
 * Question 54 - Classic Scenario: SERIAL NUMBER
 * Create a class that includes a data member holding a serial number for each object.
 * The first object created is numbered 1, the second 2, and so on.
 * Add a member function that permits an object to report its own serial number.
 * Write a main() that creates three objects and queries each about its serial number.
 * Output: "I am object 1", "I am object 2", ...
 */

public class Q54_SerialNumber {
    static class SerialObject {
        private static int nextSerial = 1;
        private final int serial;

        SerialObject() { serial = nextSerial++; }

        void reportSerial() { System.out.println("I am object " + serial); }
    }

    public static void main(String[] args) {
        SerialObject obj1 = new SerialObject();
        SerialObject obj2 = new SerialObject();
        SerialObject obj3 = new SerialObject();

        obj1.reportSerial();
        obj2.reportSerial();
        obj3.reportSerial();
    }
}
