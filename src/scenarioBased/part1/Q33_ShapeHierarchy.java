package scenarioBased.part1;/*
/*
 * Question 33 - Chapter 9: Inheritance
 * Create a base class Shape with a virtual function area().
 * Derive Triangle, Rectangle, and Circle classes from Shape,
 * each implementing area().
 * Write a main() that creates objects of each and displays their areas.
 */

public class Q33_ShapeHierarchy {
    abstract static class Shape {
        abstract double area();
        abstract String name();
    }

    static class Triangle extends Shape {
        private double base, height;
        Triangle(double b, double h) { base = b; height = h; }
        @Override double area() { return 0.5 * base * height; }
        @Override String name() { return "Triangle"; }
    }

    static class Rectangle extends Shape {
        private double length, width;
        Rectangle(double l, double w) { length = l; width = w; }
        @Override double area() { return length * width; }
        @Override String name() { return "Rectangle"; }
    }

    static class Circle extends Shape {
        private double radius;
        Circle(double r) { radius = r; }
        @Override double area() { return Math.PI * radius * radius; }
        @Override String name() { return "Circle"; }
    }

    public static void main(String[] args) {
        Shape[] shapes = {
            new Triangle(6, 4),
            new Rectangle(5, 3),
            new Circle(7)
        };
        for (Shape s : shapes)
            System.out.printf("%-12s area = %.4f%n", s.name() + ":", s.area());
    }
}
