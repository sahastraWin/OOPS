/**
 * ============================================================
 *  TOPIC: Type Casting in Java
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW:
 *
 *  1. Primitive Type Casting:
 *     Widening (implicit): byte → short → int → long → float → double
 *       - No data loss; done automatically by compiler.
 *     Narrowing (explicit): double → float → long → int → short → byte
 *       - May lose data; must cast explicitly: (int) 9.99 → 9
 *
 *  2. Object / Reference Type Casting:
 *     Upcasting   (implicit): Child → Parent (always safe, IS-A)
 *     Downcasting (explicit): Parent → Child (may throw ClassCastException)
 *       - Always check with instanceof before downcasting!
 *       - Java 16+: Pattern Matching instanceof (no explicit cast needed)
 *
 *  3. ClassCastException:
 *     Thrown at runtime when downcasting to incompatible type.
 *     Dog dog = (Dog) animalRef;  // safe only if animalRef IS a Dog
 *
 *  4. instanceof operator:
 *     Checks if object is instance of a class/interface at runtime.
 *     Returns false for null (no NullPointerException).
 *     Java 16+: if (obj instanceof Dog d) → pattern matching.
 * ╚══════════════════════════════════════════════════════════╝
 */
public class TypeCasting {

    // ═══════════════════════════════════════════════════════
    // Class hierarchy for object casting demo
    // ═══════════════════════════════════════════════════════
    static class Animal {
        String name;
        Animal(String n) { name = n; }
        public void breathe()   { System.out.println(name + " is breathing."); }
        public String describe() { return "Animal(" + name + ")"; }
    }

    static class Dog extends Animal {
        String breed;
        Dog(String n, String b) { super(n); breed = b; }
        public void fetch()  { System.out.println(name + " fetches the ball!"); }
        public void bark()   { System.out.println(name + " says: Woof!"); }
        @Override public String describe() { return "Dog(" + name + "," + breed + ")"; }
    }

    static class Cat extends Animal {
        boolean indoor;
        Cat(String n, boolean indoor) { super(n); this.indoor = indoor; }
        public void purr()   { System.out.println(name + " purrs."); }
        @Override public String describe() { return "Cat(" + name + "," + (indoor?"indoor":"outdoor") + ")"; }
    }

    static class GoldenRetriever extends Dog {
        GoldenRetriever(String name) { super(name, "Golden Retriever"); }
        public void guide() { System.out.println(name + " is guiding a blind person."); }
    }

    // ═══════════════════════════════════════════════════════
    // 1. PRIMITIVE WIDENING
    // ═══════════════════════════════════════════════════════
    static void primitiveWidening() {
        System.out.println("--- 1. Primitive Widening (implicit) ---");

        byte   b = 100;
        short  s = b;        // byte → short (widening)
        int    i = s;        // short → int
        long   l = i;        // int → long
        float  f = l;        // long → float
        double d = f;        // float → double

        System.out.println("byte   → " + b);
        System.out.println("short  → " + s);
        System.out.println("int    → " + i);
        System.out.println("long   → " + l);
        System.out.println("float  → " + f);
        System.out.println("double → " + d);

        // char widening
        char  c  = 'A';
        int   ci = c;        // char → int (Unicode value)
        System.out.println("'A' → int: " + ci);
    }

    // ═══════════════════════════════════════════════════════
    // 2. PRIMITIVE NARROWING
    // ═══════════════════════════════════════════════════════
    static void primitiveNarrowing() {
        System.out.println("\n--- 2. Primitive Narrowing (explicit cast) ---");

        double d = 9.99;
        int    i = (int) d;    // truncates decimal → 9
        System.out.println("(int) 9.99 = " + i);   // 9

        int  big   = 300;
        byte small = (byte) big;  // overflow! 300 mod 256 = 44
        System.out.println("(byte) 300 = " + small);   // 44

        long  l = 123456789012345L;
        int   narrowed = (int) l;   // data loss
        System.out.println("(int) 123456789012345L = " + narrowed);

        /*
         * ╔══════════════════════════════════════════════╗
         *  NARROWING TRAPS:
         *  - Decimal part is TRUNCATED (not rounded): (int)3.9 = 3
         *  - Overflow wraps around for integer types
         *  - No exception thrown — silent data corruption!
         * ╚══════════════════════════════════════════════╝
         */
        float  f    = 3.14f;
        int    fi   = (int) f;    // 3 (truncated)
        System.out.println("(int) 3.14f = " + fi);

        // char casting
        int  code = 65;
        char ch   = (char) code;   // int → char
        System.out.println("(char) 65 = " + ch);
    }

    // ═══════════════════════════════════════════════════════
    // 3. OBJECT UPCASTING (implicit, always safe)
    // ═══════════════════════════════════════════════════════
    static void upcasting() {
        System.out.println("\n--- 3. Upcasting (implicit, safe) ---");

        Dog dog = new Dog("Rex", "Labrador");
        Animal animal = dog;   // upcast: Dog → Animal (implicit)

        /*
         * ╔══════════════════════════════════════════════╗
         *  After upcasting:
         *  - Reference type = Animal (compile-time view)
         *  - Object type    = Dog    (runtime reality)
         *  - Can only call methods defined in Animal
         *  - Overridden methods (virtual dispatch) still call Dog's version
         *  - Cannot call Dog-specific methods (fetch, bark) via animal ref
         * ╚══════════════════════════════════════════════╝
         */
        animal.breathe();          // Animal method — OK
        System.out.println(animal.describe());  // Dog's override called at runtime
        // animal.fetch();          // compile error: Animal doesn't have fetch()

        // Array of base type holding subtype objects
        Animal[] zoo = {
            new Dog("Buddy", "Poodle"),
            new Cat("Whiskers", true),
            new GoldenRetriever("Goldie"),
            new Cat("Shadow", false)
        };

        System.out.println("Zoo polymorphic breathe:");
        for (Animal a : zoo) {
            System.out.print("  " + a.describe() + " → ");
            a.breathe();
        }
    }

    // ═══════════════════════════════════════════════════════
    // 4. OBJECT DOWNCASTING (explicit, may throw CCE)
    // ═══════════════════════════════════════════════════════
    static void downcasting() {
        System.out.println("\n--- 4. Downcasting (explicit, needs instanceof check) ---");

        Animal[] zoo = {
            new Dog("Rex", "German Shepherd"),
            new Cat("Luna", true),
            new GoldenRetriever("Goldie"),
            new Cat("Tiger", false)
        };

        for (Animal a : zoo) {
            /*
             * ╔══════════════════════════════════════════╗
             *  ALWAYS check instanceof before downcasting!
             *  Without check: ClassCastException at runtime
             *  if the object is not actually a Dog.
             * ╚══════════════════════════════════════════╝
             */
            if (a instanceof Dog) {
                Dog d = (Dog) a;   // safe downcast
                d.bark();
                d.fetch();
                System.out.println("  breed: " + d.breed);
            } else if (a instanceof Cat) {
                Cat c = (Cat) a;
                c.purr();
                System.out.println("  indoor: " + c.indoor);
            }
        }

        // ClassCastException demonstration
        System.out.println("\nClassCastException demo:");
        Animal animal = new Cat("Kitty", true);
        try {
            Dog dog = (Dog) animal;   // Cat is NOT a Dog → CCE!
            dog.bark();
        } catch (ClassCastException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }

    // ═══════════════════════════════════════════════════════
    // 5. PATTERN MATCHING instanceof (Java 16+)
    // ═══════════════════════════════════════════════════════
    static void patternMatching() {
        System.out.println("\n--- 5. Pattern Matching instanceof (Java 16+) ---");

        Object[] objects = {
            "Hello World",
            42,
            3.14,
            new Dog("Buddy", "Beagle"),
            true,
            new int[]{1, 2, 3}
        };

        for (Object obj : objects) {
            /*
             * ╔══════════════════════════════════════════╗
             *  PATTERN MATCHING:
             *  if (obj instanceof String s)
             *  Declares variable 's' of type String in one step.
             *  No explicit cast needed.
             *  's' is in scope only when instanceof is true.
             * ╚══════════════════════════════════════════╝
             */
            if (obj instanceof String s) {
                System.out.println("String: \"" + s + "\" length=" + s.length());
            } else if (obj instanceof Integer i) {
                System.out.println("Integer: " + i + " doubled=" + (i * 2));
            } else if (obj instanceof Double d) {
                System.out.printf("Double: %.2f%n", d);
            } else if (obj instanceof Dog dog) {
                System.out.println("Dog: " + dog.name + " breed=" + dog.breed);
                dog.bark();  // can call Dog methods directly
            } else if (obj instanceof Boolean b) {
                System.out.println("Boolean: " + b);
            } else if (obj instanceof int[] arr) {
                System.out.println("int[]: length=" + arr.length);
            }
        }
    }

    // ═══════════════════════════════════════════════════════
    // 6. INTERFACE TYPE CASTING
    // ═══════════════════════════════════════════════════════
    interface Flyable  { void fly(); }
    interface Swimmable{ void swim(); }

    static class Duck extends Animal implements Flyable, Swimmable {
        Duck(String n) { super(n); }
        @Override public void fly()  { System.out.println(name + " flaps and flies."); }
        @Override public void swim() { System.out.println(name + " paddles in water."); }
    }

    static void interfaceCasting() {
        System.out.println("\n--- 6. Interface Casting ---");

        Duck donald = new Duck("Donald");

        // Upcast to different interface types
        Flyable  flyer   = donald;
        Swimmable swimmer = donald;
        Animal   animal  = donald;

        flyer.fly();
        swimmer.swim();
        animal.breathe();

        // Downcast back from interface
        if (flyer instanceof Duck d) {
            System.out.println("Duck can also swim: "); d.swim();
        }

        // Check multiple interfaces
        Object obj = donald;
        System.out.println("obj instanceof Flyable:  " + (obj instanceof Flyable));
        System.out.println("obj instanceof Swimmable:" + (obj instanceof Swimmable));
        System.out.println("obj instanceof Animal:   " + (obj instanceof Animal));
        System.out.println("obj instanceof Dog:      " + (obj instanceof Dog));
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Type Casting =====\n");

        primitiveWidening();
        primitiveNarrowing();
        upcasting();
        downcasting();
        patternMatching();
        interfaceCasting();
    }
}
