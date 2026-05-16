/**
 * ============================================================
 *  TOPIC: final Keyword in Java
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW: 'final' has 3 different uses:
 *
 *  1. final VARIABLE:
 *     - Can be assigned ONLY ONCE.
 *     - Primitive: value cannot change.
 *     - Reference: reference cannot point elsewhere,
 *       but the OBJECT itself can still be mutated!
 *     - Instance final variable: must be initialised in
 *       constructor or declaration.
 *     - static final: compile-time constant.
 *
 *  2. final METHOD:
 *     - Cannot be overridden in subclasses.
 *     - Can still be inherited and called.
 *     - JVM can inline final methods (optimization).
 *
 *  3. final CLASS:
 *     - Cannot be subclassed (extended).
 *     - All methods are implicitly final.
 *     - Examples: String, Integer, Double, Math (JDK classes).
 *     - Used for immutability and security.
 *
 *  Common pattern: final + private + no setters = Immutable field
 * ╚══════════════════════════════════════════════════════════╝
 */
public class FinalKeyword {

    // ═══════════════════════════════════════════════════════
    // 1. FINAL VARIABLES
    // ═══════════════════════════════════════════════════════
    static class FinalVariableDemo {
        // static final = compile-time constant (class-level)
        public static final double PI         = 3.14159265358979;
        public static final int    MAX_RETRY  = 3;
        public static final String APP_NAME   = "MyApp";

        // instance final = must be set in constructor
        private final int    id;
        private final String createdBy;

        public FinalVariableDemo(int id, String createdBy) {
            this.id        = id;
            this.createdBy = createdBy;
            // id = 999;  // ERROR: already assigned
        }

        public void demonstrate() {
            final int localFinal = 42;
            // localFinal = 99;   // ERROR: cannot reassign final local variable

            /*
             * ╔══════════════════════════════════════════╗
             *  IMPORTANT: final REFERENCE ≠ immutable object
             *  The reference can't point elsewhere,
             *  but the object the reference points to CAN change!
             * ╚══════════════════════════════════════════╝
             */
            final int[] arr = {1, 2, 3};
            arr[0] = 99;       // OK: modifying array contents
            // arr = new int[]{4,5,6};  // ERROR: reassigning reference

            final StringBuilder sb = new StringBuilder("Hello");
            sb.append(" World");  // OK: modifying object
            // sb = new StringBuilder("New");  // ERROR

            System.out.println("arr[0]=" + arr[0] + " sb=" + sb);
            System.out.println("id=" + id + " createdBy=" + createdBy);
        }
    }

    // ═══════════════════════════════════════════════════════
    // 2. FINAL METHOD — prevents overriding
    // ═══════════════════════════════════════════════════════
    static class Account {
        protected double balance;

        public Account(double balance) { this.balance = balance; }

        /*
         * ╔══════════════════════════════════════════════╗
         *  final method: subclasses CANNOT override this.
         *  Critical for security/correctness-critical logic
         *  that must not be tampered with by subclasses.
         * ╚══════════════════════════════════════════════╝
         */
        public final boolean deduct(double amount) {
            if (amount <= 0 || amount > balance) return false;
            balance -= amount;
            System.out.printf("Deducted %.2f. Balance: %.2f%n", amount, balance);
            return true;
        }

        // Non-final: subclasses can override
        public String getAccountType() { return "Basic"; }
    }

    static class PremiumAccount extends Account {
        public PremiumAccount(double balance) { super(balance); }

        // Cannot override deduct() — it's final in parent
        // @Override public boolean deduct(double amount) { ... }  // compile error

        @Override public String getAccountType() { return "Premium"; }

        public boolean deductWithCashback(double amount, double cashbackPct) {
            if (deduct(amount)) {   // calling final deduct() — inherited normally
                double cashback = amount * cashbackPct;
                balance += cashback;
                System.out.printf("Cashback of %.2f applied. Balance: %.2f%n", cashback, balance);
                return true;
            }
            return false;
        }
    }

    // ═══════════════════════════════════════════════════════
    // 3. FINAL CLASS — prevents subclassing
    // ═══════════════════════════════════════════════════════
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  final class: cannot be extended.
     *  Use case:
     *  - Security: prevent subclass from overriding sensitive methods
     *  - Immutability guarantee (String is final!)
     *  - Design decision: "This class is not designed for extension"
     * ╚══════════════════════════════════════════════════════╝
     */
    static final class Money {
        private final double   amount;
        private final String   currency;

        public Money(double amount, String currency) {
            if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative");
            this.amount   = amount;
            this.currency = currency;
        }

        public Money add(Money other) {
            if (!this.currency.equals(other.currency))
                throw new IllegalArgumentException("Currency mismatch");
            return new Money(this.amount + other.amount, this.currency);
        }

        public Money subtract(Money other) {
            if (!this.currency.equals(other.currency))
                throw new IllegalArgumentException("Currency mismatch");
            return new Money(this.amount - other.amount, this.currency);
        }

        public Money multiply(double factor) { return new Money(amount * factor, currency); }

        public boolean isGreaterThan(Money other) { return this.amount > other.amount; }

        public double  getAmount()   { return amount; }
        public String  getCurrency() { return currency; }

        @Override public String toString() {
            return String.format("%.2f %s", amount, currency);
        }

        @Override public boolean equals(Object o) {
            if (!(o instanceof Money)) return false;
            Money m = (Money) o;
            return Double.compare(amount, m.amount) == 0 && currency.equals(m.currency);
        }
    }

    // class ExtendMoney extends Money {}  // compile error: Money is final

    // ═══════════════════════════════════════════════════════
    // 4. BLANK FINAL VARIABLE (interesting edge case)
    // ═══════════════════════════════════════════════════════
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  BLANK FINAL: declared but not initialised at declaration.
     *  Must be initialised EXACTLY ONCE before use.
     *  Common for: dependency injection, conditional init.
     * ╚══════════════════════════════════════════════════════╝
     */
    static class BlankFinalDemo {
        private final String mode;

        public BlankFinalDemo(boolean isDev) {
            if (isDev) {
                mode = "DEVELOPMENT";
            } else {
                mode = "PRODUCTION";
            }
            // All paths must assign 'mode' — compiler checks this!
        }

        public String getMode() { return mode; }
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== final Keyword =====\n");

        // --- final variables ---
        System.out.println("--- 1. final Variables ---");
        System.out.println("PI = " + FinalVariableDemo.PI);
        System.out.println("MAX_RETRY = " + FinalVariableDemo.MAX_RETRY);
        FinalVariableDemo fv = new FinalVariableDemo(1, "system");
        fv.demonstrate();

        // --- final method ---
        System.out.println("\n--- 2. final Method ---");
        Account acc = new Account(1000);
        acc.deduct(200);
        acc.deduct(2000);  // fails (more than balance)

        PremiumAccount prem = new PremiumAccount(5000);
        prem.deductWithCashback(1000, 0.05);
        System.out.println("Type: " + prem.getAccountType());

        // --- final class ---
        System.out.println("\n--- 3. final Class (Money) ---");
        Money price   = new Money(99.99, "USD");
        Money tax     = new Money(10.00, "USD");
        Money total   = price.add(tax);
        Money doubled = total.multiply(2);

        System.out.println("price  : " + price);
        System.out.println("tax    : " + tax);
        System.out.println("total  : " + total);
        System.out.println("doubled: " + doubled);
        System.out.println("total > price? " + total.isGreaterThan(price));

        // --- blank final ---
        System.out.println("\n--- 4. Blank Final Variable ---");
        BlankFinalDemo dev  = new BlankFinalDemo(true);
        BlankFinalDemo prod = new BlankFinalDemo(false);
        System.out.println("dev.mode  = " + dev.getMode());
        System.out.println("prod.mode = " + prod.getMode());

        // --- String is final (just FYI) ---
        System.out.println("\n--- 5. String is a final class (immutable) ---");
        String s1 = "Hello";
        String s2 = s1.concat(" World");  // creates NEW string
        System.out.println("s1 = " + s1);  // unchanged
        System.out.println("s2 = " + s2);
    }
}
