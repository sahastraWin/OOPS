package scenarioBased.part3;/*
 * Chapter 6, Exercise 8
 * Create a class that includes a data member that holds a "serial number" for each object
 * created from the class. That is, the first object created will be numbered 1, the second 2, etc.
 * You'll need another data member that records a count of how many objects have been created so far.
 * (This member should apply to the class as a whole, not to individual objects — use static.)
 * As each object is created, its constructor can examine this count to determine the serial number.
 * Add a member function that permits an object to report its own serial number.
 * Write a main() program that creates three objects and queries each one about its serial number.
 * They should respond "I am object number 2", and so on.
 */

public class Ch6_Ex8_SerialNumber {

    static class SerialObject {
        private static int objectCount = 0;
        private final int serialNumber;

        SerialObject() {
            objectCount++;
            this.serialNumber = objectCount;
        }

        void reportSerial() {
            System.out.println("I am object number " + serialNumber);
        }
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
