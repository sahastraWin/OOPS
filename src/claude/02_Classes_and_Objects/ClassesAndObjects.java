/**
 * ============================================================
 *  TOPIC: Classes and Objects in Java
 * ============================================================
 *
 * CLASS  = blueprint / template (no memory consumed by itself)
 * OBJECT = instance of a class (occupies heap memory)
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW:
 *  - Objects are created on the HEAP in Java.
 *  - Variables (references) are on the STACK.
 *  - 'new' keyword allocates heap memory and calls constructor.
 *  - Garbage Collector reclaims heap memory when no references
 *    exist to an object.
 *  - Java is PASS-BY-VALUE for everything:
 *      * primitives: value itself is copied
 *      * objects: the REFERENCE VALUE is copied (not the object)
 *    This means modifying an object through a passed reference
 *    DOES affect the original object.
 *  - Class members: fields, methods, constructors, blocks,
 *    nested classes.
 *  - Instance members: per object. Static members: per class.
 * ╚══════════════════════════════════════════════════════════╝
 */
public class ClassesAndObjects {

    // ═══════════════════════════════════════════════════════
    // A comprehensive class demonstrating all member types
    // ═══════════════════════════════════════════════════════
    static class BankAccount {

        // ── Instance fields (each object has its own copy) ──
        private String owner;
        private String accountNumber;
        private double balance;

        // ── Static field (shared across ALL objects) ──
        private static int totalAccounts = 0;
        private static final double MIN_BALANCE = 500.0;

        /*
         * ╔══════════════════════════════════════════════╗
         *  STATIC INITIALISER BLOCK:
         *  Runs ONCE when the class is loaded into memory,
         *  before any object is created or static method called.
         *  Used for complex static field initialisation.
         * ╚══════════════════════════════════════════════╝
         */
        static {
            System.out.println("[Static Block] BankAccount class loaded.");
            totalAccounts = 0;
        }

        /*
         * ╔══════════════════════════════════════════════╗
         *  INSTANCE INITIALISER BLOCK:
         *  Runs EVERY TIME a new object is created,
         *  BEFORE the constructor body.
         *  Copied into every constructor by the compiler.
         * ╚══════════════════════════════════════════════╝
         */
        {
            totalAccounts++;
            System.out.println("[Instance Block] Object #" + totalAccounts + " being created.");
        }

        // Constructor
        public BankAccount(String owner, String accNo, double initialDeposit) {
            if (initialDeposit < MIN_BALANCE)
                throw new IllegalArgumentException("Initial deposit must be >= " + MIN_BALANCE);
            this.owner         = owner;
            this.accountNumber = accNo;
            this.balance       = initialDeposit;
        }

        // Instance methods
        public void deposit(double amount) {
            if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
            balance += amount;
            System.out.printf("[%s] Deposited %.2f | New balance: %.2f%n", owner, amount, balance);
        }

        public boolean withdraw(double amount) {
            if (amount <= 0 || balance - amount < MIN_BALANCE) {
                System.out.println("Withdrawal denied. Minimum balance rule.");
                return false;
            }
            balance -= amount;
            System.out.printf("[%s] Withdrew %.2f | New balance: %.2f%n", owner, amount, balance);
            return true;
        }

        public double getBalance()       { return balance; }
        public String getOwner()         { return owner; }
        public String getAccountNumber() { return accountNumber; }

        // Static method — belongs to class, not to any object
        public static int getTotalAccounts() { return totalAccounts; }

        @Override
        public String toString() {
            return String.format("BankAccount[%s, %s, Balance=%.2f]",
                owner, accountNumber, balance);
        }
    }

    // ═══════════════════════════════════════════════════════
    // Demonstrating Pass-by-Value for Objects
    // ═══════════════════════════════════════════════════════
    static class Counter {
        int count = 0;
    }

    /**
     * Java passes the REFERENCE VALUE by value.
     * The method gets a COPY of the reference — but both point
     * to the SAME object. So modifications ARE visible externally.
     */
    static void incrementCounter(Counter c) {
        c.count += 10;   // modifies the actual object
    }

    /**
     * Reassigning the reference inside the method does NOT
     * affect the caller's reference — it only changes the local copy.
     */
    static void tryReassign(Counter c) {
        c = new Counter();  // only local reference changes
        c.count = 999;
    }

    // ═══════════════════════════════════════════════════════
    // Anonymous Object
    // ═══════════════════════════════════════════════════════
    static class Greeter {
        public void greet(String name) {
            System.out.println("Hello, " + name + "!");
        }
    }

    // ═══════════════════════════════════════════════════════
    // Object comparison: == vs equals()
    // ═══════════════════════════════════════════════════════
    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }

        /*
         * ╔══════════════════════════════════════════════╗
         *  INTERVIEW MUST-KNOW: == vs equals()
         *  == compares REFERENCES (memory addresses).
         *  equals() compares CONTENT (if properly overridden).
         *  Always override equals() with hashCode() together!
         *  CONTRACT: if a.equals(b) → a.hashCode() == b.hashCode()
         * ╚══════════════════════════════════════════════╝
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point p = (Point) o;
            return this.x == p.x && this.y == p.y;
        }

        @Override
        public int hashCode() {
            return 31 * x + y;
        }

        @Override
        public String toString() { return "(" + x + ", " + y + ")"; }
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Classes and Objects =====\n");

        // --- Object creation ---
        System.out.println("--- Object Creation ---");
        BankAccount acc1 = new BankAccount("Alice", "ACC001", 1000.0);
        BankAccount acc2 = new BankAccount("Bob",   "ACC002", 2000.0);
        System.out.println("Total accounts: " + BankAccount.getTotalAccounts());

        acc1.deposit(500.0);
        acc2.withdraw(300.0);
        acc1.withdraw(900.0);   // should be denied (min balance)

        System.out.println(acc1);
        System.out.println(acc2);

        // --- Pass by value ---
        System.out.println("\n--- Pass By Value (Objects) ---");
        Counter c = new Counter();
        System.out.println("Before incrementCounter: " + c.count);
        incrementCounter(c);
        System.out.println("After  incrementCounter: " + c.count);

        tryReassign(c);
        System.out.println("After  tryReassign: " + c.count);  // unchanged (still 10)

        // --- Anonymous Object ---
        System.out.println("\n--- Anonymous Object ---");
        new Greeter().greet("Java");  // object used once, then eligible for GC

        // --- == vs equals ---
        System.out.println("\n--- == vs equals() ---");
        Point p1 = new Point(3, 4);
        Point p2 = new Point(3, 4);
        Point p3 = p1;

        System.out.println("p1 == p2        : " + (p1 == p2));      // false (diff refs)
        System.out.println("p1 == p3        : " + (p1 == p3));      // true (same ref)
        System.out.println("p1.equals(p2)   : " + p1.equals(p2));   // true (same content)
        System.out.println("p1.hashCode()   : " + p1.hashCode());
        System.out.println("p2.hashCode()   : " + p2.hashCode());   // same as p1

        // --- null reference ---
        System.out.println("\n--- null Reference ---");
        BankAccount nullAcc = null;
        System.out.println("nullAcc == null : " + (nullAcc == null));
        // nullAcc.deposit(100);  // NullPointerException!
    }
}
