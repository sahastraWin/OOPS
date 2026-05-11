package scenarioBased.part3;/*
 * Chapter 6, Exercise 1
 * Create a class that imitates part of the functionality of the basic data type int.
 * Call the class Int (note different capitalization). The only data in this class is an int variable.
 * Include member functions to:
 *   - initialize an Int to 0
 *   - initialize it to an int value
 *   - display it (it looks just like an int)
 *   - add two Int values
 * Write a program that exercises this class by creating one uninitialized and two initialized
 * Int values, adding the two initialized values and placing the response in the uninitialized
 * value, and then displaying this result.
 */

public class Ch6_Ex1_IntClass {

    static class Int {
        private int value;

        Int()          { this.value = 0; }
        Int(int value) { this.value = value; }

        Int add(Int other) { return new Int(this.value + other.value); }
        void display()     { System.out.println(value); }
        int getValue()     { return value; }
    }

    public static void main(String[] args) {
        Int uninit = new Int();          // uninitialized (defaults to 0)
        Int a = new Int(10);
        Int b = new Int(25);

        uninit = a.add(b);
        System.out.print("Sum of " + a.getValue() + " and " + b.getValue() + " = ");
        uninit.display();
    }
}
