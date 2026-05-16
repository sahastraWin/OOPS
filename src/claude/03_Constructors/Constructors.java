/**
 * ============================================================
 *  TOPIC: Constructors in Java
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW:
 *  - Constructor has SAME NAME as class, NO return type.
 *  - If no constructor defined, compiler provides a DEFAULT
 *    (no-arg) constructor that calls super().
 *  - If ANY constructor is defined, compiler does NOT generate
 *    the default constructor.
 *  - Constructors CANNOT be abstract, static, final, or synchronized
 *    (they can be private — used in Singleton, Builder patterns).
 *  - this()  → calls another constructor of the SAME class.
 *  - super() → calls parent constructor. Must be FIRST statement.
 *  - this() and super() CANNOT both appear in one constructor.
 *  - Java has NO copy constructor built-in (unlike C++),
 *    but you can write one manually.
 *  - Constructor overloading = multiple constructors with
 *    different parameter lists.
 * ╚══════════════════════════════════════════════════════════╝
 *
 * Types covered:
 *  1. Default (No-arg) Constructor
 *  2. Parameterised Constructor
 *  3. Copy Constructor (manual)
 *  4. Constructor Chaining (this() / super())
 *  5. Private Constructor (Singleton)
 */
public class Constructors {

    // ═══════════════════════════════════════════════════════
    // 1. DEFAULT CONSTRUCTOR
    // ═══════════════════════════════════════════════════════
    static class DefaultDemo {
        int value;
        String label;

        /*
         * If we comment this out, compiler generates:
         *   public DefaultDemo() { super(); }
         * Primitive fields default to 0/false/'\0'.
         * Object fields default to null.
         */
        public DefaultDemo() {
            value = 0;
            label = "default";
            System.out.println("[DefaultDemo] no-arg constructor called");
        }

        @Override public String toString() {
            return "DefaultDemo(value=" + value + ", label=" + label + ")";
        }
    }

    // ═══════════════════════════════════════════════════════
    // 2. PARAMETERISED CONSTRUCTOR + OVERLOADING
    // ═══════════════════════════════════════════════════════
    static class Rectangle {
        private double length, width;

        // No-arg constructor
        public Rectangle() {
            this(1.0, 1.0);   // delegates to two-arg constructor
            System.out.println("[Rectangle] no-arg → delegated to (1.0, 1.0)");
        }

        // One-arg (square)
        public Rectangle(double side) {
            this(side, side);  // delegates
            System.out.println("[Rectangle] square constructor with side=" + side);
        }

        // Primary parameterised constructor
        public Rectangle(double length, double width) {
            if (length <= 0 || width <= 0)
                throw new IllegalArgumentException("Dimensions must be positive");
            this.length = length;
            this.width  = width;
            System.out.println("[Rectangle] primary constructor (" + length + " x " + width + ")");
        }

        public double area()      { return length * width; }
        public double perimeter() { return 2 * (length + width); }

        @Override public String toString() {
            return String.format("Rectangle(%.1f x %.1f) area=%.1f", length, width, area());
        }
    }

    // ═══════════════════════════════════════════════════════
    // 3. COPY CONSTRUCTOR (manual in Java)
    // ═══════════════════════════════════════════════════════
    static class Student {
        private String name;
        private int    age;
        private int[]  grades;   // mutable field → needs deep copy

        // Primary constructor
        public Student(String name, int age, int[] grades) {
            this.name   = name;
            this.age    = age;
            this.grades = grades.clone();   // defensive copy
        }

        /*
         * ╔══════════════════════════════════════════════╗
         *  COPY CONSTRUCTOR:
         *  Java has no built-in copy constructor.
         *  We write one manually.
         *  SHALLOW copy: copies references (shares array).
         *  DEEP copy: creates new array (independent).
         *  Always use DEEP copy for mutable fields!
         * ╚══════════════════════════════════════════════╝
         */
        public Student(Student other) {
            this.name   = other.name;          // String is immutable → safe
            this.age    = other.age;
            this.grades = other.grades.clone(); // deep copy of array
            System.out.println("[Student] Copy constructor called");
        }

        public void setGrade(int index, int grade) { grades[index] = grade; }
        public int  getGrade(int index)            { return grades[index]; }
        public String getName()                    { return name; }

        @Override public String toString() {
            StringBuilder sb = new StringBuilder("Student(" + name + ", age=" + age + ", grades=[");
            for (int i = 0; i < grades.length; i++) {
                sb.append(grades[i]);
                if (i < grades.length - 1) sb.append(",");
            }
            return sb.append("])").toString();
        }
    }

    // ═══════════════════════════════════════════════════════
    // 4. CONSTRUCTOR CHAINING — super()
    // ═══════════════════════════════════════════════════════
    static class Vehicle {
        String brand;
        int    year;

        public Vehicle(String brand, int year) {
            this.brand = brand;
            this.year  = year;
            System.out.println("[Vehicle Constructor] " + brand);
        }
    }

    static class Car extends Vehicle {
        int doors;

        public Car(String brand, int year, int doors) {
            super(brand, year);   // MUST be first statement
            this.doors = doors;
            System.out.println("[Car Constructor] doors=" + doors);
        }
    }

    static class SportsCar extends Car {
        int horsepower;

        public SportsCar(String brand, int year, int hp) {
            super(brand, year, 2);   // sports cars have 2 doors
            this.horsepower = hp;
            System.out.println("[SportsCar Constructor] hp=" + hp);
        }

        @Override public String toString() {
            return brand + " (" + year + ") HP=" + horsepower + " Doors=" + doors;
        }
    }

    // ═══════════════════════════════════════════════════════
    // 5. PRIVATE CONSTRUCTOR → Singleton Pattern
    // ═══════════════════════════════════════════════════════
    static class DatabaseConnection {
        private static DatabaseConnection instance;
        private String connectionString;
        private int    queryCount = 0;

        /*
         * ╔══════════════════════════════════════════════╗
         *  PRIVATE CONSTRUCTOR:
         *  Prevents external instantiation.
         *  Forces use of getInstance() factory method.
         *  This is the SINGLETON pattern — one of the most
         *  commonly asked design pattern interview questions.
         *
         *  This version is NOT thread-safe.
         *  Thread-safe version: use double-checked locking
         *  or initialise via enum.
         * ╚══════════════════════════════════════════════╝
         */
        private DatabaseConnection(String connStr) {
            this.connectionString = connStr;
            System.out.println("[DB] Connection established: " + connStr);
        }

        public static DatabaseConnection getInstance() {
            if (instance == null) {
                instance = new DatabaseConnection("jdbc:mysql://localhost:3306/mydb");
            }
            return instance;
        }

        public void query(String sql) {
            queryCount++;
            System.out.println("[DB Query #" + queryCount + "] " + sql);
        }

        public int getQueryCount() { return queryCount; }
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Constructors in Java =====\n");

        // --- Default Constructor ---
        System.out.println("--- 1. Default Constructor ---");
        DefaultDemo d = new DefaultDemo();
        System.out.println(d);

        // --- Parameterised + Overloading + this() ---
        System.out.println("\n--- 2. Parameterised + Overloading + this() ---");
        Rectangle r1 = new Rectangle();         // no-arg
        Rectangle r2 = new Rectangle(5.0);      // square
        Rectangle r3 = new Rectangle(4.0, 6.0); // primary
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);

        // --- Copy Constructor ---
        System.out.println("\n--- 3. Copy Constructor (Deep Copy) ---");
        int[] g = {85, 90, 78};
        Student s1 = new Student("Alice", 20, g);
        Student s2 = new Student(s1);            // copy constructor

        s1.setGrade(0, 99);                       // modify original
        System.out.println("s1: " + s1);          // grade[0] = 99
        System.out.println("s2: " + s2);          // grade[0] still 85 (deep copy)

        // --- Constructor Chaining with super() ---
        System.out.println("\n--- 4. Constructor Chaining (super) ---");
        SportsCar sc = new SportsCar("Ferrari", 2024, 700);
        System.out.println(sc);

        // --- Private Constructor / Singleton ---
        System.out.println("\n--- 5. Singleton (Private Constructor) ---");
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        System.out.println("Same instance? " + (db1 == db2));   // true
        db1.query("SELECT * FROM users");
        db2.query("INSERT INTO users VALUES (...)");
        System.out.println("Total queries: " + db1.getQueryCount());
    }
}
