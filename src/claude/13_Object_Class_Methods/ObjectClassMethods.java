/**
 * ============================================================
 *  TOPIC: java.lang.Object Class and Its Methods
 * ============================================================
 *
 * Every Java class implicitly extends java.lang.Object.
 * Object class provides 11 methods — all classes inherit them.
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW: Key Object methods
 *
 *  toString()    : String representation. Default: ClassName@hexHashCode
 *  equals()      : Logical equality. Default: == (reference equality)
 *  hashCode()    : Integer hash. Default: based on memory address
 *  clone()       : Creates a copy. Default: shallow copy
 *  getClass()    : Returns Class<?> object (runtime class info)
 *  finalize()    : Called by GC before object reclaimed (deprecated Java 9+)
 *  wait()        : Causes thread to wait (for synchronization)
 *  notify()      : Wakes a waiting thread
 *  notifyAll()   : Wakes all waiting threads
 *
 *  CONTRACT between equals() and hashCode():
 *  1. If a.equals(b) → a.hashCode() == b.hashCode() MUST be true
 *  2. If a.hashCode() == b.hashCode() → a.equals(b) MAY or MAY NOT be true
 *  ALWAYS override both together!
 *
 *  When you put objects in HashMap/HashSet, Java uses hashCode()
 *  to find the bucket, then equals() to find exact match.
 *  Violating this contract breaks HashMap/HashSet behavior!
 * ╚══════════════════════════════════════════════════════════╝
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class ObjectClassMethods {

    // ═══════════════════════════════════════════════════════
    // 1. toString()
    // ═══════════════════════════════════════════════════════
    static class Product {
        private int    id;
        private String name;
        private double price;

        public Product(int id, String name, double price) {
            this.id = id; this.name = name; this.price = price;
        }

        /*
         * ╔══════════════════════════════════════════════╗
         *  Override toString() so that:
         *    System.out.println(obj) prints meaningful info
         *    String.valueOf(obj), String + obj all use this
         *  Without override: Product@1b6d3586 (not useful!)
         * ╚══════════════════════════════════════════════╝
         */
        @Override
        public String toString() {
            return String.format("Product{id=%d, name='%s', price=%.2f}", id, name, price);
        }

        public int    getId()    { return id; }
        public String getName()  { return name; }
        public double getPrice() { return price; }
    }

    // ═══════════════════════════════════════════════════════
    // 2. equals() and hashCode()
    // ═══════════════════════════════════════════════════════
    static class Student {
        private final int    rollNo;   // natural key
        private final String name;
        private       double cgpa;

        public Student(int rollNo, String name, double cgpa) {
            this.rollNo = rollNo;
            this.name   = name;
            this.cgpa   = cgpa;
        }

        /*
         * ╔══════════════════════════════════════════════╗
         *  equals() contract (java.lang.Object javadoc):
         *  1. Reflexive  : x.equals(x) → true
         *  2. Symmetric  : x.equals(y) ↔ y.equals(x)
         *  3. Transitive : x.equals(y) && y.equals(z) → x.equals(z)
         *  4. Consistent : same result on repeated calls
         *  5. Null-safe  : x.equals(null) → false (never NPE)
         * ╚══════════════════════════════════════════════╝
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;              // same reference
            if (!(o instanceof Student)) return false; // null or wrong type
            Student s = (Student) o;
            return rollNo == s.rollNo;               // business key: rollNo
        }

        /*
         * ╔══════════════════════════════════════════════╗
         *  hashCode() must be consistent with equals():
         *  Objects.hash() uses the same fields as equals().
         *  Using Objects.hash() is the safest approach.
         * ╚══════════════════════════════════════════════╝
         */
        @Override
        public int hashCode() {
            return Objects.hash(rollNo);
        }

        @Override
        public String toString() {
            return "Student(roll=" + rollNo + ", " + name + ", cgpa=" + cgpa + ")";
        }

        public int    getRollNo() { return rollNo; }
        public String getName()   { return name; }
        public double getCgpa()   { return cgpa; }
    }

    // ═══════════════════════════════════════════════════════
    // 3. getClass() and instanceof
    // ═══════════════════════════════════════════════════════
    static class Shape  { }
    static class Circle extends Shape { double radius; Circle(double r){ radius=r; } }
    static class Rect   extends Shape { double l, w; Rect(double l, double w){ this.l=l; this.w=w; } }

    static void inspectObject(Object obj) {
        System.out.println("getClass()         : " + obj.getClass().getName());
        System.out.println("getSimpleName()    : " + obj.getClass().getSimpleName());
        System.out.println("getSuperclass()    : " + obj.getClass().getSuperclass().getSimpleName());
        System.out.println("instanceof Shape   : " + (obj instanceof Shape));
        System.out.println("instanceof Circle  : " + (obj instanceof Circle));
        System.out.println("instanceof Object  : " + (obj instanceof Object)); // always true
    }

    // ═══════════════════════════════════════════════════════
    // 4. What happens when equals/hashCode are NOT overridden
    // ═══════════════════════════════════════════════════════
    static class BrokenKey {
        int id;
        BrokenKey(int id) { this.id = id; }
        // No equals/hashCode override — uses Object's default
    }

    static class GoodKey {
        int id;
        GoodKey(int id) { this.id = id; }

        @Override public boolean equals(Object o) {
            return o instanceof GoodKey && ((GoodKey)o).id == this.id;
        }
        @Override public int hashCode() { return Objects.hash(id); }
    }

    // ═══════════════════════════════════════════════════════
    // 5. Objects utility class (Java 7+)
    // ═══════════════════════════════════════════════════════
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  java.util.Objects (note: not java.lang.Object) provides
     *  null-safe utility methods:
     *  Objects.equals(a, b)       → null-safe equality
     *  Objects.hash(fields...)    → combined hashCode
     *  Objects.toString(obj, def) → toString with default
     *  Objects.requireNonNull(obj)→ throw NPE if null (validation)
     *  Objects.isNull(obj)        → obj == null
     *  Objects.nonNull(obj)       → obj != null
     * ╚══════════════════════════════════════════════════════╝
     */
    static class SafeProcessor {
        private final String name;

        public SafeProcessor(String name) {
            // Fail fast with meaningful message
            this.name = Objects.requireNonNull(name, "name must not be null");
        }

        public void process(Object data) {
            if (Objects.isNull(data)) {
                System.out.println("Skipping null data.");
                return;
            }
            System.out.println("Processing: " + Objects.toString(data, "N/A"));
        }
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Object Class Methods =====\n");

        // --- toString() ---
        System.out.println("--- 1. toString() ---");
        Product p1 = new Product(1, "Laptop", 999.99);
        System.out.println(p1);                           // calls toString() implicitly
        System.out.println("Product: " + p1);             // string concatenation calls toString()
        System.out.println(String.valueOf(p1));            // explicit toString()

        // --- equals() and hashCode() ---
        System.out.println("\n--- 2. equals() and hashCode() ---");
        Student s1 = new Student(101, "Alice", 9.5);
        Student s2 = new Student(101, "Alice Copy", 8.0); // same rollNo, diff name
        Student s3 = new Student(102, "Bob", 8.7);

        System.out.println("s1.equals(s2) : " + s1.equals(s2));  // true (same rollNo)
        System.out.println("s1.equals(s3) : " + s1.equals(s3));  // false
        System.out.println("s1.hashCode() : " + s1.hashCode());
        System.out.println("s2.hashCode() : " + s2.hashCode());   // same as s1
        System.out.println("s3.hashCode() : " + s3.hashCode());   // different

        // HashSet uses equals + hashCode — must be consistent
        HashSet<Student> set = new HashSet<>();
        set.add(s1);
        set.add(s2);   // same hashCode + equals → NOT added (duplicate)
        set.add(s3);
        System.out.println("Set size (should be 2): " + set.size());

        // --- getClass() and reflection ---
        System.out.println("\n--- 3. getClass() ---");
        Shape[] shapes = { new Circle(5.0), new Rect(3, 4), new Circle(2.0) };
        inspectObject(shapes[0]);

        // --- equals/hashCode broken vs correct in HashMap ---
        System.out.println("\n--- 4. HashMap: Broken vs Good Key ---");
        HashMap<BrokenKey, String> brokenMap = new HashMap<>();
        BrokenKey k1 = new BrokenKey(1);
        BrokenKey k2 = new BrokenKey(1);  // logically same
        brokenMap.put(k1, "value1");
        System.out.println("BrokenKey k2 lookup: " + brokenMap.get(k2)); // null! (bug)

        HashMap<GoodKey, String> goodMap = new HashMap<>();
        GoodKey g1 = new GoodKey(1);
        GoodKey g2 = new GoodKey(1);  // logically same
        goodMap.put(g1, "value1");
        System.out.println("GoodKey   g2 lookup: " + goodMap.get(g2));   // "value1" correct

        // --- Objects utility ---
        System.out.println("\n--- 5. Objects Utility Class ---");
        SafeProcessor sp = new SafeProcessor("MyProcessor");
        sp.process("Hello");
        sp.process(null);
        sp.process(42);

        System.out.println("Objects.equals(null, null) : " + Objects.equals(null, null));
        System.out.println("Objects.equals(null, \"a\") : " + Objects.equals(null, "a"));

        try {
            new SafeProcessor(null);
        } catch (NullPointerException e) {
            System.out.println("Caught NPE: " + e.getMessage());
        }
    }
}
