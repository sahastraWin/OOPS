/**
 * ============================================================
 *  TOPIC: Introduction to OOP in Java
 * ============================================================
 *
 * Java is a PURELY Object-Oriented language (almost everything is an object).
 * Primitives (int, double, etc.) are the only exception.
 *
 * The 4 PILLARS of OOP:
 *   1. Encapsulation  — Binding data + methods; data hiding via access modifiers
 *   2. Abstraction    — Hiding implementation details; showing only essentials
 *   3. Inheritance    — Child class acquires properties of parent class
 *   4. Polymorphism   — One interface, many implementations
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW:
 *  - Java is platform-independent: "Write Once, Run Anywhere" (WORA)
 *    via the JVM (Java Virtual Machine).
 *  - Java does NOT support multiple inheritance through classes
 *    (to avoid Diamond Problem) — uses interfaces instead.
 *  - Everything in Java inherits from java.lang.Object implicitly.
 *  - Java is pass-by-value (the value of reference is copied).
 *  - Java has automatic memory management (Garbage Collector).
 *
 *  Java vs C++ OOP differences:
 *  ┌────────────────────┬────────────────────────────────────┐
 *  │ Feature            │ Java          │ C++                │
 *  ├────────────────────┼───────────────┼────────────────────┤
 *  │ Multiple inherit.  │ No (classes)  │ Yes                │
 *  │ Pointers           │ No (refs only)│ Yes                │
 *  │ Destructors        │ No (GC)       │ Yes (~)            │
 *  │ Operator overload  │ No            │ Yes                │
 *  │ Templates          │ Generics      │ Templates          │
 *  │ Memory management  │ Automatic(GC) │ Manual             │
 *  └────────────────────┴───────────────┴────────────────────┘
 * ╚══════════════════════════════════════════════════════════╝
 */
public class Introduction {

    // ── Demonstrating basic OOP concepts ──────────────────────────────────

    /**
     * A simple class demonstrating the 4 pillars.
     *
     * ENCAPSULATION: private fields + public getters/setters
     * ABSTRACTION:   user uses start() without knowing engine internals
     * INHERITANCE:   demonstrated via ElectricCar subclass
     * POLYMORPHISM:  fuel() method behaves differently per class
     */
    static class Car {
        // ENCAPSULATION: fields are private
        private String brand;
        private int    year;
        private double speed;

        // Constructor
        public Car(String brand, int year) {
            this.brand = brand;
            this.year  = year;
            this.speed = 0.0;
        }

        // ABSTRACTION: hides HOW the car starts internally
        public void start() {
            System.out.println(brand + " started.");
        }

        public void accelerate(double delta) {
            speed += delta;
            System.out.printf("%s speed: %.1f km/h%n", brand, speed);
        }

        // POLYMORPHISM: overridden in subclasses
        public String fuelType() {
            return "Petrol";
        }

        public void info() {
            System.out.println("Car: " + brand + " (" + year + ") Fuel: " + fuelType());
        }

        // Getters (Encapsulation)
        public String getBrand() { return brand; }
        public int    getYear()  { return year;  }
        public double getSpeed() { return speed; }
    }

    // INHERITANCE: ElectricCar IS-A Car
    static class ElectricCar extends Car {
        private int batteryKWh;

        public ElectricCar(String brand, int year, int battery) {
            super(brand, year);   // call parent constructor
            this.batteryKWh = battery;
        }

        /*
         * ╔══════════════════════════════════════════════╗
         *  POLYMORPHISM (Runtime): @Override changes the
         *  behaviour of fuelType() for ElectricCar objects.
         *  @Override annotation tells compiler to verify this.
         * ╚══════════════════════════════════════════════╝
         */
        @Override
        public String fuelType() {
            return "Electric (" + batteryKWh + " kWh)";
        }

        @Override
        public void start() {
            System.out.println(getBrand() + " silently powered on.");
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Introduction to OOP in Java =====\n");

        // Creating objects (instances of a class)
        Car petrol  = new Car("Toyota Camry", 2022);
        Car electric = new ElectricCar("Tesla Model 3", 2023, 75);

        petrol.start();
        electric.start();

        petrol.accelerate(60);
        electric.accelerate(100);

        /*
         * ╔══════════════════════════════════════════════╗
         *  RUNTIME POLYMORPHISM:
         *  Both references are of type 'Car', but at runtime
         *  the JVM calls the correct version of fuelType().
         *  This is achieved through dynamic dispatch.
         * ╚══════════════════════════════════════════════╝
         */
        Car[] fleet = { petrol, electric };
        System.out.println("\n--- Fleet Info (Polymorphism) ---");
        for (Car c : fleet) {
            c.info();  // calls overridden version at runtime
        }

        // All classes inherit from java.lang.Object
        System.out.println("\n--- Object class methods ---");
        System.out.println("petrol.getClass() : " + petrol.getClass().getSimpleName());
        System.out.println("petrol.hashCode() : " + petrol.hashCode());
        System.out.println("petrol.toString() : " + petrol);  // calls Object.toString()

        System.out.println("\n--- JVM Info ---");
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("Max memory  : " + Runtime.getRuntime().maxMemory() / (1024*1024) + " MB");
    }
}
