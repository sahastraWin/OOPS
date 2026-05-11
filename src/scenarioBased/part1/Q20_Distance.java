package scenarioBased.part1;/*
/*
 * Question 20 - Chapter 6: Objects and Classes
 * Create a class called Distance that stores an English distance
 * in feet and inches.
 * Write member functions to get a distance from the user,
 * add two distances, and display the result.
 */

import java.util.Scanner;

public class Q20_Distance {
    static class Distance {
        private int feet;
        private double inches;

        void getData() {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter feet: ");   feet   = sc.nextInt();
            System.out.print("Enter inches: "); inches = sc.nextDouble();
        }

        Distance add(Distance other) {
            Distance result = new Distance();
            double totalInches = this.inches + other.inches + (this.feet + other.feet) * 12;
            result.feet = (int) (totalInches / 12);
            result.inches = totalInches % 12;
            return result;
        }

        void display() {
            System.out.printf("%d feet %.2f inches%n", feet, inches);
        }
    }

    public static void main(String[] args) {
        Distance d1 = new Distance(), d2 = new Distance();
        System.out.println("First distance:");  d1.getData();
        System.out.println("Second distance:"); d2.getData();
        System.out.print("Sum: "); d1.add(d2).display();
    }
}
