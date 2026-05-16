/**
 * ============================================================
 *  TOPIC: Java 8+ Modern Features
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW: Key Java 8–17 features
 *
 *  Java 8:
 *   - Lambda Expressions        (see 18_Lambda)
 *   - Functional Interfaces     (see 18_Lambda)
 *   - Stream API                (see 19_Stream)
 *   - Optional<T>               → avoid NullPointerException
 *   - Default/Static in interface (see 09_Interfaces)
 *   - Date/Time API (java.time) → LocalDate, LocalDateTime, etc.
 *   - Method References         (see 18_Lambda)
 *
 *  Java 9:
 *   - Modules (JPMS)
 *   - Collection factory: List.of(), Map.of(), Set.of()
 *   - Stream: takeWhile(), dropWhile(), iterate() with predicate
 *   - Optional: ifPresentOrElse(), stream()
 *
 *  Java 10:
 *   - Local Variable Type Inference: var keyword
 *
 *  Java 11:
 *   - String methods: isBlank(), strip(), lines(), repeat()
 *   - var in lambda parameters
 *   - Files.readString(), Files.writeString()
 *
 *  Java 14:
 *   - Records (preview) → compact immutable data classes
 *
 *  Java 16:
 *   - Records (stable)
 *   - Pattern matching instanceof (stable)
 *
 *  Java 17:
 *   - Sealed classes → restrict which classes can extend
 *   - Pattern matching switch (preview)
 * ╚══════════════════════════════════════════════════════════╝
 */

import java.util.*;
import java.util.stream.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

public class Java8PlusFeatures {

    // ═══════════════════════════════════════════════════════
    // 1. OPTIONAL<T> — avoid null, avoid NullPointerException
    // ═══════════════════════════════════════════════════════
    static class UserRepository {
        private static final Map<Integer, String> db = Map.of(
            1, "Alice", 2, "Bob", 3, "Charlie"
        );

        /*
         * ╔══════════════════════════════════════════════╗
         *  Return Optional instead of null.
         *  Forces caller to handle the absent case.
         *  Optional is a container: present or empty.
         * ╚══════════════════════════════════════════════╝
         */
        public Optional<String> findById(int id) {
            return Optional.ofNullable(db.get(id));
        }

        public Optional<String> findByName(String name) {
            return db.values().stream()
                .filter(n -> n.equalsIgnoreCase(name))
                .findFirst();
        }
    }

    static void optionalDemo() {
        System.out.println("--- 1. Optional<T> ---");
        UserRepository repo = new UserRepository();

        // Creating Optionals
        Optional<String> present = Optional.of("Hello");
        Optional<String> empty   = Optional.empty();
        Optional<String> nullable= Optional.ofNullable(null);

        System.out.println("present.isPresent(): " + present.isPresent());
        System.out.println("empty.isEmpty():     " + empty.isEmpty());
        System.out.println("present.get():       " + present.get());

        // orElse, orElseGet, orElseThrow
        System.out.println("empty.orElse(\"default\"):     " + empty.orElse("default"));
        System.out.println("empty.orElseGet(()->'lazy'): " + empty.orElseGet(() -> "lazy"));
        try {
            empty.orElseThrow(() -> new RuntimeException("Not found!"));
        } catch (RuntimeException e) {
            System.out.println("orElseThrow: " + e.getMessage());
        }

        // map, flatMap, filter
        Optional<Integer> len = present.map(String::length);
        System.out.println("present.map(length): " + len.orElse(0));

        Optional<String> filtered = present.filter(s -> s.length() > 3);
        System.out.println("filter(len>3): " + filtered.orElse("too short"));

        // ifPresent, ifPresentOrElse (Java 9+)
        present.ifPresent(s -> System.out.println("ifPresent: " + s));
        empty.ifPresentOrElse(
            s  -> System.out.println("Present: " + s),
            () -> System.out.println("ifPresentOrElse: was empty!")
        );

        // From repository
        repo.findById(1).ifPresent(u -> System.out.println("Found user: " + u));
        String missing = repo.findById(99).orElse("Unknown User");
        System.out.println("Missing user: " + missing);

        // Optional chaining
        String result = repo.findById(2)
            .map(String::toUpperCase)
            .map(s -> "User: " + s)
            .orElse("No user");
        System.out.println("Chained: " + result);
    }

    // ═══════════════════════════════════════════════════════
    // 2. DATE / TIME API (java.time — Java 8+)
    // ═══════════════════════════════════════════════════════
    static void dateTimeDemo() {
        System.out.println("\n--- 2. Date/Time API (java.time) ---");

        /*
         * ╔══════════════════════════════════════════════╗
         *  java.time (JSR-310) replaces the broken
         *  java.util.Date and java.util.Calendar.
         *  All classes are IMMUTABLE and thread-safe.
         *
         *  Key classes:
         *  LocalDate      → date only (no time, no timezone)
         *  LocalTime      → time only (no date, no timezone)
         *  LocalDateTime  → date + time (no timezone)
         *  ZonedDateTime  → date + time + timezone
         *  Instant        → machine timestamp (epoch seconds)
         *  Duration       → time-based amount (hours, minutes, seconds)
         *  Period         → date-based amount (years, months, days)
         *  DateTimeFormatter → parsing and formatting
         * ╚══════════════════════════════════════════════╝
         */

        // LocalDate
        LocalDate today    = LocalDate.now();
        LocalDate birthday = LocalDate.of(1999, Month.MARCH, 15);
        LocalDate nextWeek = today.plusWeeks(1);
        LocalDate lastYear = today.minusYears(1);

        System.out.println("Today:     " + today);
        System.out.println("Birthday:  " + birthday);
        System.out.println("Next week: " + nextWeek);
        System.out.println("Last year: " + lastYear);
        System.out.println("Day of week: " + today.getDayOfWeek());
        System.out.println("Is leap year: " + today.isLeapYear());

        // Age calculation
        Period age = Period.between(birthday, today);
        System.out.printf("Age: %d years, %d months, %d days%n",
            age.getYears(), age.getMonths(), age.getDays());

        // LocalTime
        LocalTime now       = LocalTime.now();
        LocalTime meeting   = LocalTime.of(14, 30, 0);
        LocalTime afterMeet = meeting.plusHours(1).plusMinutes(30);

        System.out.printf("Now: %s | Meeting: %s | End: %s%n", now, meeting, afterMeet);
        System.out.println("Meeting is before now: " + meeting.isBefore(now));

        // LocalDateTime
        LocalDateTime deadline = LocalDateTime.of(
            LocalDate.now().plusDays(7),
            LocalTime.of(23, 59, 59)
        );
        System.out.println("Deadline: " + deadline);

        // Duration between two times
        Duration until = Duration.between(LocalDateTime.now(), deadline);
        System.out.printf("Time until deadline: %d days, %d hours%n",
            until.toDays(), until.toHours() % 24);

        // ZonedDateTime
        ZonedDateTime ist  = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        ZonedDateTime utc  = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime us   = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println("IST:      " + ist.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")));
        System.out.println("UTC:      " + utc.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")));
        System.out.println("New York: " + us.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")));

        // Formatting and Parsing
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formatted = LocalDateTime.now().format(formatter);
        System.out.println("Formatted: " + formatted);

        LocalDateTime parsed = LocalDateTime.parse("25/12/2024 10:30", formatter);
        System.out.println("Parsed:    " + parsed);

        // Instant (machine time)
        Instant instant = Instant.now();
        System.out.println("Instant (epoch ms): " + instant.toEpochMilli());
    }

    // ═══════════════════════════════════════════════════════
    // 3. var — Local Variable Type Inference (Java 10+)
    // ═══════════════════════════════════════════════════════
    static void varDemo() {
        System.out.println("\n--- 3. var (Local Variable Type Inference) ---");

        /*
         * ╔══════════════════════════════════════════════╗
         *  'var' is NOT dynamic typing.
         *  The type is inferred at COMPILE TIME — still statically typed.
         *  var can only be used for LOCAL variables with initializer.
         *  Cannot use: method parameters, return types, class fields.
         * ╚══════════════════════════════════════════════╝
         */
        var name     = "Alice";          // inferred: String
        var age      = 25;               // inferred: int
        var pi       = 3.14;             // inferred: double
        var list     = new ArrayList<String>();  // inferred: ArrayList<String>
        var map      = new HashMap<String, Integer>(); // inferred: HashMap<String,Integer>

        list.add("Java"); list.add("Go"); list.add("Rust");
        map.put("one", 1); map.put("two", 2);

        System.out.println("name: " + name + " age: " + age + " pi: " + pi);
        System.out.println("list: " + list);
        System.out.println("map:  " + map);

        // var in enhanced for loop
        for (var entry : map.entrySet()) {
            System.out.println("  " + entry.getKey() + " → " + entry.getValue());
        }

        // var with streams
        var result = list.stream()
            .filter(s -> s.length() > 3)
            .collect(Collectors.joining(", "));
        System.out.println("Filtered: " + result);
    }

    // ═══════════════════════════════════════════════════════
    // 4. Collection Factory Methods (Java 9+)
    // ═══════════════════════════════════════════════════════
    static void collectionFactories() {
        System.out.println("\n--- 4. Collection Factory Methods (Java 9+) ---");

        /*
         * ╔══════════════════════════════════════════════╗
         *  List.of(), Set.of(), Map.of() create IMMUTABLE collections.
         *  - Cannot add, remove, or set elements (UnsupportedOperationException)
         *  - null elements NOT allowed
         *  - Set/Map do NOT allow duplicate keys
         *  - List.copyOf(), Set.copyOf(), Map.copyOf() also available
         * ╚══════════════════════════════════════════════╝
         */
        var immutableList = List.of("Alice", "Bob", "Charlie");
        var immutableSet  = Set.of("Red", "Green", "Blue");
        var immutableMap  = Map.of("one", 1, "two", 2, "three", 3);

        System.out.println("List.of: " + immutableList);
        System.out.println("Set.of:  " + immutableSet);
        System.out.println("Map.of:  " + immutableMap);

        // Attempting to mutate throws UnsupportedOperationException
        try { immutableList.add("Dave"); }
        catch (UnsupportedOperationException e) { System.out.println("Cannot add to List.of()"); }

        // Map.ofEntries for larger maps
        var largeMap = Map.ofEntries(
            Map.entry("a", 1), Map.entry("b", 2),
            Map.entry("c", 3), Map.entry("d", 4)
        );
        System.out.println("Map.ofEntries: " + largeMap);

        // To get a mutable copy:
        var mutableList = new ArrayList<>(immutableList);
        mutableList.add("Dave");
        System.out.println("Mutable copy: " + mutableList);
    }

    // ═══════════════════════════════════════════════════════
    // 5. RECORDS (Java 16+) — compact immutable data classes
    // ═══════════════════════════════════════════════════════
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  RECORDS: concise syntax for immutable data holders.
     *  Compiler automatically generates:
     *  - private final fields
     *  - canonical constructor
     *  - accessor methods (name(), age(), etc. — NOT getName())
     *  - equals(), hashCode(), toString()
     *  Cannot extend a class (implicitly extends Record).
     *  Can implement interfaces.
     *  Can have additional methods and static members.
     * ╚══════════════════════════════════════════════════════╝
     */
    record Point(double x, double y) {
        // Compact canonical constructor (validation)
        Point {
            if (Double.isNaN(x) || Double.isNaN(y))
                throw new IllegalArgumentException("Coordinates cannot be NaN");
        }

        // Additional methods
        public double distanceTo(Point other) {
            double dx = this.x - other.x;
            double dy = this.y - other.y;
            return Math.sqrt(dx*dx + dy*dy);
        }

        public Point translate(double dx, double dy) {
            return new Point(x + dx, y + dy);
        }

        // Static factory
        public static Point origin() { return new Point(0, 0); }
    }

    record Student(int id, String name, double gpa) implements Comparable<Student> {
        @Override
        public int compareTo(Student other) {
            return Double.compare(other.gpa, this.gpa);  // descending by GPA
        }
    }

    static void recordsDemo() {
        System.out.println("\n--- 5. Records (Java 16+) ---");

        Point p1 = new Point(3, 4);
        Point p2 = Point.origin();
        Point p3 = p1.translate(1, 1);

        System.out.println("p1: " + p1);           // auto toString
        System.out.println("p1.x(): " + p1.x());   // accessor (not getX)
        System.out.println("p1.y(): " + p1.y());
        System.out.printf("distance p1→origin: %.2f%n", p1.distanceTo(p2));
        System.out.println("translated: " + p3);

        // Auto equals and hashCode
        Point p4 = new Point(3, 4);
        System.out.println("p1.equals(p4): " + p1.equals(p4));  // true
        System.out.println("p1 == p4:      " + (p1 == p4));      // false

        // Records in collections
        var students = List.of(
            new Student(1, "Alice",   9.5),
            new Student(2, "Bob",     8.7),
            new Student(3, "Charlie", 9.1)
        );
        students.stream().sorted().forEach(s ->
            System.out.printf("  %d. %s (GPA: %.1f)%n", s.id(), s.name(), s.gpa()));
    }

    // ═══════════════════════════════════════════════════════
    // 6. SEALED CLASSES (Java 17+)
    // ═══════════════════════════════════════════════════════
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  SEALED CLASSES: restrict which classes can extend them.
     *  Permits list must be explicit.
     *  Permitted subclasses must be: final, sealed, or non-sealed.
     *  Used for: exhaustive switch, type-safe hierarchies, algebraic types.
     * ╚══════════════════════════════════════════════════════╝
     */
    sealed interface Shape permits Circle, Rectangle2, Triangle2 {
        double area();
    }

    record Circle(double radius) implements Shape {
        public double area() { return Math.PI * radius * radius; }
    }

    record Rectangle2(double width, double height) implements Shape {
        public double area() { return width * height; }
    }

    record Triangle2(double base, double height) implements Shape {
        public double area() { return 0.5 * base * height; }
    }

    static String describeShape(Shape shape) {
        // Pattern matching switch (Java 17+ preview, stable in 21)
        return switch (shape) {
            case Circle    c -> String.format("Circle r=%.1f area=%.2f", c.radius(), c.area());
            case Rectangle2 r -> String.format("Rect %.1fx%.1f area=%.2f", r.width(), r.height(), r.area());
            case Triangle2  t -> String.format("Triangle b=%.1f h=%.1f area=%.2f", t.base(), t.height(), t.area());
        };
    }

    static void sealedDemo() {
        System.out.println("\n--- 6. Sealed Classes + Pattern Switch (Java 17+) ---");
        List<Shape> shapes = List.of(
            new Circle(5),
            new Rectangle2(4, 6),
            new Triangle2(3, 8)
        );
        shapes.forEach(s -> System.out.println(describeShape(s)));
    }

    // ═══════════════════════════════════════════════════════
    // 7. STREAM enhancements (Java 9+)
    // ═══════════════════════════════════════════════════════
    static void streamEnhancements() {
        System.out.println("\n--- 7. Stream Enhancements (Java 9+) ---");

        // takeWhile: take elements while predicate is true
        List<Integer> nums = List.of(2, 4, 6, 8, 1, 10, 12);
        var taken = nums.stream().takeWhile(n -> n % 2 == 0).collect(Collectors.toList());
        System.out.println("takeWhile(even): " + taken);  // [2,4,6,8] stops at 1

        // dropWhile: drop elements while predicate is true
        var dropped = nums.stream().dropWhile(n -> n % 2 == 0).collect(Collectors.toList());
        System.out.println("dropWhile(even): " + dropped); // [1,10,12]

        // Stream.iterate with predicate (Java 9+)
        var powers = Stream.iterate(1, n -> n <= 1000, n -> n * 2)
            .collect(Collectors.toList());
        System.out.println("Powers of 2 ≤1000: " + powers);

        // Stream.ofNullable (Java 9+) — avoids null check
        String maybeNull = null;
        long count = Stream.ofNullable(maybeNull).count();
        System.out.println("Stream.ofNullable(null).count(): " + count);  // 0

        // Collectors.teeing (Java 12+) — collect into two collectors simultaneously
        var stats = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .collect(Collectors.teeing(
                Collectors.summingInt(Integer::intValue),
                Collectors.counting(),
                (sum, cnt) -> "sum=" + sum + " count=" + cnt + " avg=" + (sum / cnt)
            ));
        System.out.println("Teeing: " + stats);
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Java 8+ Modern Features =====\n");
        optionalDemo();
        dateTimeDemo();
        varDemo();
        collectionFactories();
        recordsDemo();
        sealedDemo();
        streamEnhancements();
    }
}
