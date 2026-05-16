/**
 * ============================================================
 *  TOPIC: Varargs and Covariant Return Types in Java
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW: Varargs (Variable Arguments)
 *
 *  - Syntax: void method(Type... args)
 *  - Internally treated as an ARRAY: Type[] args
 *  - Must be the LAST parameter in the method signature
 *  - Only ONE varargs parameter per method
 *  - Can be called with zero or more arguments
 *  - Overloaded method with exact params is preferred over varargs
 *  - @SafeVarargs suppresses heap-pollution warnings for generics
 *
 *  INTERVIEW MUST-KNOW: Covariant Return Type (Java 5+)
 *  - Overriding method can return a SUBTYPE of the parent's return type
 *  - Example: if parent returns Animal, child can return Dog
 *  - Makes code cleaner — no explicit cast needed by caller
 *  - Works only for OBJECT return types (not primitives)
 * ╚══════════════════════════════════════════════════════════╝
 */

import java.util.*;

public class VarargsAndCovariant {

    // ═══════════════════════════════════════════════════════
    // 1. VARARGS — basics
    // ═══════════════════════════════════════════════════════
    static class MathHelper {

        // Basic varargs — treated as int[] internally
        public static int sum(int... numbers) {
            int total = 0;
            for (int n : numbers) total += n;
            return total;
        }

        // Varargs with other parameters — varargs MUST be last
        public static double weightedAverage(double weight, double... values) {
            if (values.length == 0) return 0;
            double total = 0;
            for (double v : values) total += v * weight;
            return total / values.length;
        }

        // String formatting with varargs
        public static String format(String template, Object... args) {
            return String.format(template, args);
        }

        /*
         * ╔══════════════════════════════════════════════╗
         *  OVERLOADING with varargs:
         *  If an exact-match overload exists, it is PREFERRED.
         *  sum(1, 2) → calls sum(int, int) NOT sum(int...)
         *  This avoids unnecessary array creation.
         * ╚══════════════════════════════════════════════╝
         */
        public static int sum(int a, int b) {
            System.out.println("  [exact overload called]");
            return a + b;
        }

        // Generic varargs (produces heap pollution warning without @SafeVarargs)
        @SafeVarargs
        public static <T> List<T> listOf(T... items) {
            List<T> result = new ArrayList<>();
            for (T item : items) result.add(item);
            return result;
        }
    }

    // ═══════════════════════════════════════════════════════
    // 2. VARARGS in constructor
    // ═══════════════════════════════════════════════════════
    static class Playlist {
        private String   name;
        private String[] songs;

        // Varargs constructor
        public Playlist(String name, String... songs) {
            this.name  = name;
            this.songs = songs;   // directly assigned — same array
        }

        public void display() {
            System.out.println("Playlist: " + name);
            for (int i = 0; i < songs.length; i++)
                System.out.println("  " + (i+1) + ". " + songs[i]);
        }

        public int size() { return songs.length; }
    }

    // ═══════════════════════════════════════════════════════
    // 3. COVARIANT RETURN TYPES
    // ═══════════════════════════════════════════════════════
    static class Animal {
        protected String name;
        public Animal(String name) { this.name = name; }

        /*
         * ╔══════════════════════════════════════════════╗
         *  Parent returns Animal from clone().
         *  Child overrides to return Dog (subtype).
         *  Covariant return: caller of Dog.clone() gets
         *  a Dog reference directly — no cast required.
         * ╚══════════════════════════════════════════════╝
         */
        public Animal clone() {
            System.out.println("[Animal.clone] creating Animal copy");
            return new Animal(name);
        }

        public Animal getInstance() {
            return new Animal("generic");
        }

        @Override public String toString() { return "Animal(" + name + ")"; }
    }

    static class Dog extends Animal {
        private String breed;

        public Dog(String name, String breed) {
            super(name);
            this.breed = breed;
        }

        // Covariant return: returns Dog instead of Animal
        @Override
        public Dog clone() {
            System.out.println("[Dog.clone] creating Dog copy");
            return new Dog(name, breed);   // returns Dog, not Animal
        }

        // Covariant return in factory method
        @Override
        public Dog getInstance() {
            return new Dog("default", "Mixed");
        }

        public void bark() { System.out.println(name + ": Woof!"); }

        @Override public String toString() { return "Dog(" + name + "," + breed + ")"; }
    }

    static class GoldenRetriever extends Dog {
        public GoldenRetriever(String name) { super(name, "Golden Retriever"); }

        // Covariant return chain — returns GoldenRetriever
        @Override
        public GoldenRetriever clone() {
            System.out.println("[GoldenRetriever.clone] creating GoldenRetriever copy");
            return new GoldenRetriever(name);
        }

        public void guide() { System.out.println(name + " is guiding!"); }
        @Override public String toString() { return "GoldenRetriever(" + name + ")"; }
    }

    // ═══════════════════════════════════════════════════════
    // 4. COVARIANT RETURN in Builder / fluent chains
    // ═══════════════════════════════════════════════════════
    static class BaseBuilder<T extends BaseBuilder<T>> {
        protected String id;
        protected String name;

        @SuppressWarnings("unchecked")
        public T id(String id)     { this.id = id;     return (T) this; }

        @SuppressWarnings("unchecked")
        public T name(String name) { this.name = name; return (T) this; }

        public String build() { return "Entity(id=" + id + ", name=" + name + ")"; }
    }

    static class UserBuilder extends BaseBuilder<UserBuilder> {
        private String email;
        private int    age;

        public UserBuilder email(String email) { this.email = email; return this; }
        public UserBuilder age(int age)        { this.age   = age;   return this; }

        @Override
        public String build() {
            return "User(id=" + id + ", name=" + name
                + ", email=" + email + ", age=" + age + ")";
        }
    }

    static class ProductBuilder extends BaseBuilder<ProductBuilder> {
        private double price;
        private String category;

        public ProductBuilder price(double p)    { this.price    = p; return this; }
        public ProductBuilder category(String c) { this.category = c; return this; }

        @Override
        public String build() {
            return "Product(id=" + id + ", name=" + name
                + ", price=" + price + ", cat=" + category + ")";
        }
    }

    // ═══════════════════════════════════════════════════════
    // 5. VARARGS PITFALLS
    // ═══════════════════════════════════════════════════════
    static void varargsPitfalls() {
        System.out.println("\n--- 5. Varargs Pitfalls ---");

        /*
         * ╔══════════════════════════════════════════════╗
         *  PITFALL 1: Passing null to varargs
         *  method(null) is ambiguous — null can be an array or single element.
         *  Explicit cast resolves: method((String[]) null) → args is null array
         *  Or: method((String) null)  → args = {null} (length 1)
         * ╚══════════════════════════════════════════════╝
         */
        printAll((Object) null);        // prints "null" (single element)
        // printAll((Object[]) null);   // NullPointerException in loop

        /*
         * ╔══════════════════════════════════════════════╗
         *  PITFALL 2: Performance — each call creates a new array
         *  If the method is called very frequently in a hot loop,
         *  consider providing explicit-parameter overloads for
         *  common argument counts (1, 2, 3) to avoid array creation.
         * ╚══════════════════════════════════════════════╝
         */
        System.out.println("sum() zero args: " + MathHelper.sum());   // 0
        System.out.println("sum(5) one arg: "  + MathHelper.sum(5));  // 5

        /*
         * ╔══════════════════════════════════════════════╗
         *  PITFALL 3: Generic varargs → heap pollution
         *  List<String>... lists  — compiler warns unless @SafeVarargs
         *  The JVM cannot check generic type at runtime due to erasure.
         * ╚══════════════════════════════════════════════╝
         */
        List<String>  strList  = MathHelper.listOf("a", "b", "c");
        List<Integer> intList  = MathHelper.listOf(1, 2, 3);
        System.out.println("Generic varargs list: " + strList);
        System.out.println("Generic varargs list: " + intList);
    }

    static void printAll(Object... items) {
        System.out.print("printAll: ");
        if (items == null) { System.out.println("null array"); return; }
        for (Object o : items) System.out.print(o + " ");
        System.out.println();
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Varargs and Covariant Return Types =====\n");

        // --- Varargs basics ---
        System.out.println("--- 1. Varargs Basics ---");
        System.out.println("sum()          = " + MathHelper.sum());
        System.out.println("sum(5)         = " + MathHelper.sum(5));
        System.out.println("sum(1,2,3)     = " + MathHelper.sum(1, 2, 3));
        System.out.println("sum(1..5)      = " + MathHelper.sum(1, 2, 3, 4, 5));

        // Pass an array to varargs
        int[] arr = {10, 20, 30, 40};
        // MathHelper.sum(arr);  // does NOT work with int[] directly (primitive array)
        System.out.println("sum(1,2) calls exact overload:");
        System.out.println("sum(1,2) = " + MathHelper.sum(1, 2));  // exact overload

        System.out.println("weightedAverage(0.5, 80,90,100) = " +
            MathHelper.weightedAverage(0.5, 80, 90, 100));

        System.out.println(MathHelper.format("Name: %s Age: %d GPA: %.1f", "Alice", 22, 9.5));

        // --- Varargs in constructor ---
        System.out.println("\n--- 2. Varargs Constructor ---");
        Playlist p1 = new Playlist("Morning Jams", "Song A", "Song B", "Song C");
        Playlist p2 = new Playlist("Empty");    // zero varargs
        p1.display();
        System.out.println("Empty playlist size: " + p2.size());

        // --- Covariant Return ---
        System.out.println("\n--- 3. Covariant Return Types ---");

        Animal a = new Animal("Generic");
        Animal aCopy = a.clone();           // returns Animal
        System.out.println("Animal clone: " + aCopy);

        Dog d = new Dog("Rex", "Labrador");
        Dog dCopy = d.clone();              // returns Dog directly — no cast needed!
        System.out.println("Dog clone: " + dCopy);
        dCopy.bark();                       // can call bark() without cast

        GoldenRetriever gr = new GoldenRetriever("Goldie");
        GoldenRetriever grCopy = gr.clone(); // returns GoldenRetriever
        System.out.println("GoldenRetriever clone: " + grCopy);
        grCopy.guide();

        // Without covariant return, caller would need:
        // Dog d2 = (Dog) animal.clone();  // ugly explicit cast

        // Covariant return through base reference
        Animal animalRef = d;
        Animal clonedViaBase = animalRef.clone();  // Dog.clone() still called at runtime
        System.out.println("Clone via base ref: " + clonedViaBase.getClass().getSimpleName());

        // --- Covariant return in Builder ---
        System.out.println("\n--- 4. Covariant Builder Pattern ---");
        String user = new UserBuilder()
            .id("U001")
            .name("Alice")
            .email("alice@example.com")
            .age(28)
            .build();
        System.out.println(user);

        String product = new ProductBuilder()
            .id("P001")
            .name("Laptop")
            .price(999.99)
            .category("Electronics")
            .build();
        System.out.println(product);

        // --- Pitfalls ---
        varargsPitfalls();

        // --- printAll examples ---
        System.out.println("\n--- 6. printAll varargs examples ---");
        printAll("Java", "Python", "Go", "Rust");
        printAll(1, 2.5, true, 'x');
        printAll();   // zero args — empty array, not null
    }
}
