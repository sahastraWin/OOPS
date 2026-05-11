package scenarioBased.part1;/*
/*
 * Question 58 - Classic Scenario: EMPLOYEE HIERARCHY
 * A company has managers, scientists, and laborers.
 * Design an Employee base class with name and ID.
 * Manager adds title and number of subordinates.
 * Scientist adds a field for number of publications.
 * Laborer adds no new fields.
 * Create objects of each and display their data.
 */

public class Q58_EmployeeClassic {
    static class Employee {
        protected String name;
        protected int id;

        Employee(String name, int id) { this.name = name; this.id = id; }

        void display() { System.out.printf("Name: %-15s ID: %04d", name, id); }
    }

    static class Manager extends Employee {
        private String title;
        private int subordinates;

        Manager(String name, int id, String title, int subs) {
            super(name, id); this.title = title; this.subordinates = subs;
        }

        @Override void display() {
            System.out.print("[Manager]   "); super.display();
            System.out.println(" | Title: " + title + " | Subordinates: " + subordinates);
        }
    }

    static class Scientist extends Employee {
        private int publications;

        Scientist(String name, int id, int pubs) {
            super(name, id); this.publications = pubs;
        }

        @Override void display() {
            System.out.print("[Scientist] "); super.display();
            System.out.println(" | Publications: " + publications);
        }
    }

    static class Laborer extends Employee {
        Laborer(String name, int id) { super(name, id); }

        @Override void display() {
            System.out.print("[Laborer]   "); super.display(); System.out.println();
        }
    }

    public static void main(String[] args) {
        Employee[] staff = {
            new Manager("Alice",   101, "Director", 10),
            new Scientist("Bob",   102, 15),
            new Laborer("Charlie", 103),
            new Laborer("Dave",    104)
        };
        System.out.println("=== Employee Records ===");
        for (Employee e : staff) e.display();
    }
}
