package scenarioBased.part1;/*
/*
 * Question 35 - Chapter 9: Inheritance
 * Create a base class Vehicle with make, model, and year.
 * Derive Car and Truck from it.
 * Car adds number of doors; Truck adds payload capacity.
 * Write a program that creates a fleet of vehicles and displays all info.
 */

public class Q35_VehicleHierarchy {
    static class Vehicle {
        protected String make, model;
        protected int year;

        Vehicle(String make, String model, int year) {
            this.make = make; this.model = model; this.year = year;
        }

        void display() {
            System.out.printf("%d %s %s", year, make, model);
        }
    }

    static class Car extends Vehicle {
        private int doors;

        Car(String make, String model, int year, int doors) {
            super(make, model, year); this.doors = doors;
        }

        @Override void display() {
            System.out.print("[Car] "); super.display();
            System.out.println(" | Doors: " + doors);
        }
    }

    static class Truck extends Vehicle {
        private double payload; // in tonnes

        Truck(String make, String model, int year, double payload) {
            super(make, model, year); this.payload = payload;
        }

        @Override void display() {
            System.out.print("[Truck] "); super.display();
            System.out.printf(" | Payload: %.1f tonnes%n", payload);
        }
    }

    public static void main(String[] args) {
        Vehicle[] fleet = {
            new Car("Toyota", "Corolla", 2022, 4),
            new Car("Honda", "Civic", 2021, 2),
            new Truck("Ford", "F-150", 2023, 1.5),
            new Truck("Volvo", "FH16", 2020, 25.0)
        };
        System.out.println("=== Fleet Information ===");
        for (Vehicle v : fleet) v.display();
    }
}
