/**
 * ============================================================
 *  TOPIC: Polymorphism in Java
 * ============================================================
 *
 * Poly = many, Morph = forms → "many forms"
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW:
 *
 *  A) Compile-Time (Static) Polymorphism:
 *     → Method Overloading
 *     → Binding happens at compile time (early binding)
 *     → Based on: method name + parameter types + count
 *     → Return type ALONE does NOT distinguish overloads
 *
 *  B) Runtime (Dynamic) Polymorphism:
 *     → Method Overriding + Upcasting
 *     → Binding happens at runtime (late binding / dynamic dispatch)
 *     → JVM decides which version to call based on actual object type
 *     → Achieved via: inheritance + @Override + base-type reference
 *
 *  Overloading vs Overriding:
 *  ┌──────────────┬──────────────────┬──────────────────────┐
 *  │              │ Overloading      │ Overriding           │
 *  ├──────────────┼──────────────────┼──────────────────────┤
 *  │ Where        │ Same class       │ Parent + Child class │
 *  │ Signature    │ Different        │ Same (must match)    │
 *  │ Return type  │ Can differ       │ Same (or covariant)  │
 *  │ When         │ Compile time     │ Runtime              │
 *  │ @Override    │ NOT used         │ Should be used       │
 *  │ Access       │ Any              │ Cannot be more priv  │
 *  │ Exceptions   │ Any              │ Cannot throw broader │
 *  └──────────────┴──────────────────┴──────────────────────┘
 *
 *  Rules for Overriding:
 *  1. Method name + parameter list must be IDENTICAL.
 *  2. Return type must be same or COVARIANT (subtype).
 *  3. Access modifier cannot be MORE restrictive.
 *  4. Cannot override final, static, or private methods.
 *  5. Checked exceptions cannot be broader than parent's.
 * ╚══════════════════════════════════════════════════════════╝
 */
public class Polymorphism {

    // ═══════════════════════════════════════════════════════
    // 1. METHOD OVERLOADING (Compile-Time Polymorphism)
    // ═══════════════════════════════════════════════════════
    static class Calculator {
        // Same name, different parameter types
        public int    add(int a, int b)       { return a + b; }
        public double add(double a, double b) { return a + b; }
        public int    add(int a, int b, int c){ return a + b + c; }
        public String add(String a, String b) { return a + b; }  // concatenation

        /*
         * ╔══════════════════════════════════════════════╗
         *  INTERVIEW TIP:
         *  Widening happens automatically in overloading:
         *  add(5, 3) → add(int, int) is preferred over add(double, double).
         *  If no exact match, Java widens: byte→short→int→long→float→double
         *  Autoboxing (int→Integer) happens AFTER widening.
         * ╚══════════════════════════════════════════════╝
         */
        public double multiply(int a, double b)  { return a * b; }
        public double multiply(double a, int b)  { return a * b; }
    }

    // ═══════════════════════════════════════════════════════
    // 2. METHOD OVERRIDING (Runtime Polymorphism)
    // ═══════════════════════════════════════════════════════
    static class Payment {
        protected double amount;

        public Payment(double amount) { this.amount = amount; }

        public void process() {
            System.out.printf("Processing generic payment of $%.2f%n", amount);
        }

        public String getType() { return "Generic"; }
    }

    static class CreditCardPayment extends Payment {
        private String cardNumber;

        public CreditCardPayment(double amount, String cardNumber) {
            super(amount);
            this.cardNumber = cardNumber;
        }

        @Override
        public void process() {
            System.out.printf("[CreditCard] Charging $%.2f to card ending %s%n",
                amount, cardNumber.substring(cardNumber.length() - 4));
        }

        @Override public String getType() { return "CreditCard"; }
    }

    static class UPIPayment extends Payment {
        private String upiId;

        public UPIPayment(double amount, String upiId) {
            super(amount);
            this.upiId = upiId;
        }

        @Override
        public void process() {
            System.out.printf("[UPI] Sending $%.2f to %s%n", amount, upiId);
        }

        @Override public String getType() { return "UPI"; }
    }

    static class CryptoPayment extends Payment {
        private String walletAddress;

        public CryptoPayment(double amount, String wallet) {
            super(amount);
            this.walletAddress = wallet;
        }

        @Override
        public void process() {
            System.out.printf("[Crypto] Transferring $%.2f to wallet %s%n",
                amount, walletAddress.substring(0, 8) + "...");
        }

        @Override public String getType() { return "Crypto"; }
    }

    // Process any payment — runtime polymorphism
    static void checkout(Payment payment) {
        System.out.print("Type: " + payment.getType() + " → ");
        payment.process();   // JVM decides which process() at runtime
    }

    // ═══════════════════════════════════════════════════════
    // 3. COVARIANT RETURN TYPE
    // ═══════════════════════════════════════════════════════
    static class Base {
        public Base getInstance() {
            System.out.println("Base.getInstance()");
            return new Base();
        }
    }

    static class Derived extends Base {
        /*
         * ╔══════════════════════════════════════════════╗
         *  COVARIANT RETURN TYPE (Java 5+):
         *  Overriding method can return a SUBTYPE of the
         *  return type declared in the parent.
         *  Here: Derived is a subtype of Base → valid override.
         * ╚══════════════════════════════════════════════╝
         */
        @Override
        public Derived getInstance() {  // Derived is subtype of Base
            System.out.println("Derived.getInstance()");
            return new Derived();
        }
    }

    // ═══════════════════════════════════════════════════════
    // 4. POLYMORPHISM with interfaces
    // ═══════════════════════════════════════════════════════
    interface Drawable {
        void draw();
        default String getColor() { return "black"; }
    }

    static class DrawableCircle implements Drawable {
        @Override public void draw() { System.out.println("Drawing Circle"); }
    }

    static class DrawableSquare implements Drawable {
        @Override public void draw() { System.out.println("Drawing Square"); }
        @Override public String getColor() { return "red"; }
    }

    static class DrawableTriangle implements Drawable {
        @Override public void draw() { System.out.println("Drawing Triangle"); }
    }

    static void renderAll(Drawable[] shapes) {
        for (Drawable d : shapes) {
            System.out.printf("Color=%s → ", d.getColor());
            d.draw();
        }
    }

    // ═══════════════════════════════════════════════════════
    // 5. VIRTUAL METHOD TABLE (vTable) explanation
    // ═══════════════════════════════════════════════════════
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  HOW RUNTIME POLYMORPHISM WORKS INTERNALLY:
     *  Every class has a Method Table (vtable) at class-load time.
     *  Each object has a reference to its class's vtable.
     *  When virtual method is called through a reference:
     *    1. JVM follows the object's vtable pointer.
     *    2. Looks up the method in the vtable.
     *    3. Calls the correct implementation.
     *  This is why it's called "dynamic dispatch" or "late binding".
     *
     *  In Java ALL non-static, non-final, non-private instance
     *  methods are virtual by default (unlike C++ where you
     *  need the 'virtual' keyword).
     * ╚══════════════════════════════════════════════════════╝
     */

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Polymorphism in Java =====\n");

        // --- Compile-Time (Overloading) ---
        System.out.println("--- 1. Compile-Time Polymorphism (Overloading) ---");
        Calculator calc = new Calculator();
        System.out.println("add(2,3)          = " + calc.add(2, 3));
        System.out.println("add(2.5,3.5)      = " + calc.add(2.5, 3.5));
        System.out.println("add(1,2,3)        = " + calc.add(1, 2, 3));
        System.out.println("add(\"Hi\",\" Java\") = " + calc.add("Hi", " Java"));

        // --- Runtime (Overriding) ---
        System.out.println("\n--- 2. Runtime Polymorphism (Overriding) ---");
        Payment[] payments = {
            new CreditCardPayment(500.0, "1234567890123456"),
            new UPIPayment(200.0, "alice@upi"),
            new CryptoPayment(1000.0, "0xAbCdEf1234567890"),
            new Payment(50.0)
        };

        for (Payment p : payments) checkout(p);

        double total = 0;
        for (Payment p : payments) total += p.amount;
        System.out.printf("Total checkout: $%.2f%n", total);

        // --- Covariant Return ---
        System.out.println("\n--- 3. Covariant Return Type ---");
        Base b = new Derived();
        b.getInstance();   // calls Derived.getInstance() at runtime

        Derived d = new Derived();
        Derived result = d.getInstance();  // no cast needed (covariant)

        // --- Interface Polymorphism ---
        System.out.println("\n--- 4. Interface Polymorphism ---");
        Drawable[] shapes = {
            new DrawableCircle(),
            new DrawableSquare(),
            new DrawableTriangle()
        };
        renderAll(shapes);

        // --- Key polymorphism rule demo ---
        System.out.println("\n--- 5. Reference Type vs Object Type ---");
        Payment p = new CreditCardPayment(100.0, "9999888877776666");
        p.process();       // CreditCardPayment's process() called at runtime
        // p.cardNumber;   // compile error — reference type is Payment
        System.out.println("instanceof CreditCardPayment: " + (p instanceof CreditCardPayment));
    }
}
