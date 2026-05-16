/**
 * ============================================================
 *  TOPIC: Design Patterns in Java (GoF - Gang of Four)
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW: Categories
 *
 *  Creational: HOW objects are created
 *    Singleton, Factory, Abstract Factory, Builder, Prototype
 *
 *  Structural: HOW objects are composed
 *    Adapter, Bridge, Composite, Decorator, Facade, Flyweight, Proxy
 *
 *  Behavioral: HOW objects communicate
 *    Observer, Strategy, Command, Template Method, Iterator,
 *    Chain of Responsibility, State, Visitor
 *
 *  Patterns covered here:
 *  1. Singleton   (Creational)
 *  2. Factory     (Creational)
 *  3. Builder     (Creational)
 *  4. Observer    (Behavioral)
 *  5. Strategy    (Behavioral)
 *  6. Decorator   (Structural)
 * ╚══════════════════════════════════════════════════════════╝
 */

import java.util.*;
import java.util.function.*;

public class DesignPatterns {

    // ═══════════════════════════════════════════════════════
    // 1. SINGLETON — ensures only ONE instance
    // ═══════════════════════════════════════════════════════
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  Thread-safe Singleton using double-checked locking.
     *  'volatile' ensures visibility across threads.
     *  Enum Singleton (see Enums.java) is the best approach.
     * ╚══════════════════════════════════════════════════════╝
     */
    static class ConfigManager {
        private static volatile ConfigManager instance;
        private final Map<String, String> configs = new HashMap<>();

        private ConfigManager() {
            configs.put("db.url",  "jdbc:mysql://localhost/mydb");
            configs.put("app.env", "production");
            configs.put("timeout", "30");
        }

        public static ConfigManager getInstance() {
            if (instance == null) {                   // first check (no lock)
                synchronized (ConfigManager.class) {
                    if (instance == null) {           // second check (with lock)
                        instance = new ConfigManager();
                    }
                }
            }
            return instance;
        }

        public String get(String key) { return configs.getOrDefault(key, ""); }
        public void   set(String key, String val) { configs.put(key, val); }
    }

    // ═══════════════════════════════════════════════════════
    // 2. FACTORY METHOD — delegate object creation to subclasses
    // ═══════════════════════════════════════════════════════
    interface Notification {
        void send(String recipient, String message);
        String getType();
    }

    static class EmailNotification implements Notification {
        @Override public void send(String to, String msg) {
            System.out.println("[Email → " + to + "] " + msg);
        }
        @Override public String getType() { return "EMAIL"; }
    }

    static class SMSNotification implements Notification {
        @Override public void send(String to, String msg) {
            System.out.println("[SMS → " + to + "] " + msg);
        }
        @Override public String getType() { return "SMS"; }
    }

    static class PushNotification implements Notification {
        @Override public void send(String to, String msg) {
            System.out.println("[Push → " + to + "] " + msg);
        }
        @Override public String getType() { return "PUSH"; }
    }

    /*
     * ╔══════════════════════════════════════════════════════╗
     *  Factory Method: client code doesn't know which concrete
     *  class is created — depends on input.
     *  Open/Closed Principle: add new types without modifying factory.
     * ╚══════════════════════════════════════════════════════╝
     */
    static class NotificationFactory {
        public static Notification create(String type) {
            return switch (type.toUpperCase()) {
                case "EMAIL" -> new EmailNotification();
                case "SMS"   -> new SMSNotification();
                case "PUSH"  -> new PushNotification();
                default      -> throw new IllegalArgumentException("Unknown: " + type);
            };
        }
    }

    // ═══════════════════════════════════════════════════════
    // 3. BUILDER — step-by-step construction of complex objects
    // ═══════════════════════════════════════════════════════
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  Builder solves "telescoping constructor" problem.
     *  Makes object creation readable and flexible.
     *  Private constructor forces use of Builder.
     * ╚══════════════════════════════════════════════════════╝
     */
    static final class Pizza {
        private final String   size;         // required
        private final String   crustType;    // required
        private final boolean  cheese;
        private final boolean  pepperoni;
        private final boolean  mushrooms;
        private final boolean  olives;
        private final String   sauce;

        private Pizza(Builder b) {
            size      = b.size;
            crustType = b.crustType;
            cheese    = b.cheese;
            pepperoni = b.pepperoni;
            mushrooms = b.mushrooms;
            olives    = b.olives;
            sauce     = b.sauce;
        }

        @Override public String toString() {
            List<String> toppings = new ArrayList<>();
            if (cheese)    toppings.add("Cheese");
            if (pepperoni) toppings.add("Pepperoni");
            if (mushrooms) toppings.add("Mushrooms");
            if (olives)    toppings.add("Olives");
            return size + " " + crustType + " Pizza with " + sauce + " sauce"
                + (toppings.isEmpty() ? " (plain)" : " + " + String.join(", ", toppings));
        }

        static class Builder {
            private final String size;
            private final String crustType;
            private boolean  cheese    = false;
            private boolean  pepperoni = false;
            private boolean  mushrooms = false;
            private boolean  olives    = false;
            private String   sauce     = "Tomato";

            public Builder(String size, String crustType) {
                this.size = Objects.requireNonNull(size);
                this.crustType = Objects.requireNonNull(crustType);
            }

            public Builder cheese()    { cheese    = true; return this; }
            public Builder pepperoni() { pepperoni = true; return this; }
            public Builder mushrooms() { mushrooms = true; return this; }
            public Builder olives()    { olives    = true; return this; }
            public Builder sauce(String s) { sauce = s; return this; }
            public Pizza build()       { return new Pizza(this); }
        }
    }

    // ═══════════════════════════════════════════════════════
    // 4. OBSERVER — one-to-many event notification
    // ═══════════════════════════════════════════════════════
    interface EventListener<T> {
        void onEvent(T event);
    }

    static class EventBus<T> {
        private final List<EventListener<T>> listeners = new ArrayList<>();

        public void subscribe(EventListener<T> listener) { listeners.add(listener); }
        public void unsubscribe(EventListener<T> listener) { listeners.remove(listener); }

        public void publish(T event) {
            listeners.forEach(l -> l.onEvent(event));
        }
    }

    static class StockTicker {
        private String  symbol;
        private double  price;
        private final EventBus<StockTicker> bus = new EventBus<>();

        StockTicker(String sym, double price) { this.symbol=sym; this.price=price; }

        public void addListener(EventListener<StockTicker> l) { bus.subscribe(l); }

        public void updatePrice(double newPrice) {
            this.price = newPrice;
            bus.publish(this);   // notify all observers
        }

        public String getSymbol() { return symbol; }
        public double getPrice()  { return price;  }
        @Override public String toString() { return symbol + "=$" + price; }
    }

    // ═══════════════════════════════════════════════════════
    // 5. STRATEGY — encapsulate interchangeable algorithms
    // ═══════════════════════════════════════════════════════
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  Strategy allows swapping algorithms at runtime.
     *  Open/Closed: add new strategies without changing context.
     *  In Java 8+: strategy interfaces can be replaced by lambdas.
     * ╚══════════════════════════════════════════════════════╝
     */
    @FunctionalInterface
    interface SortStrategy<T> {
        void sort(List<T> list, Comparator<T> cmp);
    }

    static class DataSorter<T> {
        private SortStrategy<T> strategy;

        public DataSorter(SortStrategy<T> strategy) { this.strategy = strategy; }
        public void setStrategy(SortStrategy<T> s)  { this.strategy = s; }

        public void sort(List<T> list, Comparator<T> cmp) {
            strategy.sort(list, cmp);
        }
    }

    // Concrete strategies
    static <T> SortStrategy<T> bubbleSort() {
        return (list, cmp) -> {
            System.out.println("[BubbleSort]");
            for (int i = 0; i < list.size()-1; i++)
                for (int j = 0; j < list.size()-1-i; j++)
                    if (cmp.compare(list.get(j), list.get(j+1)) > 0)
                        Collections.swap(list, j, j+1);
        };
    }

    static <T> SortStrategy<T> javaSort() {
        return (list, cmp) -> { System.out.println("[TimSort]"); list.sort(cmp); };
    }

    // ═══════════════════════════════════════════════════════
    // 6. DECORATOR — add behaviour dynamically
    // ═══════════════════════════════════════════════════════
    interface TextProcessor {
        String process(String text);
    }

    static class PlainText implements TextProcessor {
        @Override public String process(String text) { return text; }
    }

    // Base decorator
    static abstract class TextDecorator implements TextProcessor {
        protected final TextProcessor wrapped;
        TextDecorator(TextProcessor wrapped) { this.wrapped = wrapped; }
    }

    static class UpperCaseDecorator extends TextDecorator {
        UpperCaseDecorator(TextProcessor w) { super(w); }
        @Override public String process(String t) { return wrapped.process(t).toUpperCase(); }
    }

    static class TrimDecorator extends TextDecorator {
        TrimDecorator(TextProcessor w) { super(w); }
        @Override public String process(String t) { return wrapped.process(t).trim(); }
    }

    static class ExclamDecorator extends TextDecorator {
        ExclamDecorator(TextProcessor w) { super(w); }
        @Override public String process(String t) { return wrapped.process(t) + "!!!"; }
    }

    static class PrefixDecorator extends TextDecorator {
        private final String prefix;
        PrefixDecorator(TextProcessor w, String prefix) { super(w); this.prefix=prefix; }
        @Override public String process(String t) { return prefix + wrapped.process(t); }
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Design Patterns =====\n");

        // --- Singleton ---
        System.out.println("--- 1. Singleton ---");
        ConfigManager cfg1 = ConfigManager.getInstance();
        ConfigManager cfg2 = ConfigManager.getInstance();
        System.out.println("Same instance: " + (cfg1 == cfg2));
        System.out.println("db.url: " + cfg1.get("db.url"));
        cfg1.set("app.env", "staging");
        System.out.println("env after update (from cfg2): " + cfg2.get("app.env"));

        // --- Factory ---
        System.out.println("\n--- 2. Factory Method ---");
        String[] channels = {"EMAIL", "SMS", "PUSH"};
        for (String ch : channels) {
            Notification n = NotificationFactory.create(ch);
            n.send("user@example.com", "Welcome! [via " + n.getType() + "]");
        }

        // --- Builder ---
        System.out.println("\n--- 3. Builder ---");
        Pizza p1 = new Pizza.Builder("Large", "Thin")
            .cheese().pepperoni().sauce("BBQ").build();
        Pizza p2 = new Pizza.Builder("Medium", "Thick")
            .mushrooms().olives().cheese().build();
        Pizza p3 = new Pizza.Builder("Small", "Stuffed").build();
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        // --- Observer ---
        System.out.println("\n--- 4. Observer ---");
        StockTicker tesla = new StockTicker("TSLA", 250.0);
        tesla.addListener(s -> System.out.println("[Investor] Portfolio updated: " + s));
        tesla.addListener(s -> System.out.printf("[Alert] %s crossed $200! Now: $%.2f%n",
            s.getSymbol(), s.getPrice()));
        tesla.addListener(s -> System.out.println("[Logger] Stock event: " + s));

        tesla.updatePrice(260.50);
        tesla.updatePrice(195.75);

        // --- Strategy ---
        System.out.println("\n--- 5. Strategy ---");
        List<Integer> data1 = new ArrayList<>(Arrays.asList(5,3,8,1,9,2));
        List<Integer> data2 = new ArrayList<>(Arrays.asList(5,3,8,1,9,2));

        DataSorter<Integer> sorter = new DataSorter<>(bubbleSort());
        sorter.sort(data1, Integer::compareTo);
        System.out.println("BubbleSort: " + data1);

        sorter.setStrategy(javaSort());
        sorter.sort(data2, Integer::compareTo);
        System.out.println("TimSort:    " + data2);

        // Lambda as strategy
        sorter.setStrategy((list, cmp) -> {
            System.out.println("[ReverseSort]");
            list.sort(cmp.reversed());
        });
        List<Integer> data3 = new ArrayList<>(Arrays.asList(5,3,8,1,9,2));
        sorter.sort(data3, Integer::compareTo);
        System.out.println("ReverseSort: " + data3);

        // --- Decorator ---
        System.out.println("\n--- 6. Decorator ---");
        TextProcessor plain = new PlainText();
        System.out.println("plain: " + plain.process("  hello world  "));

        TextProcessor trimUpper = new UpperCaseDecorator(new TrimDecorator(plain));
        System.out.println("trim+upper: " + trimUpper.process("  hello world  "));

        TextProcessor full = new ExclamDecorator(
            new PrefixDecorator(
                new UpperCaseDecorator(new TrimDecorator(plain)),
                ">> "));
        System.out.println("full pipeline: " + full.process("  hello world  "));
    }
}
