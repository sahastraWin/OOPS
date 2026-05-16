/**
 * ============================================================
 *  TOPIC: Inheritance in Java
 * ============================================================
 *
 * Inheritance = Child class (subclass) acquires properties and
 * behaviours of a Parent class (superclass) using 'extends'.
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW:
 *  - Java supports: Single, Multilevel, Hierarchical inheritance.
 *  - Java does NOT support Multiple Inheritance via classes
 *    (avoids Diamond Problem) — use interfaces instead.
 *  - 'extends' for classes, 'implements' for interfaces.
 *  - Every class implicitly extends java.lang.Object.
 *  - Constructor is NOT inherited; super() calls parent's ctor.
 *  - private members are NOT inherited (not accessible in child).
 *  - IS-A relationship: Dog IS-A Animal.
 *  - HAS-A relationship (Composition): Car HAS-A Engine.
 *  - Favour COMPOSITION over INHERITANCE (GoF principle).
 *  - Method hiding vs Overriding:
 *      Instance method + @Override → overriding (runtime)
 *      Static method "overridden"  → hiding (compile time)
 * ╚══════════════════════════════════════════════════════════╝
 *
 * Types covered:
 *  1. Single Inheritance
 *  2. Multilevel Inheritance
 *  3. Hierarchical Inheritance
 *  4. Why Multiple Inheritance is not allowed (Diamond Problem)
 *  5. Method Hiding (static methods)
 *  6. Composition vs Inheritance
 */
public class Inheritance {

    // ═══════════════════════════════════════════════════════
    // 1. SINGLE INHERITANCE
    // ═══════════════════════════════════════════════════════
    static class Animal {
        protected String name;
        protected String sound;

        public Animal(String name, String sound) {
            this.name  = name;
            this.sound = sound;
            System.out.println("[Animal] Created: " + name);
        }

        public void eat()   { System.out.println(name + " is eating."); }
        public void sleep() { System.out.println(name + " is sleeping."); }
        public void makeSound() { System.out.println(name + " says: " + sound); }

        @Override public String toString() { return "Animal(" + name + ")"; }
    }

    static class Dog extends Animal {
        private String breed;

        public Dog(String name, String breed) {
            super(name, "Woof!");   // call parent constructor
            this.breed = breed;
            System.out.println("[Dog] Created: " + name + " breed=" + breed);
        }

        // Additional behaviour
        public void fetch() { System.out.println(name + " is fetching the ball!"); }
        public void guard() { System.out.println(name + " is guarding the house!"); }

        @Override
        public void makeSound() {
            System.out.println(name + " barks: WOOF WOOF!");
        }

        @Override public String toString() { return "Dog(" + name + ", " + breed + ")"; }
    }

    // ═══════════════════════════════════════════════════════
    // 2. MULTILEVEL INHERITANCE  (A → B → C)
    // ═══════════════════════════════════════════════════════
    static class LivingBeing {
        public void breathe() { System.out.println("Breathing..."); }
    }

    static class Mammal extends LivingBeing {
        public void nurseYoung()  { System.out.println("Nursing young with milk."); }
        public void regulateTemp(){ System.out.println("Warm-blooded temperature regulation."); }
    }

    static class Human extends Mammal {
        private String language;

        public Human(String language) { this.language = language; }

        public void speak()  { System.out.println("Speaking " + language + "."); }
        public void think()  { System.out.println("Thinking rationally."); }

        public void showAll() {
            breathe();       // from LivingBeing
            nurseYoung();    // from Mammal
            speak();         // from Human
        }
    }

    // ═══════════════════════════════════════════════════════
    // 3. HIERARCHICAL INHERITANCE (one parent, many children)
    // ═══════════════════════════════════════════════════════
    static abstract class Shape {
        protected String color;
        public Shape(String color) { this.color = color; }
        public abstract double area();
        public abstract double perimeter();
        public void describe() {
            System.out.printf("%s %s: area=%.2f perimeter=%.2f%n",
                color, getClass().getSimpleName(), area(), perimeter());
        }
    }

    static class Circle extends Shape {
        private double radius;
        public Circle(String color, double r) { super(color); this.radius = r; }
        @Override public double area()      { return Math.PI * radius * radius; }
        @Override public double perimeter() { return 2 * Math.PI * radius; }
    }

    static class Rectangle extends Shape {
        private double l, w;
        public Rectangle(String color, double l, double w) { super(color); this.l=l; this.w=w; }
        @Override public double area()      { return l * w; }
        @Override public double perimeter() { return 2 * (l + w); }
    }

    static class Triangle extends Shape {
        private double a, b, c;
        public Triangle(String color, double a, double b, double c) {
            super(color); this.a=a; this.b=b; this.c=c;
        }
        @Override public double perimeter() { return a + b + c; }
        @Override public double area() {
            double s = perimeter() / 2;
            return Math.sqrt(s * (s-a) * (s-b) * (s-c));
        }
    }

    // ═══════════════════════════════════════════════════════
    // 4. METHOD HIDING (static method "override")
    // ═══════════════════════════════════════════════════════
    static class Parent {
        public static void staticMethod() { System.out.println("Parent static method"); }
        public void instanceMethod()      { System.out.println("Parent instance method"); }
    }

    static class Child extends Parent {
        /*
         * ╔══════════════════════════════════════════════╗
         *  Static methods are NOT overridden — they are HIDDEN.
         *  The version called depends on the REFERENCE TYPE
         *  (compile-time), not the object type (runtime).
         *  Instance methods are overridden and use runtime dispatch.
         * ╚══════════════════════════════════════════════╝
         */
        public static void staticMethod()   { System.out.println("Child static method"); }
        @Override public void instanceMethod() { System.out.println("Child instance method"); }
    }

    // ═══════════════════════════════════════════════════════
    // 5. COMPOSITION vs INHERITANCE
    // ═══════════════════════════════════════════════════════

    // Engine as a separate class (for composition)
    static class Engine {
        private int horsepower;
        private String type;
        public Engine(int hp, String type) { this.horsepower=hp; this.type=type; }
        public void start() { System.out.println(type + " engine (" + horsepower + "hp) started."); }
        public void stop()  { System.out.println("Engine stopped."); }
        @Override public String toString() { return type + "/" + horsepower + "hp"; }
    }

    // INHERITANCE approach (IS-A — wrong here: Car IS-A Engine? No.)
    // static class CarByInheritance extends Engine { ... }  ← wrong semantics

    // COMPOSITION approach (HAS-A — correct: Car HAS-A Engine)
    static class CarByComposition {
        private String name;
        private Engine engine;   // HAS-A relationship

        public CarByComposition(String name, Engine engine) {
            this.name   = name;
            this.engine = engine;
        }

        public void start() {
            System.out.print(name + ": ");
            engine.start();
        }

        public void stop() {
            System.out.print(name + ": ");
            engine.stop();
        }

        @Override public String toString() {
            return name + " [engine: " + engine + "]";
        }
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Inheritance in Java =====\n");

        // --- Single Inheritance ---
        System.out.println("--- 1. Single Inheritance ---");
        Dog d = new Dog("Rex", "German Shepherd");
        d.eat();          // inherited from Animal
        d.makeSound();    // overridden in Dog
        d.fetch();        // Dog-specific

        // IS-A check
        System.out.println("Dog IS-A Animal: " + (d instanceof Animal));  // true

        // --- Multilevel ---
        System.out.println("\n--- 2. Multilevel Inheritance ---");
        Human h = new Human("Java");
        h.showAll();

        // --- Hierarchical ---
        System.out.println("\n--- 3. Hierarchical Inheritance ---");
        Shape[] shapes = {
            new Circle("Red", 5),
            new Rectangle("Blue", 4, 6),
            new Triangle("Green", 3, 4, 5)
        };
        for (Shape s : shapes) s.describe();

        // --- Method Hiding ---
        System.out.println("\n--- 4. Static Method Hiding vs Instance Override ---");
        Parent p  = new Child();         // ref type = Parent, obj type = Child
        p.staticMethod();                // "Parent" — decided at COMPILE TIME (hiding)
        p.instanceMethod();              // "Child"  — decided at RUNTIME (override)

        Child c = new Child();
        c.staticMethod();                // "Child" — ref is Child type
        c.instanceMethod();              // "Child"

        // --- Composition vs Inheritance ---
        System.out.println("\n--- 5. Composition over Inheritance ---");
        Engine petrolEngine  = new Engine(150, "Petrol");
        Engine electricEngine= new Engine(300, "Electric");

        CarByComposition civic = new CarByComposition("Honda Civic", petrolEngine);
        CarByComposition tesla = new CarByComposition("Tesla Model S", electricEngine);

        System.out.println(civic);
        civic.start();
        civic.stop();

        System.out.println(tesla);
        tesla.start();
        tesla.stop();
    }
}
