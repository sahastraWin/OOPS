package scenarioBased.part1;/*
/*
 * Question 23 - Chapter 7: Arrays and Strings
 * Create a class called Person with name and age as data members.
 * Make an array of Person objects, fill them with user input,
 * then sort and display them by age.
 */

import java.util.*;

public class Q23_PersonArray {
    static class Person {
        String name;
        int age;
        Person(String name, int age) { this.name = name; this.age = age; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many persons? ");
        int n = sc.nextInt();
        Person[] persons = new Person[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Name: "); String name = sc.next();
            System.out.print("Age: ");  int age = sc.nextInt();
            persons[i] = new Person(name, age);
        }
        Arrays.sort(persons, Comparator.comparingInt(p -> p.age));
        System.out.println("\nSorted by age:");
        for (Person p : persons)
            System.out.printf("%-15s %d%n", p.name, p.age);
        sc.close();
    }
}
