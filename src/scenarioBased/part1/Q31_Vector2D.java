package scenarioBased.part1;/*
/*
 * Question 31 - Chapter 8: Operator Overloading
 * Design a class called Vector2D with x and y components.
 * Implement add() and subtract() for vector arithmetic,
 * and dot() for the dot product.
 */

public class Q31_Vector2D {
    static class Vector2D {
        private double x, y;

        Vector2D(double x, double y) { this.x = x; this.y = y; }

        Vector2D add(Vector2D other)      { return new Vector2D(x + other.x, y + other.y); }
        Vector2D subtract(Vector2D other) { return new Vector2D(x - other.x, y - other.y); }
        double dot(Vector2D other)        { return x * other.x + y * other.y; }
        double magnitude()               { return Math.sqrt(x * x + y * y); }

        @Override
        public String toString() { return String.format("(%.2f, %.2f)", x, y); }
    }

    public static void main(String[] args) {
        Vector2D v1 = new Vector2D(3, 4);
        Vector2D v2 = new Vector2D(1, 2);
        System.out.println("v1 = " + v1 + " | magnitude = " + String.format("%.2f", v1.magnitude()));
        System.out.println("v2 = " + v2 + " | magnitude = " + String.format("%.2f", v2.magnitude()));
        System.out.println("v1 + v2 = " + v1.add(v2));
        System.out.println("v1 - v2 = " + v1.subtract(v2));
        System.out.printf("v1 · v2 = %.2f%n", v1.dot(v2));
    }
}
