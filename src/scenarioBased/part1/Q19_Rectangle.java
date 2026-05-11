package scenarioBased.part1;/*
/*
 * Question 19 - Chapter 6: Objects and Classes
 * Design a class called Rectangle with data members for length and width.
 * Include member functions to get these values from the user,
 * calculate the perimeter, and display the rectangle's info.
 */

import java.util.Scanner;

public class Q19_Rectangle {
    static class Rectangle {
        private double length, width;

        void getData() {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter length: "); length = sc.nextDouble();
            System.out.print("Enter width: ");  width  = sc.nextDouble();
        }

        double area()      { return length * width; }
        double perimeter() { return 2 * (length + width); }

        void display() {
            System.out.printf("Length: %.2f, Width: %.2f%n", length, width);
            System.out.printf("Area: %.2f, Perimeter: %.2f%n", area(), perimeter());
        }
    }

    public static void main(String[] args) {
        Rectangle r = new Rectangle();
        r.getData();
        r.display();
    }
}
