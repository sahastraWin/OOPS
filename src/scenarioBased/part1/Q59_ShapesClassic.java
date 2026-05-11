package scenarioBased.part1;/*
/*
 * Question 59 - Classic Scenario: SHAPES HIERARCHY
 * Create an abstract base class Shape with pure virtual function area().
 * Derive Circle (radius), Rectangle (length, width), and Triangle (base, height).
 * Store shape references in an array and display each shape's area.
 */

public class Q59_ShapesClassic {
    abstract static class Shape {
        abstract double area();
        abstract String describe();
    }

    static class Circle extends Shape {
        private double radius;
        Circle(double r) { radius = r; }
        @Override double area()      { return Math.PI * radius * radius; }
        @Override String describe()  { return String.format("Circle(r=%.2f)", radius); }
    }

    static class Rectangle extends Shape {
        private double length, width;
        Rectangle(double l, double w) { length = l; width = w; }
        @Override double area()       { return length * width; }
        @Override String describe()   { return String.format("Rectangle(%.2f x %.2f)", length, width); }
    }

    static class Triangle extends Shape {
        private double base, height;
        Triangle(double b, double h) { base = b; height = h; }
        @Override double area()      { return 0.5 * base * height; }
        @Override String describe()  { return String.format("Triangle(b=%.2f, h=%.2f)", base, height); }
    }

    public static void main(String[] args) {
        Shape[] shapes = { new Circle(5), new Rectangle(4, 6), new Triangle(3, 8) };
        System.out.println("=== Shape Areas ===");
        for (Shape s : shapes)
            System.out.printf("%-30s Area = %.4f%n", s.describe(), s.area());
    }
}
