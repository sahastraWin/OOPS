/**
 * ============================================================
 *  TOPIC: Lambda Expressions & Functional Interfaces (Java 8)
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW:
 *
 *  Lambda = anonymous function (no name, no class needed)
 *  Syntax: (params) -> expression
 *          (params) -> { statements; }
 *
 *  Lambda can only be used where a FUNCTIONAL INTERFACE is expected.
 *  Functional Interface = exactly ONE abstract method.
 *
 *  Built-in Functional Interfaces (java.util.function):
 *  ┌───────────────────┬──────────────────┬──────────────────┐
 *  │ Interface         │ Method           │ Description      │
 *  ├───────────────────┼──────────────────┼──────────────────┤
 *  │ Function<T,R>     │ R apply(T t)     │ T → R            │
 *  │ BiFunction<T,U,R> │ R apply(T,U)     │ T,U → R          │
 *  │ Consumer<T>       │ void accept(T)   │ T → void         │
 *  │ BiConsumer<T,U>   │ void accept(T,U) │ T,U → void       │
 *  │ Supplier<T>       │ T get()          │ () → T           │
 *  │ Predicate<T>      │ boolean test(T)  │ T → boolean      │
 *  │ BiPredicate<T,U>  │ boolean test(T,U)│ T,U → boolean    │
 *  │ UnaryOperator<T>  │ T apply(T)       │ T → T            │
 *  │ BinaryOperator<T> │ T apply(T,T)     │ T,T → T          │
 *  │ Runnable          │ void run()       │ () → void        │
 *  └───────────────────┴──────────────────┴──────────────────┘
 *
 *  Method References (shorthand for lambdas):
 *    ClassName::staticMethod        → static method ref
 *    instance::instanceMethod       → bound instance method ref
 *    ClassName::instanceMethod      → unbound instance method ref
 *    ClassName::new                 → constructor reference
 * ╚══════════════════════════════════════════════════════════╝
 */

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class LambdaAndFunctional {

    // ═══════════════════════════════════════════════════════
    // 1. LAMBDA BASICS
    // ═══════════════════════════════════════════════════════
    @FunctionalInterface
    interface Transformer<T> {
        T transform(T input);

        // default method doesn't break functional interface
        default Transformer<T> andThen(Transformer<T> after) {
            return input -> after.transform(this.transform(input));
        }
    }

    static void lambdaBasics() {
        System.out.println("--- 1. Lambda Basics ---");

        // Before Java 8 (anonymous class)
        Runnable r1 = new Runnable() {
            @Override public void run() { System.out.println("Anonymous class Runnable"); }
        };

        // Java 8 lambda (same thing, concise)
        Runnable r2 = () -> System.out.println("Lambda Runnable");

        r1.run();
        r2.run();

        // Lambda with parameters
        Comparator<String> byLength = (a, b) -> Integer.compare(a.length(), b.length());
        List<String> words = new ArrayList<>(Arrays.asList("banana", "fig", "apple", "kiwi"));
        words.sort(byLength);
        System.out.println("Sorted by length: " + words);

        // Custom functional interface
        Transformer<String> upper  = String::toUpperCase;
        Transformer<String> trim   = String::trim;
        Transformer<String> exclaim = s -> s + "!";
        Transformer<String> pipeline = trim.andThen(upper).andThen(exclaim);
        System.out.println(pipeline.transform("  hello world  "));
    }

    // ═══════════════════════════════════════════════════════
    // 2. BUILT-IN FUNCTIONAL INTERFACES
    // ═══════════════════════════════════════════════════════
    static void builtinInterfaces() {
        System.out.println("\n--- 2. Built-in Functional Interfaces ---");

        // Function<T,R> — T → R
        Function<String, Integer> length  = String::length;
        Function<String, String>  shout   = s -> s.toUpperCase() + "!!!";
        Function<Integer, Integer> square = x -> x * x;

        System.out.println("length(\"Hello\") = " + length.apply("Hello"));
        System.out.println("shout(\"hello\") = " + shout.apply("hello"));

        // Function composition: andThen, compose
        Function<Integer, String> squareThenToStr = square.andThen(Object::toString);
        System.out.println("square then toString(5) = " + squareThenToStr.apply(5));

        Function<String, Integer> pipeline = length.andThen(square);
        System.out.println("length then square(\"Java\") = " + pipeline.apply("Java"));

        // Consumer<T> — T → void
        Consumer<String> print   = System.out::println;
        Consumer<String> log     = s -> System.out.println("[LOG] " + s);
        Consumer<String> both    = print.andThen(log);
        both.accept("Testing consumer chain");

        // Supplier<T> — () → T
        Supplier<List<String>> listFactory = ArrayList::new;
        Supplier<Double>       random      = Math::random;
        Supplier<String>       greeting    = () -> "Hello, World!";
        System.out.println("Supplier greeting: " + greeting.get());
        System.out.printf("Random: %.4f%n", random.get());
        List<String> newList = listFactory.get();
        newList.add("supplied!"); System.out.println("Supplied list: " + newList);

        // Predicate<T> — T → boolean
        Predicate<String>  notEmpty    = s -> !s.isEmpty();
        Predicate<String>  startWithA  = s -> s.startsWith("A");
        Predicate<String>  lenGt3      = s -> s.length() > 3;
        Predicate<Integer> isEven      = n -> n % 2 == 0;
        Predicate<Integer> isPositive  = n -> n > 0;

        // Predicate composition
        Predicate<String> validName = notEmpty.and(startWithA).and(lenGt3);
        System.out.println("validName(\"Alice\") = " + validName.test("Alice"));
        System.out.println("validName(\"Al\")    = " + validName.test("Al"));
        System.out.println("validName(\"Bob\")   = " + validName.test("Bob"));

        Predicate<Integer> evenAndPositive = isEven.and(isPositive);
        Predicate<Integer> evenOrPositive  = isEven.or(isPositive);
        System.out.println("evenAndPositive(-4) = " + evenAndPositive.test(-4));
        System.out.println("evenOrPositive (-3) = " + evenOrPositive.test(-3));

        // UnaryOperator<T>: T → T (special Function)
        UnaryOperator<String> trim  = String::trim;
        UnaryOperator<String> upper = String::toUpperCase;
        System.out.println(trim.andThen(upper).apply("  java  "));

        // BinaryOperator<T>: (T,T) → T
        BinaryOperator<Integer> max   = (a, b) -> a > b ? a : b;
        BinaryOperator<String>  concat = (a, b) -> a + " " + b;
        System.out.println("max(10,7) = " + max.apply(10, 7));
        System.out.println("concat = " + concat.apply("Hello", "Java"));

        // BiFunction<T,U,R>
        BiFunction<String, Integer, String> repeatStr = String::repeat;
        System.out.println("repeat(\"ab\", 3) = " + repeatStr.apply("ab", 3));
    }

    // ═══════════════════════════════════════════════════════
    // 3. METHOD REFERENCES
    // ═══════════════════════════════════════════════════════
    static class MathOps {
        public static int doubleIt(int n)  { return n * 2; }
        public        int tripleIt(int n)  { return n * 3; }
    }

    static void methodReferences() {
        System.out.println("\n--- 3. Method References ---");

        // 1. Static method reference: ClassName::staticMethod
        Function<Integer, Integer> doubler = MathOps::doubleIt;
        System.out.println("Static ref doubleIt(5) = " + doubler.apply(5));

        // 2. Bound instance method ref: instance::method
        MathOps obj = new MathOps();
        Function<Integer, Integer> tripler = obj::tripleIt;
        System.out.println("Bound instance ref tripleIt(4) = " + tripler.apply(4));

        // 3. Unbound instance method ref: ClassName::instanceMethod
        Function<String, String> upper = String::toUpperCase;
        Function<String, Integer> len  = String::length;
        System.out.println("Unbound upper(\"java\") = " + upper.apply("java"));

        // 4. Constructor reference: ClassName::new
        Function<String, StringBuilder> sbFactory = StringBuilder::new;
        BiFunction<String, Integer, String> subStr = String::substring;
        StringBuilder sb = sbFactory.apply("Initial");
        System.out.println("Constructor ref: " + sb);
        System.out.println("subStr(\"Hello World\", 6) = " + subStr.apply("Hello World", 6));

        // Method references with collections
        List<String> names = Arrays.asList("Charlie", "Alice", "Bob", "Diana");
        names.sort(String::compareToIgnoreCase);   // unbound
        names.forEach(System.out::println);        // bound (to System.out)
    }

    // ═══════════════════════════════════════════════════════
    // 4. CLOSURES — variable capture in lambdas
    // ═══════════════════════════════════════════════════════
    static void closureDemo() {
        System.out.println("\n--- 4. Closures (Variable Capture) ---");

        int base = 10;   // effectively final
        Function<Integer, Integer> addBase = x -> x + base;
        // base = 20;  // ERROR: would make base not effectively final

        System.out.println("addBase(5) = " + addBase.apply(5));

        // Closure in loop — common gotcha
        List<Supplier<Integer>> suppliers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            final int captured = i;   // must capture as effectively final
            suppliers.add(() -> captured);
        }
        System.out.print("Captured loop values: ");
        suppliers.forEach(s -> System.out.print(s.get() + " "));
        System.out.println();

        /*
         * ╔══════════════════════════════════════════════╗
         *  LAMBDA GOTCHA: lambdas cannot modify local variables
         *  from the enclosing scope (must be effectively final).
         *  But they CAN modify object state via the reference.
         * ╚══════════════════════════════════════════════╝
         */
        int[] counter = {0};   // trick: use array to "mutate" in lambda
        Runnable incrementer = () -> counter[0]++;
        incrementer.run(); incrementer.run(); incrementer.run();
        System.out.println("Counter after 3 runs: " + counter[0]);
    }

    // ═══════════════════════════════════════════════════════
    // 5. REAL-WORLD PIPELINE EXAMPLE
    // ═══════════════════════════════════════════════════════
    static class Order {
        String customer; String product; double amount; boolean paid;
        Order(String c, String p, double a, boolean paid) {
            this.customer=c; this.product=p; this.amount=a; this.paid=paid;
        }
        @Override public String toString() {
            return customer + "/" + product + "/$" + amount + (paid?"/PAID":"/UNPAID");
        }
    }

    static void pipelineDemo() {
        System.out.println("\n--- 5. Real-world Functional Pipeline ---");

        List<Order> orders = Arrays.asList(
            new Order("Alice",   "Laptop",  999.99, true),
            new Order("Bob",     "Phone",   499.50, false),
            new Order("Charlie", "Tablet",  349.99, true),
            new Order("Alice",   "Monitor", 299.99, true),
            new Order("Bob",     "Keyboard", 79.99, false)
        );

        Predicate<Order>          isPaid        = o -> o.paid;
        Predicate<Order>          isExpensive   = o -> o.amount > 300;
        Function<Order, String>   formatOrder   = o -> o.customer + ": $" + o.amount;
        Consumer<String>          printSummary  = System.out::println;

        System.out.println("Paid orders over $300:");
        orders.stream()
            .filter(isPaid.and(isExpensive))
            .map(formatOrder)
            .forEach(printSummary);

        // Aggregate with BinaryOperator
        BinaryOperator<Double> sum = Double::sum;
        double totalRevenue = orders.stream()
            .filter(isPaid)
            .map(o -> o.amount)
            .reduce(0.0, sum);
        System.out.printf("Total paid revenue: $%.2f%n", totalRevenue);
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Lambda & Functional Interfaces =====\n");
        lambdaBasics();
        builtinInterfaces();
        methodReferences();
        closureDemo();
        pipelineDemo();
    }
}
