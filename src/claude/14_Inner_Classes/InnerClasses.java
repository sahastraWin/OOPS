/**
 * ============================================================
 *  TOPIC: Inner Classes (Nested Classes) in Java
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW: 4 types of nested classes
 *
 *  1. Static Nested Class
 *     - declared with 'static' inside another class
 *     - does NOT hold a reference to the outer class
 *     - can only access outer class's static members
 *     - instantiate: new Outer.StaticNested()
 *
 *  2. Non-static Inner Class (Member Inner Class)
 *     - no 'static' keyword
 *     - holds an implicit reference to the outer class instance
 *     - can access ALL members of outer class (even private)
 *     - instantiate: outerObj.new Inner()
 *     - causes memory leak if inner object outlives outer (holds ref!)
 *
 *  3. Local Inner Class
 *     - defined inside a method/block
 *     - scope limited to that method
 *     - can access local variables only if they are effectively final
 *
 *  4. Anonymous Inner Class
 *     - no name; defined and instantiated in one expression
 *     - extends a class OR implements an interface
 *     - used heavily before Java 8 lambdas
 *     - can access effectively final local variables
 *
 *  When to use:
 *  Static nested  → helper class, Builder, grouping (no outer ref needed)
 *  Inner class    → needs access to outer instance (e.g., Iterator)
 *  Local class    → complex one-time logic inside a method
 *  Anonymous      → one-time implementation of an interface (or lambda)
 * ╚══════════════════════════════════════════════════════════╝
 */
public class InnerClasses {

    // ═══════════════════════════════════════════════════════
    // 1. STATIC NESTED CLASS
    // ═══════════════════════════════════════════════════════
    static class Computer {
        private String brand;
        private CPU    cpu;      // has-a relationship
        private RAM    ram;

        /*
         * ╔══════════════════════════════════════════════╗
         *  Static Nested: CPU and RAM are implementation
         *  details of Computer. Making them static nested
         *  classes groups them logically without exposing
         *  them as top-level classes.
         * ╚══════════════════════════════════════════════╝
         */
        static class CPU {
            private String model;
            private int    cores;
            private double ghz;

            public CPU(String model, int cores, double ghz) {
                this.model = model; this.cores = cores; this.ghz = ghz;
            }

            @Override public String toString() {
                return model + " " + cores + "-core @" + ghz + "GHz";
            }
        }

        static class RAM {
            private int    gbSize;
            private String type;

            public RAM(int gbSize, String type) { this.gbSize = gbSize; this.type = type; }

            @Override public String toString() { return gbSize + "GB " + type; }
        }

        public Computer(String brand, CPU cpu, RAM ram) {
            this.brand = brand; this.cpu = cpu; this.ram = ram;
        }

        @Override public String toString() {
            return brand + " [CPU: " + cpu + ", RAM: " + ram + "]";
        }
    }

    // ═══════════════════════════════════════════════════════
    // 2. NON-STATIC INNER CLASS (Member Inner Class)
    // ═══════════════════════════════════════════════════════
    static class LinkedList {
        private Node head;
        private int  size;

        // Non-static inner: Node needs to be part of a LinkedList
        class Node {
            int  data;
            Node next;

            Node(int data) {
                this.data = data;
                this.next = null;
                size++;  // can access outer class's instance field 'size'
            }
        }

        public void add(int value) {
            Node n = new Node(value);  // no need for: this.new Node(value)
            if (head == null) { head = n; return; }
            Node cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = n;
        }

        public void print() {
            Node cur = head;
            System.out.print("List[" + size + "]: ");
            while (cur != null) {
                System.out.print(cur.data + (cur.next != null ? " → " : ""));
                cur = cur.next;
            }
            System.out.println();
        }

        /*
         * ╔══════════════════════════════════════════════╗
         *  ITERATOR as inner class (classic pattern):
         *  Inner class Iterator holds state and accesses
         *  LinkedList's head (outer instance member).
         * ╚══════════════════════════════════════════════╝
         */
        class ListIterator {
            private Node current = head;

            public boolean hasNext() { return current != null; }

            public int next() {
                if (!hasNext()) throw new java.util.NoSuchElementException();
                int val = current.data;
                current = current.next;
                return val;
            }
        }

        public ListIterator iterator() { return new ListIterator(); }
    }

    // ═══════════════════════════════════════════════════════
    // 3. LOCAL INNER CLASS (inside a method)
    // ═══════════════════════════════════════════════════════
    static void localClassDemo() {
        final String prefix = "[LOCAL]";  // effectively final — accessible by local class

        /*
         * ╔══════════════════════════════════════════════╗
         *  LOCAL CLASS:
         *  - Defined inside a method.
         *  - Cannot have access modifiers (public/private/static).
         *  - Can access: method params and local vars IF effectively final.
         *  - Can access: all members of the enclosing class.
         * ╚══════════════════════════════════════════════╝
         */
        class LocalLogger {
            private String tag;
            LocalLogger(String tag) { this.tag = tag; }
            void log(String msg) { System.out.println(prefix + "[" + tag + "] " + msg); }
        }

        LocalLogger appLog  = new LocalLogger("APP");
        LocalLogger dbLog   = new LocalLogger("DB");
        appLog.log("Application started");
        dbLog.log("Connected to database");
        appLog.log("Request received");
    }

    // ═══════════════════════════════════════════════════════
    // 4. ANONYMOUS INNER CLASS
    // ═══════════════════════════════════════════════════════
    interface Greeting {
        void greet(String name);
        default String getLanguage() { return "English"; }
    }

    static abstract class Shape {
        abstract double area();
        void describe() {
            System.out.println(getClass().getSimpleName() + " area=" + String.format("%.2f", area()));
        }
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Inner Classes =====\n");

        // --- Static Nested Class ---
        System.out.println("--- 1. Static Nested Class ---");
        // Instantiated WITHOUT any outer class instance
        Computer.CPU cpu = new Computer.CPU("Intel Core i9", 16, 3.6);
        Computer.RAM ram = new Computer.RAM(32, "DDR5");
        Computer pc = new Computer("Dell XPS", cpu, ram);
        System.out.println(pc);

        Computer.CPU m2 = new Computer.CPU("Apple M2", 8, 3.5);
        Computer mac = new Computer("MacBook Pro", m2, new Computer.RAM(16, "LPDDR5"));
        System.out.println(mac);

        // --- Non-static Inner Class ---
        System.out.println("\n--- 2. Non-Static Inner Class (Iterator) ---");
        LinkedList list = new LinkedList();
        list.add(10); list.add(20); list.add(30); list.add(40);
        list.print();

        // Iterator (inner class with outer class access)
        LinkedList.ListIterator it = list.iterator();
        System.out.print("Iterating: ");
        while (it.hasNext()) System.out.print(it.next() + " ");
        System.out.println();

        // --- Local Inner Class ---
        System.out.println("\n--- 3. Local Inner Class ---");
        localClassDemo();

        // --- Anonymous Inner Class ---
        System.out.println("\n--- 4. Anonymous Inner Class ---");

        // Implementing interface anonymously
        Greeting hindi = new Greeting() {
            @Override public void greet(String name) {
                System.out.println("Namaste, " + name + "!");
            }
            @Override public String getLanguage() { return "Hindi"; }
        };

        Greeting japanese = new Greeting() {
            @Override public void greet(String name) {
                System.out.println("Konnichiwa, " + name + "!");
            }
        };

        hindi.greet("Sahastrajeet");
        System.out.println("Language: " + hindi.getLanguage());
        japanese.greet("Tanaka");
        System.out.println("Language: " + japanese.getLanguage());

        // Extending abstract class anonymously
        Shape circle = new Shape() {
            double radius = 5.0;
            @Override public double area() { return Math.PI * radius * radius; }
        };

        Shape rectangle = new Shape() {
            double l = 4, w = 6;
            @Override public double area() { return l * w; }
        };

        circle.describe();
        rectangle.describe();

        // Sorting with anonymous Comparator (pre-lambda style)
        String[] names = {"Charlie", "Alice", "Bob", "Diana"};
        java.util.Arrays.sort(names, new java.util.Comparator<String>() {
            @Override public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });
        System.out.print("Sorted: ");
        for (String n : names) System.out.print(n + " ");
        System.out.println();

        // Lambda equivalent (Java 8+) — same thing, less code
        java.util.Arrays.sort(names, (a, b) -> b.compareTo(a));  // reverse sort
        System.out.print("Reverse: ");
        for (String n : names) System.out.print(n + " ");
        System.out.println();
    }
}
