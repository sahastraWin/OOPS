/**
 * ============================================================
 *  TOPIC: Interfaces in Java
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW:
 *
 *  Interface = Pure contract defining WHAT a class must do,
 *  not HOW. A class "implements" an interface.
 *
 *  Interface member rules:
 *  - Variables   : implicitly public static final (constants)
 *  - Methods     : implicitly public abstract (pre Java 8)
 *  - default     : concrete method in interface (Java 8+)
 *  - static      : static method in interface (Java 8+)
 *  - private     : private helper methods (Java 9+)
 *
 *  Key points:
 *  - A class can implement MULTIPLE interfaces (solves no-multiple-inheritance)
 *  - An interface can extend MULTIPLE interfaces
 *  - Interfaces CAN'T be instantiated
 *  - Interfaces CAN have reference variables
 *  - Marker interface: no methods (Serializable, Cloneable)
 *  - Functional interface: exactly one abstract method (Java 8)
 *    Used with lambdas → @FunctionalInterface annotation
 *
 *  Diamond problem with interfaces:
 *  If two interfaces have same default method → implementing class
 *  MUST override it to resolve ambiguity.
 * ╚══════════════════════════════════════════════════════════╝
 */
public class Interfaces {

    // ═══════════════════════════════════════════════════════
    // 1. BASIC INTERFACE + MULTIPLE IMPLEMENTATION
    // ═══════════════════════════════════════════════════════
    interface Flyable {
        int MAX_ALTITUDE = 40000;  // implicitly public static final

        void fly();
        void land();

        // Default method (Java 8)
        default void describe() {
            System.out.println("I can fly up to " + MAX_ALTITUDE + " feet.");
        }
    }

    interface Swimmable {
        void swim();
        default void describe() {
            System.out.println("I can swim.");
        }
    }

    interface Runnable2 {   // renamed to avoid java.lang.Runnable conflict
        void run();
    }

    // Duck implements multiple interfaces
    static class Duck implements Flyable, Swimmable, Runnable2 {
        private String name;
        public Duck(String name) { this.name = name; }

        @Override public void fly()  { System.out.println(name + " is flying low."); }
        @Override public void land() { System.out.println(name + " landed on water."); }
        @Override public void swim() { System.out.println(name + " is swimming."); }
        @Override public void run()  { System.out.println(name + " is waddling."); }

        /*
         * ╔══════════════════════════════════════════════╗
         *  DIAMOND PROBLEM RESOLUTION:
         *  Both Flyable and Swimmable have a default describe().
         *  Duck MUST override to resolve the conflict.
         *  Can call specific interface's version using:
         *  InterfaceName.super.method()
         * ╚══════════════════════════════════════════════╝
         */
        @Override public void describe() {
            Flyable.super.describe();    // call Flyable's version
            Swimmable.super.describe();  // call Swimmable's version
            System.out.println("I am a duck that does both!");
        }
    }

    // ═══════════════════════════════════════════════════════
    // 2. INTERFACE EXTENDING MULTIPLE INTERFACES
    // ═══════════════════════════════════════════════════════
    interface Readable   { String read(); }
    interface Writable   { void write(String data); }
    interface ReadWritable extends Readable, Writable {
        void seek(int position);
    }

    static class FileStream implements ReadWritable {
        private StringBuilder content = new StringBuilder();
        private int position = 0;

        @Override public String read() { return content.toString(); }
        @Override public void write(String data) { content.append(data); }
        @Override public void seek(int pos) { this.position = pos; }
    }

    // ═══════════════════════════════════════════════════════
    // 3. FUNCTIONAL INTERFACE + LAMBDA
    // ═══════════════════════════════════════════════════════
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  FUNCTIONAL INTERFACE:
     *  Exactly ONE abstract method.
     *  @FunctionalInterface annotation is optional but
     *  causes a compile error if the contract is violated.
     *  Can have default and static methods.
     *  Used with lambda expressions and method references.
     * ╚══════════════════════════════════════════════════════╝
     */
    @FunctionalInterface
    interface MathOperation {
        double operate(double a, double b);

        // Default method — does NOT count toward the one abstract
        default MathOperation andThen(MathOperation other) {
            return (a, b) -> other.operate(this.operate(a, b), 0);
        }
    }

    @FunctionalInterface
    interface StringTransformer {
        String transform(String input);
    }

    @FunctionalInterface
    interface Validator<T> {
        boolean validate(T value);
        default Validator<T> and(Validator<T> other) {
            return value -> this.validate(value) && other.validate(value);
        }
    }

    // ═══════════════════════════════════════════════════════
    // 4. MARKER INTERFACE
    // ═══════════════════════════════════════════════════════
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  MARKER INTERFACE: No methods — just marks a class.
     *  JVM or frameworks check 'instanceof MarkerInterface'
     *  to decide behaviour.
     *  Examples in JDK: Serializable, Cloneable, Remote
     *  Modern alternative: annotations (@Serializable, etc.)
     * ╚══════════════════════════════════════════════════════╝
     */
    interface Persistable {}   // marker interface

    static class UserEntity implements Persistable {
        String username;
        UserEntity(String u) { this.username = u; }
    }

    static class TempObject {}  // NOT persistable

    static void saveToDatabase(Object obj) {
        if (obj instanceof Persistable) {
            System.out.println("Saving " + obj.getClass().getSimpleName() + " to DB.");
        } else {
            System.out.println(obj.getClass().getSimpleName() + " is NOT persistable. Skipped.");
        }
    }

    // ═══════════════════════════════════════════════════════
    // 5. INTERFACE with STATIC methods (Java 8+)
    // ═══════════════════════════════════════════════════════
    interface Converter<F, T> {
        T convert(F from);

        // Static factory method in interface
        static Converter<String, Integer> stringToInt() {
            return Integer::parseInt;
        }

        static Converter<String, Double> stringToDouble() {
            return Double::parseDouble;
        }

        // Default composition
        default <V> Converter<F, V> andThen(Converter<T, V> after) {
            return f -> after.convert(this.convert(f));
        }
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Interfaces in Java =====\n");

        // --- Multiple interface implementation ---
        System.out.println("--- 1. Multiple Interfaces ---");
        Duck donald = new Duck("Donald");
        donald.fly();
        donald.swim();
        donald.run();
        donald.describe();   // overridden to resolve diamond

        // Using interface references (polymorphism)
        Flyable f   = donald;
        Swimmable s = donald;
        f.fly();
        s.swim();
        System.out.println("MAX_ALTITUDE: " + Flyable.MAX_ALTITUDE);

        // --- Interface extending interfaces ---
        System.out.println("\n--- 2. Interface Extending Interfaces ---");
        ReadWritable rw = new FileStream();
        rw.write("Hello ");
        rw.write("World");
        System.out.println("Read: " + rw.read());

        // --- Functional Interface + Lambda ---
        System.out.println("\n--- 3. Functional Interface + Lambda ---");
        MathOperation add = (a, b) -> a + b;
        MathOperation mul = (a, b) -> a * b;
        MathOperation sub = (a, b) -> a - b;

        System.out.println("add(5, 3)  = " + add.operate(5, 3));
        System.out.println("mul(4, 7)  = " + mul.operate(4, 7));
        System.out.println("sub(10, 4) = " + sub.operate(10, 4));

        StringTransformer upper = String::toUpperCase;
        StringTransformer trim  = String::trim;
        StringTransformer chain = s2 -> upper.transform(trim.transform(s2));
        System.out.println(chain.transform("  hello world  "));

        Validator<String> notEmpty   = s2 -> !s2.isEmpty();
        Validator<String> minLen5    = s2 -> s2.length() >= 5;
        Validator<String> combined   = notEmpty.and(minLen5);
        System.out.println("Validate 'Hi'     : " + combined.validate("Hi"));
        System.out.println("Validate 'Hello!' : " + combined.validate("Hello!"));

        // --- Marker Interface ---
        System.out.println("\n--- 4. Marker Interface ---");
        saveToDatabase(new UserEntity("alice"));
        saveToDatabase(new TempObject());

        // --- Static methods in interface ---
        System.out.println("\n--- 5. Static Interface Methods ---");
        Converter<String, Integer> toInt = Converter.stringToInt();
        System.out.println("\"42\" → " + toInt.convert("42"));

        Converter<String, Double> toDouble = Converter.stringToDouble();
        System.out.println("\"3.14\" → " + toDouble.convert("3.14"));

        // Compose converters
        Converter<String, String> pipeline = Converter.stringToInt()
            .andThen(i -> i * i)
            .andThen(Object::toString);
        System.out.println("\"9\" → squared → " + pipeline.convert("9"));
    }
}
