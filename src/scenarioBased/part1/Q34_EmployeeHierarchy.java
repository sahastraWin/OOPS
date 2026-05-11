package scenarioBased.part1;/*
/*
 * Question 34 - Chapter 9: Inheritance
 * Design a base class Employee with name and ID.
 * Derive Manager and Scientist from Employee.
 * Manager adds a title and number of subordinates.
 * Scientist adds a list of publications.
 * Write a main() demonstrating polymorphic behavior.
 */

import java.util.Arrays;

public class Q34_EmployeeHierarchy {
    abstract static class Employee {
        protected String name;
        protected int id;

        Employee(String name, int id) { this.name = name; this.id = id; }

        abstract void display();
    }

    static class Manager extends Employee {
        private String title;
        private int subordinates;

        Manager(String name, int id, String title, int subordinates) {
            super(name, id); this.title = title; this.subordinates = subordinates;
        }

        @Override void display() {
            System.out.printf("[Manager] %s (ID:%d) | Title: %s | Subordinates: %d%n",
                              name, id, title, subordinates);
        }
    }

    static class Scientist extends Employee {
        private String[] publications;

        Scientist(String name, int id, String... pubs) {
            super(name, id); publications = pubs;
        }

        @Override void display() {
            System.out.printf("[Scientist] %s (ID:%d) | Publications: %s%n",
                              name, id, Arrays.toString(publications));
        }
    }

    public static void main(String[] args) {
        Employee[] staff = {
            new Manager("Alice", 101, "VP Engineering", 12),
            new Scientist("Bob", 102, "AI Research", "Deep Learning Survey", "NLP Review"),
            new Manager("Carol", 103, "Director Marketing", 6)
        };
        for (Employee e : staff) e.display();
    }
}
