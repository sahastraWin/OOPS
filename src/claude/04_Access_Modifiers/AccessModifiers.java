/**
 * ============================================================
 *  TOPIC: Access Modifiers in Java
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW: Access Modifier Table
 *
 *  ┌─────────────┬──────┬─────────┬───────────┬─────────────┐
 *  │ Modifier    │ Same │ Same    │ Different │ Different   │
 *  │             │ Class│ Package │ Pkg+Sub   │ Pkg+NonSub  │
 *  ├─────────────┼──────┼─────────┼───────────┼─────────────┤
 *  │ private     │  ✓   │   ✗     │    ✗      │     ✗       │
 *  │ default     │  ✓   │   ✓     │    ✗      │     ✗       │
 *  │ protected   │  ✓   │   ✓     │    ✓      │     ✗       │
 *  │ public      │  ✓   │   ✓     │    ✓      │     ✓       │
 *  └─────────────┴──────┴─────────┴───────────┴─────────────┘
 *
 *  KEY POINTS:
 *  - private: most restrictive; only within the same class
 *  - default (package-private): no keyword; same package only
 *  - protected: same package + subclasses (any package)
 *  - public: accessible from anywhere
 *
 *  Class-level modifiers:
 *  - A top-level class can only be public or default.
 *  - Inner/nested classes can use all four.
 *
 *  Constructor access:
 *  - private constructor → Singleton, utility class, Builder
 *  - protected constructor → only subclasses can instantiate
 * ╚══════════════════════════════════════════════════════════╝
 */
public class AccessModifiers {

    // ─── Demonstrating all four modifiers within one class ───

    static class AccessDemo {
        private   int privateField   = 1;   // only this class
        int           defaultField   = 2;   // package only (no keyword)
        protected int protectedField = 3;   // package + subclasses
        public    int publicField    = 4;   // everywhere

        private   void privateMethod()   { System.out.println("private method"); }
        void          defaultMethod()    { System.out.println("default method"); }
        protected void protectedMethod() { System.out.println("protected method"); }
        public    void publicMethod()    { System.out.println("public method"); }

        // Within the same class: all are accessible
        public void showAll() {
            System.out.println("private   : " + privateField);
            System.out.println("default   : " + defaultField);
            System.out.println("protected : " + protectedField);
            System.out.println("public    : " + publicField);
            privateMethod();
            defaultMethod();
            protectedMethod();
            publicMethod();
        }
    }

    // ─── Subclass in same file (same package) ───
    static class SubAccessDemo extends AccessDemo {
        public void showFromSubclass() {
            // privateField    → NOT accessible (compile error)
            System.out.println("default   from sub: " + defaultField);   // same package OK
            System.out.println("protected from sub: " + protectedField); // subclass OK
            System.out.println("public    from sub: " + publicField);
        }
    }

    // ─── Utility class with private constructor ───
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  INTERVIEW TIP:
     *  Utility classes (like java.lang.Math) have a PRIVATE
     *  constructor to prevent instantiation. They only have
     *  static methods. This is a common pattern.
     * ╚══════════════════════════════════════════════════════╝
     */
    static final class MathUtils {
        private MathUtils() {}   // prevent instantiation

        public static int square(int x) { return x * x; }
        public static int cube(int x)   { return x * x * x; }
        public static boolean isPrime(int n) {
            if (n < 2) return false;
            for (int i = 2; i * i <= n; i++)
                if (n % i == 0) return false;
            return true;
        }
    }

    // ─── Immutable class using private fields + no setters ───
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  IMMUTABLE CLASS (interview favourite):
     *  1. Declare class as final (no subclassing)
     *  2. All fields private and final
     *  3. No setters
     *  4. Deep copy mutable fields in constructor and getters
     *  5. Return deep copies from getters for mutable fields
     *  Examples: String, Integer, LocalDate in Java SDK
     * ╚══════════════════════════════════════════════════════╝
     */
    static final class ImmutableEmployee {
        private final String   name;
        private final int      id;
        private final double   salary;
        private final int[]    bonusHistory;   // mutable → must deep copy

        public ImmutableEmployee(String name, int id, double salary, int[] bonusHistory) {
            this.name         = name;
            this.id           = id;
            this.salary       = salary;
            this.bonusHistory = bonusHistory.clone();  // defensive copy
        }

        public String getName()   { return name; }
        public int    getId()     { return id; }
        public double getSalary() { return salary; }

        public int[] getBonusHistory() {
            return bonusHistory.clone();   // return defensive copy
        }

        @Override public String toString() {
            return "Employee[" + id + "] " + name + " salary=" + salary;
        }
    }

    // ─── Protected visibility in inheritance ───
    static class Base {
        protected String secret = "I am protected";

        protected void show() {
            System.out.println("Base: " + secret);
        }
    }

    static class Derived extends Base {
        public void reveal() {
            System.out.println("Derived accessing protected: " + secret);
            show();   // protected method accessible in subclass
        }
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Access Modifiers =====\n");

        // --- Within same class ---
        System.out.println("--- All modifiers from within same class ---");
        AccessDemo demo = new AccessDemo();
        demo.showAll();

        // --- From subclass (same package) ---
        System.out.println("\n--- From Subclass (same package) ---");
        SubAccessDemo sub = new SubAccessDemo();
        sub.showFromSubclass();
        // sub.privateField  → compile error (not shown, commented)

        // --- Utility class (private constructor) ---
        System.out.println("\n--- Utility Class (private constructor) ---");
        System.out.println("square(7)  = " + MathUtils.square(7));
        System.out.println("cube(4)    = " + MathUtils.cube(4));
        System.out.println("isPrime(17)= " + MathUtils.isPrime(17));
        // new MathUtils(); → compile error (private constructor)

        // --- Immutable class ---
        System.out.println("\n--- Immutable Class ---");
        int[] bonuses = {1000, 2000, 1500};
        ImmutableEmployee emp = new ImmutableEmployee("Alice", 101, 75000, bonuses);
        System.out.println(emp);

        bonuses[0] = 9999;                         // modifying original array
        System.out.println("emp bonus[0] still: " + emp.getBonusHistory()[0]); // 1000

        int[] got = emp.getBonusHistory();
        got[0] = 8888;                              // modifying returned copy
        System.out.println("emp bonus[0] still: " + emp.getBonusHistory()[0]); // 1000

        // --- Protected in inheritance ---
        System.out.println("\n--- Protected Access via Inheritance ---");
        Derived d = new Derived();
        d.reveal();
        System.out.println("Accessing protected from same file: " + d.secret);
    }
}
