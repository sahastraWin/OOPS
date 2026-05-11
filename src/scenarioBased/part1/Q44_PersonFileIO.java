package scenarioBased.part1;/*
/*
 * Question 44 - Chapter 12: Streams and Files
 * Create a class called Person with name and age.
 * Write methods that save a Person object to a file and read it back.
 * In main(), create several Person objects, save them, then read and display.
 */

import java.io.*;
import java.util.*;

public class Q44_PersonFileIO {
    static class Person {
        String name;
        int age;

        Person(String name, int age) { this.name = name; this.age = age; }

        void save(PrintWriter pw) { pw.println(name + "," + age); }

        static Person load(String line) {
            String[] parts = line.split(",");
            return new Person(parts[0], Integer.parseInt(parts[1].trim()));
        }

        @Override public String toString() { return name + " (Age: " + age + ")"; }
    }

    public static void main(String[] args) throws IOException {
        String filename = "persons.txt";
        List<Person> people = Arrays.asList(
            new Person("Alice", 30), new Person("Bob", 25), new Person("Charlie", 35)
        );

        // Write to file
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Person p : people) p.save(pw);
        }
        System.out.println("Saved to " + filename);

        // Read from file
        List<Person> loaded = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) loaded.add(Person.load(line));
        }

        System.out.println("Loaded persons:");
        for (Person p : loaded) System.out.println("  " + p);
    }
}
