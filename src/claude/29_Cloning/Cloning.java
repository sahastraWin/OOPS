/**
 * ============================================================
 *  TOPIC: Object Cloning in Java
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW:
 *
 *  clone() is defined in java.lang.Object:
 *    protected Object clone() throws CloneNotSupportedException
 *
 *  To enable cloning:
 *  1. Implement Cloneable (marker interface)
 *  2. Override clone() and call super.clone()
 *  3. Make clone() public (Object's is protected)
 *
 *  If Cloneable is NOT implemented → CloneNotSupportedException
 *
 *  SHALLOW COPY (default Object.clone()):
 *  - Primitives: values are copied
 *  - Object references: REFERENCE is copied (same object shared!)
 *  - Result: two objects sharing internal mutable state
 *
 *  DEEP COPY:
 *  - Recursively clones all mutable referenced objects
 *  - Each object is fully independent
 *  - Must be implemented manually
 *
 *  Alternatives to clone():
 *  1. Copy constructor: MyClass(MyClass other)
 *  2. Static factory: MyClass.copyOf(other)
 *  3. Serialization-based deep clone
 *  4. Libraries: Apache Commons, Jackson, Gson
 *
 *  Issues with Cloneable (Joshua Bloch - Effective Java):
 *  - Cloneable is a "broken" design (marker iface changes Object behavior)
 *  - clone() doesn't call constructor — bypasses constructor logic
 *  - Prefer copy constructors or copy factories over clone()
 * ╚══════════════════════════════════════════════════════════╝
 */

import java.util.Arrays;

public class Cloning {

    // ═══════════════════════════════════════════════════════
    // 1. SHALLOW COPY — default clone()
    // ═══════════════════════════════════════════════════════
    static class Address {
        String city;
        String country;

        Address(String city, String country) {
            this.city = city; this.country = country;
        }

        @Override public String toString() { return city + ", " + country; }
    }

    static class ShallowEmployee implements Cloneable {
        String  name;
        int     age;
        Address address;   // mutable reference — DANGER in shallow copy

        ShallowEmployee(String name, int age, Address address) {
            this.name    = name;
            this.age     = age;
            this.address = address;
        }

        /*
         * ╔══════════════════════════════════════════════╗
         *  Shallow clone: super.clone() copies the reference
         *  to address, NOT the Address object itself.
         *  Both original and clone share the SAME Address object.
         *  Modifying one affects the other!
         * ╚══════════════════════════════════════════════╝
         */
        @Override
        public ShallowEmployee clone() {
            try {
                return (ShallowEmployee) super.clone();   // shallow copy
            } catch (CloneNotSupportedException e) {
                throw new AssertionError("Should not happen", e);
            }
        }

        @Override public String toString() {
            return "Employee[" + name + ", age=" + age + ", addr=" + address + "]";
        }
    }

    // ═══════════════════════════════════════════════════════
    // 2. DEEP COPY — manual clone of all mutable fields
    // ═══════════════════════════════════════════════════════
    static class DeepAddress implements Cloneable {
        String city;
        String country;

        DeepAddress(String city, String country) {
            this.city = city; this.country = country;
        }

        @Override
        public DeepAddress clone() {
            try {
                return (DeepAddress) super.clone();  // String is immutable → safe
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        @Override public String toString() { return city + ", " + country; }
    }

    static class DeepEmployee implements Cloneable {
        String      name;
        int         age;
        DeepAddress address;
        int[]       scores;   // mutable array

        DeepEmployee(String name, int age, DeepAddress address, int[] scores) {
            this.name    = name;
            this.age     = age;
            this.address = address;
            this.scores  = scores;
        }

        /*
         * ╔══════════════════════════════════════════════╗
         *  DEEP CLONE:
         *  1. Call super.clone() for primitives and Strings
         *  2. Manually clone each mutable reference field
         *  3. Clone the mutable array with .clone()
         * ╚══════════════════════════════════════════════╝
         */
        @Override
        public DeepEmployee clone() {
            try {
                DeepEmployee copy = (DeepEmployee) super.clone();
                copy.address = address.clone();         // deep clone Address
                copy.scores  = scores.clone();          // deep clone array
                return copy;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        @Override public String toString() {
            return "DeepEmployee[" + name + ", age=" + age
                + ", addr=" + address + ", scores=" + Arrays.toString(scores) + "]";
        }
    }

    // ═══════════════════════════════════════════════════════
    // 3. COPY CONSTRUCTOR (preferred over clone())
    // ═══════════════════════════════════════════════════════
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  Copy Constructor:
     *  - No need to implement Cloneable
     *  - No CloneNotSupportedException
     *  - Does call the constructor (correct initialization)
     *  - Explicitly clear and easy to read
     *  - Recommended by Effective Java
     * ╚══════════════════════════════════════════════════════╝
     */
    static class Student {
        String   name;
        int      age;
        String[] courses;   // mutable array

        // Primary constructor
        Student(String name, int age, String[] courses) {
            this.name    = name;
            this.age     = age;
            this.courses = courses.clone();   // defensive copy
        }

        // COPY CONSTRUCTOR — deep copy
        Student(Student other) {
            this.name    = other.name;          // String immutable — safe
            this.age     = other.age;           // primitive — safe
            this.courses = other.courses.clone(); // deep copy array
        }

        // Static copy factory (alternative to copy constructor)
        public static Student copyOf(Student other) {
            return new Student(other);
        }

        @Override public String toString() {
            return "Student[" + name + ", age=" + age
                + ", courses=" + Arrays.toString(courses) + "]";
        }
    }

    // ═══════════════════════════════════════════════════════
    // 4. PROTOTYPE PATTERN — using clone()
    // ═══════════════════════════════════════════════════════
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  PROTOTYPE PATTERN:
     *  Create new objects by cloning an existing "prototype".
     *  Used when object creation is expensive (DB fetch, config)
     *  and you need many similar objects.
     *  clone() is the core mechanism here.
     * ╚══════════════════════════════════════════════════════╝
     */
    interface Prototype<T> { T clone(); }

    static class ConfigTemplate implements Prototype<ConfigTemplate> {
        String host;
        int    port;
        boolean ssl;
        int[]  allowedPorts;

        ConfigTemplate(String host, int port, boolean ssl, int[] allowedPorts) {
            this.host         = host;
            this.port         = port;
            this.ssl          = ssl;
            this.allowedPorts = allowedPorts.clone();
        }

        @Override
        public ConfigTemplate clone() {
            return new ConfigTemplate(host, port, ssl, allowedPorts);
        }

        public ConfigTemplate withPort(int newPort) {
            ConfigTemplate copy = clone();
            copy.port = newPort;
            return copy;
        }

        public ConfigTemplate withHost(String newHost) {
            ConfigTemplate copy = clone();
            copy.host = newHost;
            return copy;
        }

        @Override public String toString() {
            return (ssl ? "https" : "http") + "://" + host + ":" + port
                + " ports=" + Arrays.toString(allowedPorts);
        }
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Object Cloning =====\n");

        // --- Shallow copy problem ---
        System.out.println("--- 1. Shallow Copy (Problem) ---");
        Address addr     = new Address("Mumbai", "India");
        ShallowEmployee e1 = new ShallowEmployee("Alice", 28, addr);
        ShallowEmployee e2 = e1.clone();

        System.out.println("e1: " + e1);
        System.out.println("e2: " + e2);
        System.out.println("Same address object? " + (e1.address == e2.address)); // true!

        e1.address.city = "Delhi";  // modifying e1's address also affects e2!
        System.out.println("After e1 city change:");
        System.out.println("e1: " + e1);
        System.out.println("e2: " + e2);  // e2's city also changed — PROBLEM!

        // --- Deep copy solution ---
        System.out.println("\n--- 2. Deep Copy (Solution) ---");
        DeepAddress deepAddr = new DeepAddress("Mumbai", "India");
        int[] scores = {85, 90, 78};
        DeepEmployee d1 = new DeepEmployee("Bob", 30, deepAddr, scores);
        DeepEmployee d2 = d1.clone();

        System.out.println("d1: " + d1);
        System.out.println("d2: " + d2);
        System.out.println("Same address object? " + (d1.address == d2.address)); // false!

        d1.address.city = "Delhi";
        d1.scores[0]    = 999;
        System.out.println("After modifying d1:");
        System.out.println("d1: " + d1);
        System.out.println("d2: " + d2);  // d2 unaffected — correct!

        // --- Copy constructor ---
        System.out.println("\n--- 3. Copy Constructor (preferred) ---");
        String[] courses = {"Java", "Python", "DSA"};
        Student s1 = new Student("Charlie", 22, courses);
        Student s2 = new Student(s1);         // copy constructor
        Student s3 = Student.copyOf(s1);      // static factory

        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);
        System.out.println("s3: " + s3);

        s1.courses[0] = "Rust";
        System.out.println("After s1 courses[0] change:");
        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);  // unaffected (deep copy)
        System.out.println("s3: " + s3);  // unaffected

        // --- Prototype pattern ---
        System.out.println("\n--- 4. Prototype Pattern ---");
        ConfigTemplate base = new ConfigTemplate("api.prod.com", 443, true, new int[]{80, 443, 8080});
        System.out.println("Base: " + base);

        ConfigTemplate staging = base.withHost("api.staging.com").withPort(8443);
        ConfigTemplate local   = base.withHost("localhost").withPort(3000);

        System.out.println("Staging: " + staging);
        System.out.println("Local:   " + local);
        System.out.println("Base unchanged: " + base);

        // --- Comparison of approaches ---
        System.out.println("\n--- 5. Summary: clone() vs Copy Constructor ---");
        System.out.println("clone() issues:");
        System.out.println("  - Must implement Cloneable (broken design)");
        System.out.println("  - Bypasses constructor (no validation)");
        System.out.println("  - CloneNotSupportedException (checked)");
        System.out.println("  - Covariant return helps but still verbose");
        System.out.println("Copy Constructor advantages:");
        System.out.println("  - No Cloneable needed");
        System.out.println("  - Calls constructor (validation runs)");
        System.out.println("  - Explicit and readable");
        System.out.println("  - Can convert between types: new ArrayList(linkedList)");
    }
}
