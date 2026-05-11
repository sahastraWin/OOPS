package scenarioBased.part1;/*
/*
 * Question 37 - Chapter 10: Pointers
 * Create a class called Person with name and age.
 * Dynamically allocate an array of Person objects (using ArrayList in Java),
 * fill them with user data, and then display all.
 * (Java uses references/garbage-collection; ArrayList replaces dynamic arrays.)
 */

import java.util.*;

public class Q37_DynamicPersonArray {
    static class Person {
        String name;
        int age;

        Person(String name, int age) { this.name = name; this.age = age; }

        void display() { System.out.printf("Name: %-15s Age: %d%n", name, age); }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many persons to enter? ");
        int n = sc.nextInt();

        List<Person> people = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            System.out.println("Person " + (i + 1) + ":");
            System.out.print("  Name: "); String name = sc.next();
            System.out.print("  Age: ");  int age = sc.nextInt();
            people.add(new Person(name, age));
        }

        System.out.println("\n--- All Persons ---");
        for (Person p : people) p.display();
        sc.close();
    }
}
