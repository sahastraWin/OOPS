/**
 * ============================================================
 *  TOPIC: Generics in Java
 * ============================================================
 *
 * Generics enable writing TYPE-SAFE, REUSABLE code.
 * The type parameter is specified at compile time.
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW:
 *
 *  Type Parameter naming conventions:
 *    T → Type           E → Element (Collections)
 *    K → Key            V → Value (Map)
 *    N → Number         R → Return type
 *    ? → Wildcard
 *
 *  Bounded Type Parameters:
 *    <T extends Comparable<T>>  → T must be Comparable
 *    <T extends A & B>          → T must extend A AND implement B
 *
 *  Wildcards:
 *    <?>                → unknown type (read-only)
 *    <? extends T>      → upper bounded: T or subtype (Producer)
 *    <? super T>        → lower bounded: T or supertype (Consumer)
 *    PECS Principle: Producer → Extends, Consumer → Super
 *
 *  TYPE ERASURE (critical for interviews!):
 *    Generics are a COMPILE-TIME feature.
 *    At runtime, all generic type info is ERASED.
 *    List<String> and List<Integer> are both List at runtime.
 *    Cannot do: new T(), instanceof T, T.class, new T[]
 *
 *  Generic methods vs Generic classes:
 *    Generic class: type param in class declaration
 *    Generic method: type param before return type
 * ╚══════════════════════════════════════════════════════════╝
 */

import java.util.*;
import java.util.function.*;

public class Generics {

    // ═══════════════════════════════════════════════════════
    // 1. GENERIC CLASS
    // ═══════════════════════════════════════════════════════
    static class Pair<A, B> {
        private final A first;
        private final B second;

        public Pair(A first, B second) {
            this.first = first; this.second = second;
        }

        public A getFirst()  { return first; }
        public B getSecond() { return second; }

        public Pair<B, A> swap() { return new Pair<>(second, first); }

        @Override public String toString() {
            return "(" + first + ", " + second + ")";
        }

        // Static factory method (better than constructor in many cases)
        public static <X, Y> Pair<X, Y> of(X x, Y y) { return new Pair<>(x, y); }
    }

    // Generic Stack
    static class GenericStack<T> {
        private final List<T> elements = new ArrayList<>();
        private final int     capacity;

        public GenericStack(int capacity) { this.capacity = capacity; }

        public void push(T item) {
            if (elements.size() == capacity) throw new RuntimeException("Stack overflow");
            elements.add(item);
        }

        public T pop() {
            if (isEmpty()) throw new RuntimeException("Stack underflow");
            return elements.remove(elements.size() - 1);
        }

        public T peek() {
            if (isEmpty()) throw new RuntimeException("Stack empty");
            return elements.get(elements.size() - 1);
        }

        public boolean isEmpty() { return elements.isEmpty(); }
        public int     size()    { return elements.size(); }

        @Override public String toString() { return "Stack" + elements; }
    }

    // ═══════════════════════════════════════════════════════
    // 2. GENERIC METHODS
    // ═══════════════════════════════════════════════════════
    static class GenericUtils {

        // Generic method: type param before return type
        public static <T> void swap(T[] arr, int i, int j) {
            T temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
        }

        public static <T> List<T> repeat(T item, int n) {
            List<T> result = new ArrayList<>();
            for (int i = 0; i < n; i++) result.add(item);
            return result;
        }

        // Bounded type parameter: T must implement Comparable
        public static <T extends Comparable<T>> T max(T a, T b, T c) {
            T max = (a.compareTo(b) >= 0) ? a : b;
            return (max.compareTo(c) >= 0) ? max : c;
        }

        // Multiple bounds: T extends class AND implements interface
        public static <T extends Number & Comparable<T>> T clamp(T val, T lo, T hi) {
            if (val.compareTo(lo) < 0) return lo;
            if (val.compareTo(hi) > 0) return hi;
            return val;
        }
    }

    // ═══════════════════════════════════════════════════════
    // 3. WILDCARDS
    // ═══════════════════════════════════════════════════════
    static class WildcardDemo {

        /*
         * ╔══════════════════════════════════════════════╗
         *  Upper Bounded: <? extends Number>
         *  - Can READ elements as Number.
         *  - CANNOT add elements (except null).
         *  - Use when: you're a PRODUCER of data.
         *  PECS: Producer Extends
         * ╚══════════════════════════════════════════════╝
         */
        public static double sumList(List<? extends Number> list) {
            double sum = 0;
            for (Number n : list) sum += n.doubleValue();
            return sum;
        }

        /*
         * ╔══════════════════════════════════════════════╗
         *  Lower Bounded: <? super Integer>
         *  - Can ADD Integer or subtype elements.
         *  - Can only READ as Object.
         *  - Use when: you're a CONSUMER of data.
         *  PECS: Consumer Super
         * ╚══════════════════════════════════════════════╝
         */
        public static void addNumbers(List<? super Integer> list, int n) {
            for (int i = 1; i <= n; i++) list.add(i);
        }

        /*
         * ╔══════════════════════════════════════════════╗
         *  Unbounded Wildcard: <?>
         *  - Read-only access; elements treated as Object.
         *  - Use when: the method works with any type.
         * ╚══════════════════════════════════════════════╝
         */
        public static void printList(List<?> list) {
            System.out.print("[");
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + (i < list.size()-1 ? ", " : ""));
            }
            System.out.println("]");
        }
    }

    // ═══════════════════════════════════════════════════════
    // 4. GENERIC INTERFACE + IMPLEMENTATION
    // ═══════════════════════════════════════════════════════
    interface Repository<T, ID> {
        void   save(T entity);
        T      findById(ID id);
        void   delete(ID id);
        List<T> findAll();
    }

    static class User {
        int id; String name;
        User(int id, String name) { this.id = id; this.name = name; }
        @Override public String toString() { return "User(" + id + "," + name + ")"; }
    }

    static class UserRepository implements Repository<User, Integer> {
        private Map<Integer, User> store = new HashMap<>();

        @Override public void   save(User u)        { store.put(u.id, u); System.out.println("Saved: " + u); }
        @Override public User   findById(Integer id) { return store.get(id); }
        @Override public void   delete(Integer id)  { store.remove(id); System.out.println("Deleted id=" + id); }
        @Override public List<User> findAll()       { return new ArrayList<>(store.values()); }
    }

    // ═══════════════════════════════════════════════════════
    // 5. TYPE ERASURE demonstration
    // ═══════════════════════════════════════════════════════
    /*
     * ╔══════════════════════════════════════════════════════╗
     *  TYPE ERASURE:
     *  At compile time: List<String>, List<Integer> — different types
     *  At runtime:      both become just List (raw type)
     *  The compiler inserts casts automatically.
     *  This is why:
     *    - new T() is illegal
     *    - instanceof T is illegal
     *    - T.class is illegal
     *    - Cannot create generic array: new T[10] — illegal
     *  Workaround for new T(): pass Class<T> as constructor arg.
     * ╚══════════════════════════════════════════════════════╝
     */
    static class TypeErasureDemo<T> {
        private final Class<T> type;

        public TypeErasureDemo(Class<T> type) { this.type = type; }

        public T createInstance() throws Exception {
            // Cannot do: return new T(); — use reflection workaround
            return type.getDeclaredConstructor().newInstance();
        }

        public boolean isInstance(Object obj) {
            return type.isInstance(obj);
        }
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Generics =====\n");

        // --- Generic class ---
        System.out.println("--- 1. Generic Class ---");
        Pair<String, Integer> p1 = Pair.of("Alice", 25);
        Pair<Integer, String> p2 = Pair.of(42, "answer");
        System.out.println("p1 = " + p1 + "  swapped = " + p1.swap());
        System.out.println("p2 = " + p2);

        GenericStack<String> strStack = new GenericStack<>(5);
        strStack.push("Java"); strStack.push("Generics"); strStack.push("Rock");
        System.out.println(strStack);
        System.out.println("Pop: " + strStack.pop());
        System.out.println(strStack);

        GenericStack<Integer> intStack = new GenericStack<>(3);
        intStack.push(10); intStack.push(20); intStack.push(30);
        System.out.println(intStack);

        // --- Generic methods ---
        System.out.println("\n--- 2. Generic Methods ---");
        Integer[] nums = {5, 2, 8, 1, 9};
        GenericUtils.swap(nums, 0, 4);
        System.out.println("After swap[0,4]: " + Arrays.toString(nums));

        System.out.println("repeat('x', 4) = " + GenericUtils.repeat("x", 4));
        System.out.println("max(3,7,5)     = " + GenericUtils.max(3, 7, 5));
        System.out.println("max(a,z,m)     = " + GenericUtils.max('a', 'z', 'm'));
        System.out.println("clamp(15,0,10) = " + GenericUtils.clamp(15, 0, 10));
        System.out.println("clamp(-5,0,10) = " + GenericUtils.clamp(-5, 0, 10));

        // --- Wildcards ---
        System.out.println("\n--- 3. Wildcards ---");
        List<Integer>  ints    = Arrays.asList(1, 2, 3, 4, 5);
        List<Double>   doubles = Arrays.asList(1.1, 2.2, 3.3);
        System.out.println("Sum ints   : " + WildcardDemo.sumList(ints));
        System.out.println("Sum doubles: " + WildcardDemo.sumList(doubles));

        List<Number> numList = new ArrayList<>();
        WildcardDemo.addNumbers(numList, 5);
        WildcardDemo.printList(numList);

        WildcardDemo.printList(Arrays.asList("a", "b", "c"));
        WildcardDemo.printList(Arrays.asList(true, false, true));

        // --- Generic Interface ---
        System.out.println("\n--- 4. Generic Repository Interface ---");
        UserRepository repo = new UserRepository();
        repo.save(new User(1, "Alice"));
        repo.save(new User(2, "Bob"));
        repo.save(new User(3, "Charlie"));
        System.out.println("findById(2): " + repo.findById(2));
        System.out.println("findAll: " + repo.findAll());
        repo.delete(2);
        System.out.println("findAll after delete: " + repo.findAll());

        // --- Type erasure workaround ---
        System.out.println("\n--- 5. Type Erasure workaround (Class<T>) ---");
        List<String>  strList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();
        System.out.println("Same class at runtime? " +
            (strList.getClass() == intList.getClass()));   // true! (type erasure)
    }
}
