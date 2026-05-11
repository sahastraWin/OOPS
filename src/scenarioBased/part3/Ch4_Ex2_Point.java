package scenarioBased.part3;/*
 * Chapter 4, Exercise 2
 * A point on the two-dimensional plane can be represented by two numbers: an x coordinate
 * and a y coordinate. The sum of two points can be defined as a new point whose x coordinate
 * is the sum of the x coordinates, and whose y coordinate is the sum of the y coordinates.
 * Write a program that uses a structure called point to model a point. Define three points,
 * have the user input values for two of them. Set the third equal to the sum, then display.
 * Example:
 *   Enter coordinates for p1: 3 4
 *   Enter coordinates for p2: 5 7
 *   Coordinates of p1+p2 are: 8, 11
 */

import java.util.Scanner;

public class Ch4_Ex2_Point {

    static class Point {
        double x, y;
        Point(double x, double y) { this.x = x; this.y = y; }
        Point add(Point other) { return new Point(this.x + other.x, this.y + other.y); }
        @Override public String toString() { return x + ", " + y; }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter coordinates for p1: ");
        Point p1 = new Point(scanner.nextDouble(), scanner.nextDouble());
        System.out.print("Enter coordinates for p2: ");
        Point p2 = new Point(scanner.nextDouble(), scanner.nextDouble());

        Point p3 = p1.add(p2);
        System.out.println("Coordinates of p1+p2 are: " + p3);
        scanner.close();
    }
}
