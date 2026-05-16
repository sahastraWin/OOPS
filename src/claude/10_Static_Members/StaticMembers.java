/**
 * ============================================================
 *  TOPIC: Static Members in Java
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW:
 *  - static members belong to the CLASS, not to any object.
 *  - ONE copy shared across ALL instances.
 *  - Loaded when class is first loaded into JVM.
 *  - Accessed via ClassName.member (preferred) or obj.member.
 *
 *  Static variable:
 *  - Shared state across all objects (e.g., counter, config)
 *  - Stored in Method Area (PermGen/Metaspace) of JVM
 *
 *  Static method:
 *  - Can access ONLY static fields and other static methods
 *  - NO 'this' or 'super' inside static methods
 *  - Cannot be overridden (only hidden in subclass)
 *  - Can be called without creating an object
 *
 *  Static block:
 *  - Runs ONCE when class is loaded
 *  - Used for complex static initialisation
 *  - Multiple static blocks execute in order
 *
 *  Static inner class:
 *  - Does NOT have access to instance members of outer class
 *  - Instantiated without outer class object
 * ╚══════════════════════════════════════════════════════════╝
 */
public class StaticMembers {

    // ═══════════════════════════════════════════════════════
    // 1. STATIC FIELD + STATIC METHOD (Object Counter)
    // ═══════════════════════════════════════════════════════
    static class Employee {
        private static int    totalCount   = 0;
        private static int    nextId       = 1000;
        private static String company      = "TechCorp";

        private int    id;
        private String name;
        private double salary;

        static {
            System.out.println("[Employee static block] Class loaded. Company: " + company);
        }

        public Employee(String name, double salary) {
            this.id     = nextId++;
            this.name   = name;
            this.salary = salary;
            totalCount++;
            System.out.println("[Employee] Created #" + this.id + ": " + name);
        }

        // Static methods — can only use static state
        public static int    getTotalCount() { return totalCount; }
        public static String getCompany()    { return company; }
        public static void   setCompany(String c) { company = c; }

        // Instance method — can use both instance and static state
        public void displayBadge() {
            System.out.printf("[Badge] %s | ID: %d | %s | Salary: %.0f%n",
                name, id, company, salary);
        }

        @Override public String toString() {
            return "Employee(" + id + ", " + name + ")";
        }
    }

    // ═══════════════════════════════════════════════════════
    // 2. STATIC UTILITY CLASS
    // ═══════════════════════════════════════════════════════
    static final class StringUtils {
        private StringUtils() {}  // prevent instantiation

        public static boolean isPalindrome(String s) {
            String clean = s.toLowerCase().replaceAll("[^a-z0-9]", "");
            return clean.equals(new StringBuilder(clean).reverse().toString());
        }

        public static String capitalize(String s) {
            if (s == null || s.isEmpty()) return s;
            return Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
        }

        public static int countWords(String s) {
            return s.trim().isEmpty() ? 0 : s.trim().split("\\s+").length;
        }

        public static String repeat(String s, int n) {
            return s.repeat(n);
        }
    }

    // ═══════════════════════════════════════════════════════
    // 3. STATIC CONSTANTS + ENUM-LIKE STATIC FIELDS
    // ═══════════════════════════════════════════════════════
    static class MathConstants {
        public static final double PI      = Math.PI;
        public static final double E       = Math.E;
        public static final double GOLDEN  = 1.6180339887;
        public static final double SQRT2   = Math.sqrt(2);

        private MathConstants() {}

        public static double circleArea(double r)    { return PI * r * r; }
        public static double sphereVolume(double r)  { return (4.0/3.0) * PI * r * r * r; }
    }

    // ═══════════════════════════════════════════════════════
    // 4. STATIC NESTED CLASS
    // ═══════════════════════════════════════════════════════
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  STATIC NESTED CLASS:
     *  - Declared inside another class with 'static'.
     *  - Does NOT have access to outer class's instance members.
     *  - Instantiated without outer class object:
     *      new OuterClass.StaticNested()
     *  - Used for: Builder pattern, helper classes, grouping.
     * ╚══════════════════════════════════════════════════════╝
     */
    static class HttpRequest {
        private final String method;
        private final String url;
        private final String body;
        private final int    timeout;

        private HttpRequest(Builder b) {
            this.method  = b.method;
            this.url     = b.url;
            this.body    = b.body;
            this.timeout = b.timeout;
        }

        @Override public String toString() {
            return method + " " + url + " (timeout=" + timeout + "s)"
                + (body != null ? " body=" + body : "");
        }

        // Static nested Builder class
        static class Builder {
            private String method  = "GET";
            private String url;
            private String body;
            private int    timeout = 30;

            public Builder url(String url)       { this.url     = url; return this; }
            public Builder method(String method) { this.method  = method; return this; }
            public Builder body(String body)     { this.body    = body; return this; }
            public Builder timeout(int t)        { this.timeout = t; return this; }

            public HttpRequest build() {
                if (url == null || url.isEmpty())
                    throw new IllegalStateException("URL is required");
                return new HttpRequest(this);
            }
        }
    }

    // ═══════════════════════════════════════════════════════
    // 5. STATIC IMPORT (simulated — cannot import in inner code)
    // ═══════════════════════════════════════════════════════
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  STATIC IMPORT (Java 5+):
     *  import static java.lang.Math.PI;
     *  import static java.lang.Math.sqrt;
     *  Allows using PI directly instead of Math.PI.
     *  Used with static utility methods for cleaner code.
     * ╚══════════════════════════════════════════════════════╝
     */

    // ═══════════════════════════════════════════════════════
    // 6. MULTIPLE STATIC BLOCKS (execution order)
    // ═══════════════════════════════════════════════════════
    static class LoadOrder {
        static int x;
        static int y;

        static {
            x = 10;
            System.out.println("[Block 1] x = " + x);
        }

        static {
            y = x * 2;   // x is already initialised from block 1
            System.out.println("[Block 2] y = " + y);
        }

        static { System.out.println("[Block 3] All statics ready. x=" + x + " y=" + y); }
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Static Members =====\n");

        // --- Static field + method ---
        System.out.println("--- 1. Static Object Counter ---");
        System.out.println("Count before: " + Employee.getTotalCount());
        Employee e1 = new Employee("Alice", 75000);
        Employee e2 = new Employee("Bob", 85000);
        Employee e3 = new Employee("Charlie", 65000);
        System.out.println("Count after:  " + Employee.getTotalCount());
        System.out.println("Company: " + Employee.getCompany());

        Employee.setCompany("NewTechCorp");
        e1.displayBadge();  // shows updated company (shared static field)
        e2.displayBadge();
        e3.displayBadge();

        // --- Utility class ---
        System.out.println("\n--- 2. Static Utility Class ---");
        System.out.println("isPalindrome(\"racecar\"): " + StringUtils.isPalindrome("racecar"));
        System.out.println("isPalindrome(\"A man a plan a canal Panama\"): " +
            StringUtils.isPalindrome("A man a plan a canal Panama"));
        System.out.println("capitalize(\"hELLO\"): " + StringUtils.capitalize("hELLO"));
        System.out.println("countWords(\"Hello World\"): " + StringUtils.countWords("Hello World"));

        // --- Static constants ---
        System.out.println("\n--- 3. Static Constants ---");
        System.out.printf("PI=%.5f  E=%.5f  Golden=%.5f%n",
            MathConstants.PI, MathConstants.E, MathConstants.GOLDEN);
        System.out.printf("circleArea(5) = %.4f%n", MathConstants.circleArea(5));
        System.out.printf("sphereVolume(3) = %.4f%n", MathConstants.sphereVolume(3));

        // --- Static Nested Builder ---
        System.out.println("\n--- 4. Static Nested Class (Builder Pattern) ---");
        HttpRequest req1 = new HttpRequest.Builder()
            .url("https://api.example.com/users")
            .method("GET")
            .timeout(10)
            .build();

        HttpRequest req2 = new HttpRequest.Builder()
            .url("https://api.example.com/users")
            .method("POST")
            .body("{\"name\":\"Alice\"}")
            .timeout(30)
            .build();

        System.out.println(req1);
        System.out.println(req2);

        // --- Static blocks order ---
        System.out.println("\n--- 5. Multiple Static Blocks (order) ---");
        // Accessing LoadOrder triggers all static blocks
        System.out.println("LoadOrder.x=" + LoadOrder.x + " y=" + LoadOrder.y);
    }
}
