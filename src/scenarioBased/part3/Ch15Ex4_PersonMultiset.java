package scenarioBased.part3;/*
 * Chapter 15 (STL/Collections), Exercise 4 (Starred):
 * Start with the person class, and create a multiset to hold pointers to person objects.
 * Define the multiset with the comparePersons function object, so it will be sorted
 * automatically by names of persons. Define a half-dozen persons, put them in the multiset,
 * and display its contents. Several of the persons should have the same name, to verify
 * that the multiset stores multiple objects with the same key.
 * (In Java, we use TreeMap with a list per key, or a TreeMultiset-like structure using TreeMap<String, List<Person>>.)
 */

import java.util.*;

public class Ch15Ex4_PersonMultiset {

    static class Person {
        private String name;
        private int    age;

        public Person(String n, int a) { name = n; age = a; }
        public String getName() { return name; }
        public int    getAge()  { return age; }

        @Override
        public String toString() {
            return String.format("Person{name='%s', age=%d}", name, age);
        }
    }

    // Comparator equivalent to comparePersons function object — sorts by name
    static Comparator<Person> comparePersons = (p1, p2) -> {
        int cmp = p1.getName().compareTo(p2.getName());
        if (cmp != 0) return cmp;
        return Integer.compare(p1.getAge(), p2.getAge()); // tie-break by age
    };

    public static void main(String[] args) {
        System.out.println("=== Multiset of Person Objects (sorted by name) ===\n");

        // TreeSet with custom comparator simulates C++ multiset<Person*, comparePersons>
        // We use a list with manual sorted insertion to allow duplicates (like multiset)
        List<Person> multiset = new ArrayList<>();

        // Add half-dozen persons (some with same name)
        multiset.add(new Person("Alice", 30));
        multiset.add(new Person("Bob",   25));
        multiset.add(new Person("Alice", 28)); // duplicate name
        multiset.add(new Person("Charlie", 35));
        multiset.add(new Person("Bob",   32)); // duplicate name
        multiset.add(new Person("Diana", 22));

        // Sort using comparePersons (simulates multiset auto-sort)
        multiset.sort(comparePersons);

        System.out.println("Multiset contents (sorted by name):");
        System.out.printf("%-15s %s%n", "Name", "Age");
        System.out.println("-".repeat(25));
        for (Person p : multiset) {
            System.out.printf("%-15s %d%n", p.getName(), p.getAge());
        }

        System.out.println("\nTotal persons: " + multiset.size());
        System.out.println("(Note: duplicate names 'Alice' and 'Bob' are both stored — like C++ multiset)");
    }
}
