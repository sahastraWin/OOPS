package scenarioBased.part1;/*
/*
 * Question 42 - Chapter 11: Virtual Functions and Other Subtleties
 * Create an abstract class Shape with abstract methods area() and perimeter().
 * Derive Sphere, Cylinder, and Cone.
 * Demonstrate storing them in an array of Shape references.
 */

public class Q42_AbstractShapes {
    abstract static class Shape {
        abstract double area();
        abstract double perimeter(); // surface area for 3D shapes
        abstract String name();
    }

    static class Sphere extends Shape {
        private double radius;
        Sphere(double r) { radius = r; }
        @Override double area()      { return 4 * Math.PI * radius * radius; }
        @Override double perimeter() { return area(); } // surface area
        @Override String name()      { return "Sphere"; }
    }

    static class Cylinder extends Shape {
        private double radius, height;
        Cylinder(double r, double h) { radius = r; height = h; }
        @Override double area()      { return 2 * Math.PI * radius * (radius + height); }
        @Override double perimeter() { return area(); }
        @Override String name()      { return "Cylinder"; }
    }

    static class Cone extends Shape {
        private double radius, slantHeight;
        Cone(double r, double l) { radius = r; slantHeight = l; }
        @Override double area()      { return Math.PI * radius * (radius + slantHeight); }
        @Override double perimeter() { return area(); }
        @Override String name()      { return "Cone"; }
    }

    public static void main(String[] args) {
        Shape[] shapes = { new Sphere(5), new Cylinder(3, 7), new Cone(4, 6) };
        for (Shape s : shapes)
            System.out.printf("%-10s Surface Area = %.4f%n", s.name() + ":", s.area());
    }
}
