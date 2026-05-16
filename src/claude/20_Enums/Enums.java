/**
 * ============================================================
 *  TOPIC: Enums in Java
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW:
 *  - enum is a special class in Java (extends java.lang.Enum)
 *  - All enum constants are implicitly: public static final
 *  - Enums can have fields, constructors (private), and methods
 *  - Enum constructor is always private (called once per constant)
 *  - Enums can implement interfaces
 *  - Enums CANNOT extend a class (already extends Enum)
 *  - Enums are singletons by JVM guarantee (thread-safe)
 *  - Enum is the BEST Singleton implementation in Java!
 *  - switch works with enums
 *  - Built-in methods: name(), ordinal(), values(), valueOf()
 *  - EnumSet and EnumMap are efficient specialized collections
 * ╚══════════════════════════════════════════════════════════╝
 */

import java.util.*;

public class Enums {

    // ─── 1. Basic Enum ───────────────────────────────────────
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

        public boolean isWeekend() {
            return this == SATURDAY || this == SUNDAY;
        }
    }

    // ─── 2. Enum with fields + constructor + methods ─────────
    enum Planet {
        MERCURY(3.303e+23, 2.4397e6),
        VENUS  (4.869e+24, 6.0518e6),
        EARTH  (5.976e+24, 6.37814e6),
        MARS   (6.421e+23, 3.3972e6);

        private final double mass;    // kg
        private final double radius;  // m
        private static final double G = 6.67300E-11;

        /*
         * ╔══════════════════════════════════════════╗
         *  Enum constructor is ALWAYS private (or package-private).
         *  Called once per constant at class load time.
         * ╚══════════════════════════════════════════╝
         */
        Planet(double mass, double radius) {
            this.mass = mass; this.radius = radius;
        }

        public double surfaceGravity() {
            return G * mass / (radius * radius);
        }

        public double surfaceWeight(double otherMass) {
            return otherMass * surfaceGravity();
        }
    }

    // ─── 3. Enum implementing an interface ───────────────────
    interface Operation {
        double apply(double x, double y);
    }

    enum MathOp implements Operation {
        ADD    { @Override public double apply(double x, double y) { return x + y; } },
        SUB    { @Override public double apply(double x, double y) { return x - y; } },
        MULT   { @Override public double apply(double x, double y) { return x * y; } },
        DIVIDE {
            @Override public double apply(double x, double y) {
                if (y == 0) throw new ArithmeticException("Division by zero");
                return x / y;
            }
        };

        public String symbol() { return name().substring(0,1); }
    }

    // ─── 4. Enum as Singleton ────────────────────────────────
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  ENUM SINGLETON is the BEST Singleton pattern because:
     *  1. Thread-safe by JVM class loading guarantee
     *  2. Serialization-safe (no extra getInstance() needed)
     *  3. Reflection-proof (cannot instantiate via reflection)
     *  Recommended by Effective Java (Joshua Bloch).
     * ╚══════════════════════════════════════════════════════╝
     */
    enum AppConfig {
        INSTANCE;

        private String dbUrl    = "jdbc:mysql://localhost:3306/db";
        private int    maxConns = 10;

        public String getDbUrl()    { return dbUrl; }
        public int    getMaxConns() { return maxConns; }
        public void   setDbUrl(String url) { this.dbUrl = url; }
    }

    // ─── 5. EnumSet and EnumMap ──────────────────────────────
    enum Permission { READ, WRITE, EXECUTE, DELETE, ADMIN }

    public static void main(String[] args) {
        System.out.println("===== Enums =====\n");

        // --- Basic enum ---
        System.out.println("--- 1. Basic Enum ---");
        Day today = Day.WEDNESDAY;
        System.out.println("Today: " + today);
        System.out.println("name(): "    + today.name());
        System.out.println("ordinal(): " + today.ordinal());
        System.out.println("isWeekend: " + today.isWeekend());
        System.out.println("Saturday isWeekend: " + Day.SATURDAY.isWeekend());

        System.out.println("All days:");
        for (Day d : Day.values()) System.out.print(d + " ");
        System.out.println();

        Day parsed = Day.valueOf("FRIDAY");
        System.out.println("valueOf(FRIDAY): " + parsed);

        // Switch on enum
        switch (today) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                System.out.println(today + " is a weekday."); break;
            default:
                System.out.println(today + " is weekend.");
        }

        // --- Enum with fields ---
        System.out.println("\n--- 2. Enum with Fields (Planet) ---");
        double earthWeight = 75.0;
        double mass = earthWeight / Planet.EARTH.surfaceGravity();
        for (Planet p : Planet.values()) {
            System.out.printf("Weight on %s: %.2f N%n", p, p.surfaceWeight(mass));
        }

        // --- Enum implementing interface ---
        System.out.println("\n--- 3. Enum implementing Interface ---");
        double x = 10, y = 3;
        for (MathOp op : MathOp.values()) {
            System.out.printf("%.0f %s %.0f = %.2f%n", x, op.name(), y, op.apply(x, y));
        }

        // --- Enum Singleton ---
        System.out.println("\n--- 4. Enum Singleton ---");
        AppConfig cfg1 = AppConfig.INSTANCE;
        AppConfig cfg2 = AppConfig.INSTANCE;
        System.out.println("Same instance: " + (cfg1 == cfg2));
        System.out.println("DB URL: " + cfg1.getDbUrl());
        cfg1.setDbUrl("jdbc:postgresql://prod:5432/db");
        System.out.println("After update: " + cfg2.getDbUrl());  // same instance

        // --- EnumSet and EnumMap ---
        System.out.println("\n--- 5. EnumSet and EnumMap ---");
        EnumSet<Permission> adminPerms = EnumSet.allOf(Permission.class);
        EnumSet<Permission> userPerms  = EnumSet.of(Permission.READ, Permission.WRITE);
        EnumSet<Permission> readOnly   = EnumSet.of(Permission.READ);

        System.out.println("Admin permissions: " + adminPerms);
        System.out.println("User permissions:  " + userPerms);
        System.out.println("ReadOnly:          " + readOnly);
        System.out.println("User has DELETE?   " + userPerms.contains(Permission.DELETE));

        EnumMap<Day, String> schedule = new EnumMap<>(Day.class);
        schedule.put(Day.MONDAY,    "Team standup");
        schedule.put(Day.WEDNESDAY, "Architecture review");
        schedule.put(Day.FRIDAY,    "Sprint review");
        schedule.forEach((day, event) -> System.out.println(day + ": " + event));
    }
}
