/**
 * ============================================================
 *  TOPIC: Serialization and Deserialization in Java
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW:
 *
 *  Serialization   = Object → byte stream (to file / network / DB)
 *  Deserialization = byte stream → Object (reconstruct)
 *
 *  To make a class serializable:
 *  1. Implement java.io.Serializable (marker interface — no methods)
 *  2. All fields must be serializable (or marked transient)
 *  3. Declare serialVersionUID (strongly recommended)
 *
 *  serialVersionUID:
 *  - Used to verify sender and receiver have compatible classes
 *  - If not declared, JVM generates one based on class structure
 *  - If class changes and UID doesn't match → InvalidClassException
 *  - Always declare it explicitly to control version compatibility
 *
 *  transient keyword:
 *  - Marks fields to be EXCLUDED from serialization
 *  - Use for: passwords, caches, derived data, non-serializable refs
 *  - Transient fields are null/default after deserialization
 *
 *  static fields:
 *  - NOT serialized (belong to class, not object)
 *
 *  Externalizable:
 *  - More control than Serializable
 *  - Must implement writeExternal() and readExternal() manually
 *  - Faster than default serialization
 *
 *  readObject / writeObject:
 *  - Custom serialization hooks inside the class
 *  - Called by ObjectOutputStream/ObjectInputStream automatically
 * ╚══════════════════════════════════════════════════════════╝
 */

import java.io.*;
import java.util.*;

public class Serialization {

    // ═══════════════════════════════════════════════════════
    // 1. BASIC SERIALIZABLE CLASS
    // ═══════════════════════════════════════════════════════
    static class Student implements Serializable {
        /*
         * ╔══════════════════════════════════════════════╗
         *  serialVersionUID: always declare explicitly.
         *  If you add/remove fields and UID changes,
         *  old serialized files become incompatible.
         *  Declaring it gives YOU control over versioning.
         * ╚══════════════════════════════════════════════╝
         */
        private static final long serialVersionUID = 1L;

        private int    rollNo;
        private String name;
        private double cgpa;

        // transient: excluded from serialization
        private transient String  password;    // sensitive — don't serialize
        private transient int     sessionId;   // runtime-only value

        // static: belongs to class, not serialized
        private static String institution = "TechUniversity";

        public Student(int rollNo, String name, double cgpa, String password) {
            this.rollNo   = rollNo;
            this.name     = name;
            this.cgpa     = cgpa;
            this.password = password;
            this.sessionId = new Random().nextInt(10000);
        }

        @Override
        public String toString() {
            return String.format("Student[roll=%d, name=%s, cgpa=%.1f, " +
                "password=%s, sessionId=%d, institution=%s]",
                rollNo, name, cgpa, password, sessionId, institution);
        }
    }

    // ═══════════════════════════════════════════════════════
    // 2. CLASS WITH CUSTOM readObject / writeObject
    // ═══════════════════════════════════════════════════════
    static class SecureUser implements Serializable {
        private static final long serialVersionUID = 2L;

        private String username;
        private transient String password;  // won't serialize by default
        private String email;

        public SecureUser(String username, String password, String email) {
            this.username = username;
            this.password = password;
            this.email    = email;
        }

        /*
         * ╔══════════════════════════════════════════════╗
         *  CUSTOM SERIALIZATION:
         *  writeObject: called during serialization
         *  readObject:  called during deserialization
         *  Must be private — called via reflection by JVM.
         *  Can encrypt sensitive data before writing,
         *  decrypt after reading.
         * ╚══════════════════════════════════════════════╝
         */
        private void writeObject(ObjectOutputStream oos) throws IOException {
            oos.defaultWriteObject();   // serialize non-transient fields
            // Encrypt password before writing (simple XOR for demo)
            String encrypted = encrypt(password);
            oos.writeObject(encrypted);
            System.out.println("[writeObject] Encrypted password written.");
        }

        private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
            ois.defaultReadObject();   // deserialize non-transient fields
            // Decrypt password after reading
            String encrypted = (String) ois.readObject();
            this.password = decrypt(encrypted);
            System.out.println("[readObject] Password decrypted.");
        }

        // Simple Caesar cipher for demo (NOT real encryption!)
        private String encrypt(String s) {
            return s == null ? null :
                s.chars().map(c -> c + 3).collect(StringBuilder::new,
                    StringBuilder::appendCodePoint, StringBuilder::append).toString();
        }

        private String decrypt(String s) {
            return s == null ? null :
                s.chars().map(c -> c - 3).collect(StringBuilder::new,
                    StringBuilder::appendCodePoint, StringBuilder::append).toString();
        }

        @Override public String toString() {
            return "SecureUser[" + username + ", pwd=" + password + ", email=" + email + "]";
        }
    }

    // ═══════════════════════════════════════════════════════
    // 3. INHERITANCE + SERIALIZATION
    // ═══════════════════════════════════════════════════════
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  If PARENT is Serializable → child is automatically Serializable.
     *  If PARENT is NOT Serializable:
     *  - Parent fields are NOT serialized.
     *  - On deserialization, parent's no-arg constructor is called.
     *  - Parent MUST have a no-arg constructor (or error).
     * ╚══════════════════════════════════════════════════════╝
     */
    static class Person {   // NOT Serializable
        String name;
        Person() { this.name = "DefaultPerson"; }  // required no-arg ctor
        Person(String name) { this.name = name; }
    }

    static class Employee extends Person implements Serializable {
        private static final long serialVersionUID = 3L;
        private int    id;
        private double salary;

        Employee(String name, int id, double salary) {
            super(name);
            this.id = id; this.salary = salary;
        }

        @Override public String toString() {
            return "Employee[id=" + id + ", name=" + name + ", salary=" + salary + "]";
        }
    }

    // ═══════════════════════════════════════════════════════
    // 4. SERIALIZING COLLECTIONS
    // ═══════════════════════════════════════════════════════
    static class Department implements Serializable {
        private static final long serialVersionUID = 4L;
        private String        deptName;
        private List<Student> students;

        Department(String name) {
            this.deptName = name;
            this.students = new ArrayList<>();
        }

        public void addStudent(Student s) { students.add(s); }

        @Override public String toString() {
            return "Department[" + deptName + ", students=" + students.size() + "]";
        }
    }

    // ═══════════════════════════════════════════════════════
    // Helper: serialize to byte array (in-memory, no file needed)
    // ═══════════════════════════════════════════════════════
    static byte[] toBytes(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(obj);
        }
        return baos.toByteArray();
    }

    @SuppressWarnings("unchecked")
    static <T> T fromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            return (T) ois.readObject();
        }
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("===== Serialization & Deserialization =====\n");

        // --- Basic Serialization ---
        System.out.println("--- 1. Basic Serializable (transient, static) ---");
        Student original = new Student(101, "Alice", 9.5, "secret123");
        System.out.println("Before serialize: " + original);

        byte[] data = toBytes(original);
        System.out.println("Serialized size: " + data.length + " bytes");

        Student deserialized = fromBytes(data);
        System.out.println("After  deserialize: " + deserialized);

        /*
         * ╔══════════════════════════════════════════════╗
         *  OBSERVE:
         *  - password   = null (transient — not serialized)
         *  - sessionId  = 0    (transient — default int value)
         *  - institution still works (static — class-level, not in stream)
         * ╚══════════════════════════════════════════════╝
         */

        // --- Custom readObject/writeObject ---
        System.out.println("\n--- 2. Custom writeObject/readObject ---");
        SecureUser user = new SecureUser("alice", "mySecret!", "alice@example.com");
        System.out.println("Before: " + user);

        byte[] userData = toBytes(user);
        SecureUser restoredUser = fromBytes(userData);
        System.out.println("After:  " + restoredUser);

        // --- Inheritance + Serialization ---
        System.out.println("\n--- 3. Inheritance (non-serializable parent) ---");
        Employee emp = new Employee("Bob", 201, 75000.0);
        System.out.println("Before: " + emp);

        byte[] empData = toBytes(emp);
        Employee restoredEmp = fromBytes(empData);
        System.out.println("After:  " + restoredEmp);
        /*
         * NOTE: name = "DefaultPerson" because Person is not Serializable.
         * Person's no-arg constructor was called → name reset to "DefaultPerson".
         */

        // --- Serializing Collections ---
        System.out.println("\n--- 4. Serializing Collections ---");
        Department dept = new Department("Computer Science");
        dept.addStudent(new Student(1, "Alice",   9.5, "pw1"));
        dept.addStudent(new Student(2, "Bob",     8.7, "pw2"));
        dept.addStudent(new Student(3, "Charlie", 9.1, "pw3"));
        System.out.println("Before: " + dept);

        byte[] deptData = toBytes(dept);
        Department restoredDept = fromBytes(deptData);
        System.out.println("After:  " + restoredDept);

        // --- Object identity after serialization ---
        System.out.println("\n--- 5. Object Identity After Serialization ---");
        Student s1 = new Student(1, "Test", 9.0, "pass");
        byte[]  d1 = toBytes(s1);

        Student r1 = fromBytes(d1);
        Student r2 = fromBytes(d1);

        System.out.println("r1 == r2         : " + (r1 == r2));        // false: different objects
        System.out.println("r1.equals(r2)?   : " + r1.toString().equals(r2.toString())); // same content

        // --- Cloning via serialization (deep clone trick) ---
        System.out.println("\n--- 6. Deep Clone via Serialization ---");
        Student original2 = new Student(999, "DeepClone", 10.0, "clonePwd");
        byte[]  cloneData  = toBytes(original2);
        Student deepClone  = fromBytes(cloneData);
        System.out.println("Original  : " + original2);
        System.out.println("Deep clone: " + deepClone);
        System.out.println("Same ref? " + (original2 == deepClone)); // false — true deep clone
    }
}
