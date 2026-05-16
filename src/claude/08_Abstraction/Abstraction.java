/**
 * ============================================================
 *  TOPIC: Abstraction in Java
 * ============================================================
 *
 * Abstraction = Hiding internal implementation details and
 * exposing only the essential interface to the user.
 *
 * Achieved via:
 *   1. Abstract Classes  (0% to 100% abstraction)
 *   2. Interfaces        (100% abstraction — until Java 8)
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW: Abstract Class
 *  - Declared with 'abstract' keyword.
 *  - Cannot be instantiated (cannot use new AbstractClass()).
 *  - CAN have abstract methods (no body) AND concrete methods.
 *  - CAN have constructors (called via super() from subclass).
 *  - CAN have instance fields.
 *  - Subclass MUST implement ALL abstract methods,
 *    or the subclass must also be declared abstract.
 *  - An abstract class CAN have 0 abstract methods.
 *
 *  Abstract Class vs Interface:
 *  ┌───────────────────┬───────────────┬──────────────────┐
 *  │ Feature           │ Abstract Class│ Interface        │
 *  ├───────────────────┼───────────────┼──────────────────┤
 *  │ Instantiate       │ No            │ No               │
 *  │ Constructors      │ Yes           │ No               │
 *  │ Instance fields   │ Yes           │ No (only static) │
 *  │ Access modifiers  │ Any           │ public only      │
 *  │ Multiple inherit  │ No (one class)│ Yes (multiple)   │
 *  │ Abstract methods  │ Optional      │ Yes (by default) │
 *  │ Concrete methods  │ Yes           │ Yes (default/static)│
 *  └───────────────────┴───────────────┴──────────────────┘
 *
 *  When to use ABSTRACT CLASS:
 *    - Shared state (fields) among related classes
 *    - Partial implementation to reuse
 *    - Close relationship between classes (IS-A strong)
 *  When to use INTERFACE:
 *    - Unrelated classes sharing capability (Comparable, Runnable)
 *    - Multiple inheritance needed
 *    - Define a pure contract (API)
 * ╚══════════════════════════════════════════════════════════╝
 */
public class Abstraction {

    // ═══════════════════════════════════════════════════════
    // 1. ABSTRACT CLASS — Vehicle hierarchy
    // ═══════════════════════════════════════════════════════
    static abstract class Vehicle {
        // Instance fields (abstract class CAN have these)
        protected String brand;
        protected int    year;
        protected double fuelLevel;   // 0.0 to 100.0

        // Constructor (abstract class CAN have this)
        public Vehicle(String brand, int year) {
            this.brand     = brand;
            this.year      = year;
            this.fuelLevel = 100.0;
        }

        // Abstract methods — MUST be implemented by subclasses
        public abstract void start();
        public abstract void stop();
        public abstract double fuelEfficiency();   // km per litre/kWh
        public abstract String getFuelType();

        // Concrete methods — shared by all vehicles
        public void refuel(double amount) {
            fuelLevel = Math.min(100.0, fuelLevel + amount);
            System.out.printf("%s refuelled to %.1f%%%n", brand, fuelLevel);
        }

        public double estimatedRange() {
            return (fuelLevel / 100.0) * fuelEfficiency() * 50; // assume 50 unit tank
        }

        public void displayInfo() {
            System.out.printf("[%s %d] Fuel: %s (%.1f%%) Range: %.1f km%n",
                brand, year, getFuelType(), fuelLevel, estimatedRange());
        }

        @Override public String toString() {
            return brand + "(" + year + ")";
        }
    }

    static class PetrolCar extends Vehicle {
        private int engineCC;

        public PetrolCar(String brand, int year, int cc) {
            super(brand, year);
            this.engineCC = cc;
        }

        @Override public void start() {
            System.out.println(brand + ": Vroom! Petrol engine started.");
        }

        @Override public void stop() {
            System.out.println(brand + ": Engine turned off.");
        }

        @Override public double fuelEfficiency() { return 15.0; }  // 15 km/litre
        @Override public String getFuelType()    { return "Petrol"; }
    }

    static class ElectricCar extends Vehicle {
        private int batteryCapacity;

        public ElectricCar(String brand, int year, int batteryKWh) {
            super(brand, year);
            this.batteryCapacity = batteryKWh;
        }

        @Override public void start() {
            System.out.println(brand + ": Whisper-quiet electric motor on.");
        }

        @Override public void stop() {
            System.out.println(brand + ": Motors off. Regenerative braking saved energy.");
        }

        @Override public double fuelEfficiency() { return 6.0; }  // km/kWh
        @Override public String getFuelType()    { return "Electric"; }

        // Subclass-specific method
        public void chargeBattery() {
            fuelLevel = 100.0;
            System.out.println(brand + ": Battery charged to 100%");
        }
    }

    static class HybridCar extends Vehicle {
        public HybridCar(String brand, int year) { super(brand, year); }

        @Override public void start() {
            System.out.println(brand + ": Smart hybrid system engaged.");
        }

        @Override public void stop() {
            System.out.println(brand + ": Hybrid system off. Captured braking energy.");
        }

        @Override public double fuelEfficiency() { return 22.0; }  // 22 km/litre
        @Override public String getFuelType()    { return "Petrol+Electric"; }
    }

    // ═══════════════════════════════════════════════════════
    // 2. ABSTRACT CLASS with TEMPLATE METHOD PATTERN
    // ═══════════════════════════════════════════════════════
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  TEMPLATE METHOD PATTERN (classic use of abstract class):
     *  - Base class defines the SKELETON of an algorithm.
     *  - Some steps are abstract (subclasses fill in details).
     *  - The overall algorithm structure is fixed.
     *  - "Don't call us, we'll call you" (Hollywood principle)
     * ╚══════════════════════════════════════════════════════╝
     */
    static abstract class DataProcessor {
        // Template method — the algorithm skeleton (final = cannot override)
        public final void process() {
            readData();       // step 1 — abstract
            validateData();   // step 2 — concrete (default behaviour)
            processData();    // step 3 — abstract
            saveResults();    // step 4 — abstract
            System.out.println("--- Processing complete ---\n");
        }

        protected abstract void readData();
        protected abstract void processData();
        protected abstract void saveResults();

        // Default behaviour — subclasses can override if needed
        protected void validateData() {
            System.out.println("  [Default validation] Data validated.");
        }
    }

    static class CSVProcessor extends DataProcessor {
        @Override protected void readData()    { System.out.println("  [CSV] Reading CSV file..."); }
        @Override protected void processData() { System.out.println("  [CSV] Parsing rows & columns..."); }
        @Override protected void saveResults() { System.out.println("  [CSV] Saving to database..."); }
    }

    static class JSONProcessor extends DataProcessor {
        @Override protected void readData()    { System.out.println("  [JSON] Fetching JSON from API..."); }
        @Override protected void processData() { System.out.println("  [JSON] Deserialising objects..."); }
        @Override protected void saveResults() { System.out.println("  [JSON] Caching results in Redis..."); }
        @Override protected void validateData(){ System.out.println("  [JSON] Schema validation done."); }
    }

    // ═══════════════════════════════════════════════════════
    // 3. ABSTRACT FACTORY — creational use of abstraction
    // ═══════════════════════════════════════════════════════
    interface Button  { void render(); void onClick(); }
    interface TextBox { void render(); String getText(); }

    static abstract class UIFactory {
        public abstract Button  createButton(String label);
        public abstract TextBox createTextBox(String placeholder);

        // Template method
        public void buildLoginForm() {
            Button  btn = createButton("Login");
            TextBox usr = createTextBox("Username");
            TextBox pwd = createTextBox("Password");
            usr.render(); pwd.render(); btn.render();
        }
    }

    static class WindowsUIFactory extends UIFactory {
        @Override public Button createButton(String label) {
            return new Button() {
                public void render()   { System.out.println("[Win] Button: [" + label + "]"); }
                public void onClick()  { System.out.println("[Win] Button clicked: " + label); }
            };
        }
        @Override public TextBox createTextBox(String placeholder) {
            return new TextBox() {
                public void render()      { System.out.println("[Win] TextBox: _" + placeholder + "_"); }
                public String getText()   { return "windows_input"; }
            };
        }
    }

    static class MacUIFactory extends UIFactory {
        @Override public Button createButton(String label) {
            return new Button() {
                public void render()   { System.out.println("[Mac] Button: ⬜ " + label); }
                public void onClick()  { System.out.println("[Mac] Clicked: " + label); }
            };
        }
        @Override public TextBox createTextBox(String placeholder) {
            return new TextBox() {
                public void render()      { System.out.println("[Mac] TextField: ○ " + placeholder); }
                public String getText()   { return "mac_input"; }
            };
        }
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Abstraction in Java =====\n");

        // --- Abstract class hierarchy ---
        System.out.println("--- 1. Vehicle Abstract Class ---");
        // new Vehicle(...); // compile error — cannot instantiate abstract
        Vehicle[] fleet = {
            new PetrolCar("Honda", 2022, 1500),
            new ElectricCar("Tesla", 2023, 75),
            new HybridCar("Toyota Prius", 2023)
        };
        for (Vehicle v : fleet) {
            v.start();
            v.displayInfo();
            v.stop();
            System.out.println();
        }

        // --- Template Method Pattern ---
        System.out.println("--- 2. Template Method Pattern ---");
        DataProcessor csv  = new CSVProcessor();
        DataProcessor json = new JSONProcessor();
        System.out.println("Processing CSV:");
        csv.process();
        System.out.println("Processing JSON:");
        json.process();

        // --- Abstract Factory ---
        System.out.println("--- 3. Abstract Factory ---");
        UIFactory winFactory = new WindowsUIFactory();
        UIFactory macFactory = new MacUIFactory();
        System.out.println("Windows Login Form:");
        winFactory.buildLoginForm();
        System.out.println("\nMac Login Form:");
        macFactory.buildLoginForm();
    }
}
