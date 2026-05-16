/**
 * ============================================================
 *  TOPIC: 'this' and 'super' Keywords in Java
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW: 'this' keyword
 *  1. Refers to the CURRENT OBJECT inside an instance method.
 *  2. Disambiguates instance variable from local param (this.x = x)
 *  3. Calls another constructor of the SAME class: this(args)
 *     → Must be the FIRST statement in a constructor.
 *  4. Passes current object as argument to another method.
 *  5. Returns the current object from a method (method chaining).
 *  6. Cannot use 'this' in a static context (no current object).
 *
 *  INTERVIEW MUST-KNOW: 'super' keyword
 *  1. Refers to the PARENT class.
 *  2. Access parent class's field hidden by child: super.field
 *  3. Call parent class's overridden method: super.method()
 *  4. Call parent class constructor: super(args)
 *     → Must be the FIRST statement in a constructor.
 *  5. super() is implicitly called if not written explicitly
 *     (calls the no-arg parent constructor).
 *
 *  this() vs super():
 *  - Both must be first statement → cannot use both in same constructor.
 *  - this() calls same-class constructor.
 *  - super() calls parent-class constructor.
 * ╚══════════════════════════════════════════════════════════╝
 */
public class ThisAndSuper {

    // ═══════════════════════════════════════════════════════
    // 1. 'this' — disambiguation + method chaining
    // ═══════════════════════════════════════════════════════
    static class Person {
        private String name;
        private int    age;
        private String email;

        public Person() { this("Unknown", 0); }  // this() → constructor chaining

        public Person(String name, int age) {
            this(name, age, "");
        }

        public Person(String name, int age, String email) {
            /*
             * ╔══════════════════════════════════════════╗
             *  'this.name' = instance field
             *  'name'      = local parameter
             *  Without 'this', parameter shadows the field.
             * ╚══════════════════════════════════════════╝
             */
            this.name  = name;   // instance field = parameter
            this.age   = age;
            this.email = email;
        }

        // Setters return 'this' for METHOD CHAINING (Fluent API)
        public Person setName(String name)   { this.name = name; return this; }
        public Person setAge(int age)        { this.age = age;   return this; }
        public Person setEmail(String email) { this.email = email; return this; }

        // Pass 'this' to another method
        public void register() { PersonService.register(this); }

        public String getName()  { return name; }
        public int    getAge()   { return age; }
        public String getEmail() { return email; }

        @Override public String toString() {
            return "Person(" + name + ", " + age + ", " + email + ")";
        }
    }

    static class PersonService {
        public static void register(Person p) {
            // 'p' is the object that called p.register() via 'this'
            System.out.println("Registering: " + p);
        }
    }

    // ═══════════════════════════════════════════════════════
    // 2. 'super' — field shadowing + method override access
    // ═══════════════════════════════════════════════════════
    static class Animal {
        String name = "Animal";       // instance field

        public Animal() {
            System.out.println("[Animal no-arg constructor]");
        }

        public Animal(String name) {
            this.name = name;
            System.out.println("[Animal parameterised constructor] " + name);
        }

        public void eat() {
            System.out.println(name + " (Animal) is eating generic food.");
        }

        public void breathe() {
            System.out.println("Breathing air.");
        }
    }

    static class Dog extends Animal {
        String name = "Dog";          // shadows Animal.name

        public Dog() {
            super("Buddy");           // calls Animal(String) constructor
            System.out.println("[Dog constructor]");
        }

        public Dog(String name) {
            super(name);
            System.out.println("[Dog constructor with name]");
        }

        @Override
        public void eat() {
            super.eat();              // calls Animal's eat() first
            System.out.println(name + " (Dog) prefers dog food.");
        }

        public void showNames() {
            System.out.println("this.name  (Dog)    = " + this.name);
            System.out.println("super.name (Animal) = " + super.name);
        }
    }

    static class GoldenRetriever extends Dog {
        public GoldenRetriever() {
            super("Goldie");          // calls Dog(String) → Dog calls super(name) → Animal(String)
            System.out.println("[GoldenRetriever constructor]");
        }

        @Override
        public void eat() {
            super.eat();             // calls Dog's eat() (which calls Animal's eat() too)
            System.out.println("...and loves treats!");
        }
    }

    // ═══════════════════════════════════════════════════════
    // 3. super for accessing hidden static field (method hiding)
    // ═══════════════════════════════════════════════════════
    static class Base {
        static String type = "Base";
        int           value = 10;

        public void display() {
            System.out.println("Base.display() type=" + type + " value=" + value);
        }
    }

    static class Derived extends Base {
        static String type = "Derived";  // hides Base.type
        int           value = 20;        // hides Base.value

        @Override
        public void display() {
            System.out.println("Derived.display() type=" + type + " value=" + value);
            System.out.println("super type=" + Base.type + " super value=" + super.value);
            super.display();   // calls Base.display()
        }
    }

    // ═══════════════════════════════════════════════════════
    // 4. Builder pattern using 'this' return
    // ═══════════════════════════════════════════════════════
    static class QueryBuilder {
        private String  table;
        private String  condition;
        private int     limit = -1;
        private String  orderBy;
        private boolean distinct;

        public QueryBuilder from(String table) {
            this.table = table;
            return this;                 // return 'this' for chaining
        }

        public QueryBuilder where(String condition) {
            this.condition = condition;
            return this;
        }

        public QueryBuilder limit(int n) {
            this.limit = n;
            return this;
        }

        public QueryBuilder orderBy(String col) {
            this.orderBy = col;
            return this;
        }

        public QueryBuilder distinct() {
            this.distinct = true;
            return this;
        }

        public String build() {
            StringBuilder sb = new StringBuilder("SELECT ");
            if (distinct) sb.append("DISTINCT ");
            sb.append("* FROM ").append(table);
            if (condition != null) sb.append(" WHERE ").append(condition);
            if (orderBy  != null) sb.append(" ORDER BY ").append(orderBy);
            if (limit     >= 0)   sb.append(" LIMIT ").append(limit);
            return sb.toString();
        }
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== this and super Keywords =====\n");

        // --- 'this' disambiguation ---
        System.out.println("--- 1. 'this' (disambiguation + chaining + passing) ---");
        Person p = new Person();               // this() chain
        System.out.println("Default: " + p);

        // Fluent API with method chaining
        p.setName("Alice").setAge(25).setEmail("alice@example.com");
        System.out.println("After chaining: " + p);

        p.register();   // passes 'this' to PersonService

        // --- 'super' field + method ---
        System.out.println("\n--- 2. 'super' (constructor + field + method) ---");
        System.out.println("\nCreating GoldenRetriever (constructor chain):");
        GoldenRetriever gr = new GoldenRetriever();

        System.out.println("\nGoldenRetriever eating (method chain):");
        gr.eat();

        System.out.println("\nDog name shadowing:");
        Dog d = new Dog();
        d.showNames();

        // --- Hidden fields with super ---
        System.out.println("\n--- 3. super with Hidden Fields ---");
        Derived dv = new Derived();
        dv.display();

        // Reference type matters for field access (not polymorphic)
        Base   bRef = new Derived();
        System.out.println("Base ref field: " + bRef.value);   // 10 (Base's field)

        // --- Builder with 'this' chaining ---
        System.out.println("\n--- 4. QueryBuilder (Fluent API via 'this') ---");
        String q1 = new QueryBuilder()
            .from("users")
            .where("age > 18")
            .orderBy("name")
            .limit(10)
            .build();

        String q2 = new QueryBuilder()
            .from("orders")
            .distinct()
            .where("status = 'active'")
            .build();

        System.out.println("Query 1: " + q1);
        System.out.println("Query 2: " + q2);
    }
}
