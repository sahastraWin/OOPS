package scenarioBased.part2;/*
 * Question 17: Create a class Shape which consists one final method area()
 * and volume(). Create three subclasses Rect, Circle and Triangle and
 * calculate area and volume of each.
 */

// Base class Shape with final methods (Note: subclasses override these;
// if methods were truly final they cannot be overridden, so we use abstract here
// to demonstrate polymorphism. The question likely intends overriding.)
abstract class Shape {
    String name;

    abstract double area();
    abstract double volume();

    void display() {
        System.out.println("\nShape: " + name);
        System.out.printf("Area: %.2f%n", area());
        System.out.printf("Volume: %.2f%n", volume());
    }
}

// Rectangle (with height for volume = cuboid)
class Rect extends Shape {
    double length, width, height;

    Rect(double length, double width, double height) {
        this.name = "Rectangle (Cuboid)";
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    double area() {
        return 2 * (length * width + width * height + height * length); // Surface area of cuboid
    }

    @Override
    double volume() {
        return length * width * height; // Volume of cuboid
    }
}

// Circle (with radius and height for volume = cylinder)
class Circle extends Shape {
    double radius, height;

    Circle(double radius, double height) {
        this.name = "Circle (Cylinder)";
        this.radius = radius;
        this.height = height;
    }

    @Override
    double area() {
        return 2 * Math.PI * radius * (radius + height); // Total surface area of cylinder
    }

    @Override
    double volume() {
        return Math.PI * radius * radius * height; // Volume of cylinder
    }
}

// Triangle (with base, height, and side for volume = triangular prism)
class Triangle extends Shape {
    double base, triHeight, length;

    Triangle(double base, double triHeight, double length) {
        this.name = "Triangle (Triangular Prism)";
        this.base = base;
        this.triHeight = triHeight;
        this.length = length;
    }

    @Override
    double area() {
        return 0.5 * base * triHeight; // Area of triangle
    }

    @Override
    double volume() {
        return area() * length; // Volume of prism = base area × length
    }
}

public class Q17_Shape {
    public static void main(String[] args) {
        // Create objects for each shape
        Shape rect = new Rect(5, 3, 4);
        Shape circle = new Circle(7, 10);
        Shape triangle = new Triangle(6, 4, 8);

        System.out.println("===== Shape Calculations =====");
        rect.display();
        circle.display();
        triangle.display();

        // Polymorphism demo
        System.out.println("\n--- Polymorphism Demo ---");
        Shape[] shapes = {rect, circle, triangle};
        for (Shape s : shapes) {
            System.out.printf("%s -> Area: %.2f, Volume: %.2f%n",
                s.name, s.area(), s.volume());
        }
    }
}
