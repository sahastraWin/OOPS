/**
 * ============================================================
 *  TOPIC: Comparable and Comparator in Java
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW:
 *
 *  Comparable<T> (java.lang):
 *  - Interface: int compareTo(T o)
 *  - "Natural ordering" — class defines its own comparison logic
 *  - Implemented by the class itself (intrusive)
 *  - Used by: Collections.sort(), Arrays.sort(), TreeSet, TreeMap
 *  - Return: negative → this < o | zero → this == o | positive → this > o
 *  - Rule: consistent with equals() (if a.compareTo(b)==0 → a.equals(b))
 *
 *  Comparator<T> (java.util):
 *  - Interface: int compare(T o1, T o2)
 *  - "External ordering" — separate class/lambda defines comparison
 *  - Not intrusive — doesn't modify the class being sorted
 *  - Can have multiple Comparators for different sort orders
 *  - Java 8: Comparator.comparing(), thenComparing(), reversed()
 *
 *  Comparable vs Comparator:
 *  ┌──────────────────┬──────────────────┬──────────────────┐
 *  │                  │ Comparable       │ Comparator       │
 *  ├──────────────────┼──────────────────┼──────────────────┤
 *  │ Package          │ java.lang        │ java.util        │
 *  │ Method           │ compareTo(T)     │ compare(T,T)     │
 *  │ Where defined    │ In the class     │ External class   │
 *  │ Sort order       │ Single natural   │ Multiple custom  │
 *  │ Modify class?    │ Yes              │ No               │
 *  └──────────────────┴──────────────────┴──────────────────┘
 * ╚══════════════════════════════════════════════════════════╝
 */

import java.util.*;
import java.util.stream.*;

public class ComparableAndComparator {

    // ─── Comparable: natural ordering by salary ───────────────
    static class Employee implements Comparable<Employee> {
        String name;
        String dept;
        double salary;
        int    age;

        Employee(String n, String d, double s, int a) {
            name=n; dept=d; salary=s; age=a;
        }

        /*
         * ╔══════════════════════════════════════════════╗
         *  Natural order: by salary (ascending).
         *  Common pattern: Double.compare(this.x, other.x)
         *  avoids subtraction issues with doubles/overflow.
         * ╚══════════════════════════════════════════════╝
         */
        @Override
        public int compareTo(Employee other) {
            return Double.compare(this.salary, other.salary);
        }

        @Override public String toString() {
            return String.format("%-10s %-12s $%-8.0f age%d", name, dept, salary, age);
        }
    }

    // ─── Custom Comparators (multiple sort strategies) ─────────
    static class EmployeeComparators {

        // By name (alphabetical)
        public static final Comparator<Employee> BY_NAME =
            Comparator.comparing(e -> e.name);

        // By salary descending
        public static final Comparator<Employee> BY_SALARY_DESC =
            Comparator.comparingDouble((Employee e) -> e.salary).reversed();

        // By dept then name (chained)
        public static final Comparator<Employee> BY_DEPT_THEN_NAME =
            Comparator.comparing((Employee e) -> e.dept)
                      .thenComparing(e -> e.name);

        // By age then salary desc (complex chain)
        public static final Comparator<Employee> BY_AGE_SALARY_DESC =
            Comparator.comparingInt((Employee e) -> e.age)
                      .thenComparing(Comparator.comparingDouble(
                          (Employee e) -> e.salary).reversed());

        // Null-safe comparator (handles null names)
        public static final Comparator<Employee> NULL_SAFE_NAME =
            Comparator.comparing(e -> e.name, Comparator.nullsLast(String::compareTo));
    }

    // ─── Product class with Comparable ───────────────────────
    static class Product implements Comparable<Product> {
        String name;
        double price;
        int    rating;

        Product(String n, double p, int r) { name=n; price=p; rating=r; }

        // Natural order: by price ascending
        @Override public int compareTo(Product other) {
            return Double.compare(this.price, other.price);
        }

        @Override public String toString() {
            return name + "($" + price + ",★" + rating + ")";
        }
    }

    // ─── Student: multiple sort criteria demo ─────────────────
    static class Student {
        String name; int grade; double gpa;
        Student(String n, int g, double gpa) { name=n; grade=g; this.gpa=gpa; }
        @Override public String toString() { return name + "(G" + grade + ",GPA" + gpa + ")"; }
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Comparable and Comparator =====\n");

        List<Employee> employees = Arrays.asList(
            new Employee("Charlie", "Engineering", 85000, 32),
            new Employee("Alice",   "HR",          60000, 26),
            new Employee("Eve",     "Engineering", 95000, 30),
            new Employee("Bob",     "Marketing",   70000, 35),
            new Employee("Diana",   "HR",          55000, 28),
            new Employee("Frank",   "Marketing",   70000, 42)
        );

        // --- Comparable (natural order: salary ascending) ---
        System.out.println("--- 1. Comparable (natural order: salary asc) ---");
        List<Employee> sorted = new ArrayList<>(employees);
        Collections.sort(sorted);  // uses compareTo
        sorted.forEach(System.out::println);

        // TreeSet uses natural order
        TreeSet<Employee> treeSet = new TreeSet<>(employees);
        System.out.println("TreeSet first (lowest salary): " + treeSet.first());
        System.out.println("TreeSet last  (highest salary): " + treeSet.last());

        // --- Comparator: by name ---
        System.out.println("\n--- 2. Comparator: by name ---");
        employees.stream()
            .sorted(EmployeeComparators.BY_NAME)
            .forEach(System.out::println);

        // --- Comparator: by salary desc ---
        System.out.println("\n--- 3. Comparator: salary descending ---");
        employees.stream()
            .sorted(EmployeeComparators.BY_SALARY_DESC)
            .forEach(System.out::println);

        // --- Comparator: chained (dept then name) ---
        System.out.println("\n--- 4. Comparator: dept then name ---");
        employees.stream()
            .sorted(EmployeeComparators.BY_DEPT_THEN_NAME)
            .forEach(System.out::println);

        // --- Comparator.comparing() with lambda ---
        System.out.println("\n--- 5. Inline Comparator with lambda ---");
        employees.stream()
            .sorted((e1, e2) -> e1.name.compareTo(e2.name))
            .forEach(e -> System.out.print(e.name + " "));
        System.out.println();

        // --- Multiple comparators on Product ---
        System.out.println("\n--- 6. Product: multiple sort strategies ---");
        List<Product> products = Arrays.asList(
            new Product("Laptop",  999.99, 4),
            new Product("Phone",   499.50, 5),
            new Product("Tablet",  349.99, 3),
            new Product("Monitor", 299.99, 4),
            new Product("Keyboard", 79.99, 5)
        );

        System.out.print("Natural (price asc): ");
        products.stream().sorted().forEach(p -> System.out.print(p.name + " "));
        System.out.println();

        System.out.print("By rating desc: ");
        products.stream()
            .sorted(Comparator.comparingInt((Product p)->p.rating).reversed())
            .forEach(p -> System.out.print(p.name + " "));
        System.out.println();

        System.out.print("By rating desc then price asc: ");
        products.stream()
            .sorted(Comparator.comparingInt((Product p)->p.rating).reversed()
                .thenComparingDouble(p -> p.price))
            .forEach(p -> System.out.print(p.name + "$" + (int)p.price + " "));
        System.out.println();

        // --- Student: complex chain ---
        System.out.println("\n--- 7. Student: complex sort chain ---");
        List<Student> students = Arrays.asList(
            new Student("Alice", 12, 3.9),
            new Student("Bob",   11, 3.7),
            new Student("Carol", 12, 3.5),
            new Student("Dave",  11, 3.9),
            new Student("Eve",   12, 3.9)
        );

        Comparator<Student> complex =
            Comparator.comparingInt((Student s) -> s.grade)
                .reversed()
                .thenComparingDouble((Student s) -> s.gpa)
                .reversed()
                .thenComparing(s -> s.name);

        students.stream().sorted(complex).forEach(System.out::println);

        // --- TreeMap with custom Comparator ---
        System.out.println("\n--- 8. TreeMap with custom Comparator ---");
        TreeMap<String, Integer> reverseMap = new TreeMap<>(Comparator.reverseOrder());
        reverseMap.put("Banana", 3);
        reverseMap.put("Apple", 5);
        reverseMap.put("Cherry", 2);
        reverseMap.put("Date", 8);
        System.out.println("TreeMap (reverse key order): " + reverseMap);
    }
}
