/**
 * ============================================================
 *  TOPIC: Encapsulation in Java
 * ============================================================
 *
 * Encapsulation = Wrapping data (fields) + methods that operate
 * on that data into a single unit (class), AND restricting
 * direct access to internal state.
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW:
 *  - Achieved via: private fields + public getters/setters
 *  - Benefits:
 *      1. Data hiding — internal state not exposed directly
 *      2. Validation  — setters can validate before setting
 *      3. Flexibility — internal impl can change without breaking API
 *      4. Read-only/Write-only fields — omit getter or setter
 *  - A fully encapsulated class is also called a JavaBean if:
 *      1. All fields are private
 *      2. Public no-arg constructor
 *      3. Public getters and setters
 *      4. Implements Serializable (optional but common)
 *  - Encapsulation ≠ Abstraction:
 *      Encapsulation = HOW data is hidden (mechanism)
 *      Abstraction   = WHAT to hide (design decision)
 * ╚══════════════════════════════════════════════════════════╝
 */
public class Encapsulation {

    // ═══════════════════════════════════════════════════════
    // 1. FULLY ENCAPSULATED CLASS (JavaBean style)
    // ═══════════════════════════════════════════════════════
    static class Employee {
        private int    id;
        private String name;
        private double salary;
        private int    age;
        private String department;

        // No-arg constructor (JavaBean requirement)
        public Employee() {}

        public Employee(int id, String name, double salary, int age, String dept) {
            this.id         = id;
            setName(name);       // use setter for validation
            setSalary(salary);
            setAge(age);
            this.department = dept;
        }

        // Getters
        public int    getId()         { return id; }
        public String getName()       { return name; }
        public double getSalary()     { return salary; }
        public int    getAge()        { return age; }
        public String getDepartment() { return department; }

        // Setters WITH VALIDATION
        public void setName(String name) {
            if (name == null || name.trim().isEmpty())
                throw new IllegalArgumentException("Name cannot be empty");
            this.name = name.trim();
        }

        public void setSalary(double salary) {
            if (salary < 0)
                throw new IllegalArgumentException("Salary cannot be negative");
            this.salary = salary;
        }

        public void setAge(int age) {
            if (age < 18 || age > 65)
                throw new IllegalArgumentException("Age must be between 18 and 65");
            this.age = age;
        }

        public void setDepartment(String dept) { this.department = dept; }

        /*
         * ╔══════════════════════════════════════════════╗
         *  Read-only field: id has getter but NO setter.
         *  Write-only field: provide setter but NO getter.
         *  This gives fine-grained control over data access.
         * ╚══════════════════════════════════════════════╝
         */
        // id is read-only (no setId)

        @Override
        public String toString() {
            return String.format("Employee[%d, %s, dept=%s, salary=%.2f, age=%d]",
                id, name, department, salary, age);
        }
    }

    // ═══════════════════════════════════════════════════════
    // 2. ENCAPSULATION with COMPUTED PROPERTIES
    // ═══════════════════════════════════════════════════════
    static class Circle {
        private double radius;   // single field

        public Circle(double radius) { setRadius(radius); }

        public double getRadius() { return radius; }

        public void setRadius(double radius) {
            if (radius <= 0) throw new IllegalArgumentException("Radius must be positive");
            this.radius = radius;
        }

        // Computed properties — derived from radius
        public double getArea()        { return Math.PI * radius * radius; }
        public double getCircumference() { return 2 * Math.PI * radius; }
        public double getDiameter()    { return 2 * radius; }

        @Override
        public String toString() {
            return String.format("Circle(r=%.2f, area=%.2f, circ=%.2f)",
                radius, getArea(), getCircumference());
        }
    }

    // ═══════════════════════════════════════════════════════
    // 3. MUTABLE vs IMMUTABLE encapsulation
    //    (protecting array/collection fields)
    // ═══════════════════════════════════════════════════════
    static class Classroom {
        private String   roomName;
        private String[] students;   // mutable array inside

        public Classroom(String name, String[] students) {
            this.roomName = name;
            // Defensive copy on the way IN
            this.students = students.clone();
        }

        public String getRoomName() { return roomName; }

        /*
         * ╔══════════════════════════════════════════════╗
         *  DEFENSIVE COPY: return a copy, not the internal array.
         *  Without this, the caller can mutate the internal state!
         *  BAD:  return students;          // exposes internals
         *  GOOD: return students.clone();  // safe copy
         * ╚══════════════════════════════════════════════╝
         */
        public String[] getStudents() {
            return students.clone();  // defensive copy on the way OUT
        }

        public int getCount() { return students.length; }

        public void printRoster() {
            System.out.println("Room: " + roomName);
            for (String s : students) System.out.println("  - " + s);
        }
    }

    // ═══════════════════════════════════════════════════════
    // 4. ENCAPSULATION breaking (what NOT to do)
    // ═══════════════════════════════════════════════════════
    static class BadEmployee {
        public String name;    // ← public field breaks encapsulation
        public double salary;  // ← anyone can set salary = -99999

        public BadEmployee(String n, double s) { name = n; salary = s; }
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Encapsulation =====\n");

        // --- JavaBean style ---
        System.out.println("--- 1. JavaBean Encapsulation ---");
        Employee e = new Employee(1, "Alice", 75000, 28, "Engineering");
        System.out.println(e);

        e.setSalary(80000);
        System.out.println("After raise: " + e);

        try { e.setAge(10); }
        catch (IllegalArgumentException ex) { System.out.println("Caught: " + ex.getMessage()); }

        try { e.setName(""); }
        catch (IllegalArgumentException ex) { System.out.println("Caught: " + ex.getMessage()); }

        // --- Computed properties ---
        System.out.println("\n--- 2. Computed Properties ---");
        Circle c = new Circle(5.0);
        System.out.println(c);
        System.out.println("Diameter : " + c.getDiameter());

        c.setRadius(10.0);
        System.out.println("After resize: " + c);

        // --- Defensive copy ---
        System.out.println("\n--- 3. Defensive Copy (protecting internal array) ---");
        String[] roster = {"Alice", "Bob", "Charlie"};
        Classroom room = new Classroom("CS101", roster);
        room.printRoster();

        // Try to corrupt internal state
        roster[0] = "HACKED";
        room.printRoster();   // unaffected — input was copied

        String[] got = room.getStudents();
        got[0] = "HACKED_AGAIN";
        room.printRoster();   // unaffected — getter returned a copy

        // --- Breaking encapsulation (BAD) ---
        System.out.println("\n--- 4. BAD: Public Fields (anti-pattern) ---");
        BadEmployee bad = new BadEmployee("Bob", 50000);
        bad.salary = -999999;   // no validation possible!
        System.out.println("Bad salary: " + bad.salary);
    }
}
