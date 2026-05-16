/**
 * ============================================================
 *  TOPIC: Collections Framework in Java
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW: Collections Hierarchy
 *
 *  Iterable
 *    └── Collection
 *          ├── List     → ordered, duplicates allowed
 *          │    ├── ArrayList   (dynamic array, O(1) get, O(n) insert)
 *          │    ├── LinkedList  (doubly-linked, O(1) insert/delete at ends)
 *          │    └── Vector      (synchronized ArrayList, legacy)
 *          │         └── Stack  (legacy, use Deque instead)
 *          ├── Set      → no duplicates
 *          │    ├── HashSet      (O(1) ops, no order)
 *          │    ├── LinkedHashSet(insertion order)
 *          │    └── TreeSet      (sorted order, O(log n))
 *          └── Queue    → FIFO
 *               ├── LinkedList  (also implements Deque)
 *               ├── PriorityQueue (min-heap by default)
 *               └── Deque (double-ended queue)
 *                    └── ArrayDeque (preferred over Stack/LinkedList)
 *
 *  Map (NOT in Collection hierarchy):
 *    ├── HashMap        (O(1) ops, no order, allows null key/value)
 *    ├── LinkedHashMap  (insertion or access order)
 *    ├── TreeMap        (sorted by key, O(log n))
 *    ├── Hashtable      (synchronized, legacy, no null)
 *    └── ConcurrentHashMap (thread-safe, modern)
 *
 *  fail-fast vs fail-safe iterators:
 *  - fail-fast (ArrayList, HashMap): throws ConcurrentModificationException
 *    if collection modified during iteration (except via iterator.remove())
 *  - fail-safe (CopyOnWriteArrayList, ConcurrentHashMap): works on a copy
 * ╚══════════════════════════════════════════════════════════╝
 */

import java.util.*;
import java.util.stream.*;

public class CollectionsFramework {

    // ═══════════════════════════════════════════════════════
    // 1. LIST — ArrayList vs LinkedList
    // ═══════════════════════════════════════════════════════
    static void listDemo() {
        System.out.println("--- 1. List ---");

        // ArrayList: backed by array; fast random access O(1), slow insert/delete O(n)
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Banana");
        arrayList.add("Apple");
        arrayList.add("Cherry");
        arrayList.add(1, "Mango");       // insert at index 1
        arrayList.add("Apple");          // duplicates allowed
        System.out.println("ArrayList: " + arrayList);
        System.out.println("get(2): " + arrayList.get(2));
        System.out.println("contains(Apple): " + arrayList.contains("Apple"));
        arrayList.remove("Apple");       // removes first occurrence
        System.out.println("After remove Apple: " + arrayList);

        // LinkedList: doubly linked list; O(1) insert/delete at ends, O(n) random access
        LinkedList<Integer> linked = new LinkedList<>();
        linked.addFirst(10);
        linked.addLast(20);
        linked.addFirst(5);
        linked.add(30);
        System.out.println("LinkedList: " + linked);
        System.out.println("peekFirst: " + linked.peekFirst());
        System.out.println("pollLast: "  + linked.pollLast());
        System.out.println("After pollLast: " + linked);

        /*
         * ╔══════════════════════════════════════════════╗
         *  ArrayList vs LinkedList:
         *  Use ArrayList when: frequent random access, iteration
         *  Use LinkedList when: frequent insert/delete at ends/middle
         *  In practice: ArrayList is faster for most use cases
         *  due to CPU cache locality (contiguous memory).
         * ╚══════════════════════════════════════════════╝
         */
    }

    // ═══════════════════════════════════════════════════════
    // 2. SET — HashSet, LinkedHashSet, TreeSet
    // ═══════════════════════════════════════════════════════
    static void setDemo() {
        System.out.println("\n--- 2. Set ---");

        // HashSet: no order, O(1) add/contains/remove
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Banana"); hashSet.add("Apple"); hashSet.add("Cherry");
        hashSet.add("Apple");  // duplicate — ignored
        System.out.println("HashSet (no order): " + hashSet);

        // LinkedHashSet: insertion order maintained
        Set<String> linkedSet = new LinkedHashSet<>();
        linkedSet.add("Banana"); linkedSet.add("Apple"); linkedSet.add("Cherry");
        System.out.println("LinkedHashSet (insertion order): " + linkedSet);

        // TreeSet: sorted order, O(log n)
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(50); treeSet.add(10); treeSet.add(30); treeSet.add(20);
        System.out.println("TreeSet (sorted): " + treeSet);

        // TreeSet with custom comparator (reverse)
        TreeSet<String> reverseTree = new TreeSet<>(Comparator.reverseOrder());
        reverseTree.addAll(Arrays.asList("Banana", "Apple", "Cherry", "Date"));
        System.out.println("TreeSet (reverse): " + reverseTree);
        System.out.println("First: " + reverseTree.first() + " Last: " + reverseTree.last());

        // Set operations
        Set<Integer> setA = new HashSet<>(Arrays.asList(1,2,3,4,5));
        Set<Integer> setB = new HashSet<>(Arrays.asList(4,5,6,7,8));

        Set<Integer> union = new HashSet<>(setA);
        union.addAll(setB);

        Set<Integer> intersection = new HashSet<>(setA);
        intersection.retainAll(setB);

        Set<Integer> difference = new HashSet<>(setA);
        difference.removeAll(setB);

        System.out.println("A=" + setA + " B=" + setB);
        System.out.println("Union       : " + new TreeSet<>(union));
        System.out.println("Intersection: " + intersection);
        System.out.println("Difference  : " + difference);
    }

    // ═══════════════════════════════════════════════════════
    // 3. MAP — HashMap, LinkedHashMap, TreeMap
    // ═══════════════════════════════════════════════════════
    static void mapDemo() {
        System.out.println("\n--- 3. Map ---");

        // HashMap: O(1) get/put/remove, no order, allows null key
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice",  95);
        scores.put("Bob",    82);
        scores.put("Charlie",88);
        scores.put("Alice",  97);   // overwrites previous value
        scores.put(null, 0);         // null key allowed
        System.out.println("HashMap: " + scores);
        System.out.println("Alice's score: " + scores.get("Alice"));
        System.out.println("Contains Bob: " + scores.containsKey("Bob"));

        // getOrDefault, putIfAbsent, computeIfAbsent (Java 8)
        System.out.println("Diana (default): " + scores.getOrDefault("Diana", -1));
        scores.putIfAbsent("Bob", 100);   // won't overwrite, Bob exists
        scores.putIfAbsent("Diana", 79);  // will add
        System.out.println("After putIfAbsent: " + scores.get("Bob") + " Diana=" + scores.get("Diana"));

        // Iterating a Map
        System.out.println("Iterating entrySet:");
        scores.entrySet().stream()
            .filter(e -> e.getKey() != null)
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .forEach(e -> System.out.println("  " + e.getKey() + " → " + e.getValue()));

        // LinkedHashMap: maintains insertion order
        Map<String, String> capitals = new LinkedHashMap<>();
        capitals.put("India", "New Delhi");
        capitals.put("USA", "Washington DC");
        capitals.put("Japan", "Tokyo");
        System.out.println("LinkedHashMap: " + capitals);

        // TreeMap: sorted by key
        Map<String, Integer> freq = new TreeMap<>();
        "hello world hello java world java java".chars()
            .mapToObj(c -> String.valueOf((char)c))
            .forEach(w -> freq.merge(w, 1, Integer::sum));
        // Word frequency with TreeMap (char frequency here)
        TreeMap<Integer, String> inventory = new TreeMap<>();
        inventory.put(3, "Banana"); inventory.put(1, "Apple"); inventory.put(5, "Cherry");
        System.out.println("TreeMap (sorted by key): " + inventory);
        System.out.println("firstKey=" + inventory.firstKey() + " lastKey=" + inventory.lastKey());

        /*
         * ╔══════════════════════════════════════════════╗
         *  HashMap internal working (interview favourite):
         *  - Backed by array of Node<K,V> (buckets)
         *  - hashCode() determines the bucket index
         *  - equals() resolves collisions in same bucket (linked list or tree)
         *  - Java 8+: bucket becomes a Red-Black Tree when size ≥ 8
         *  - Load factor (default 0.75): when 75% full → resize (double array)
         *  - Initial capacity: 16 (default)
         * ╚══════════════════════════════════════════════╝
         */
    }

    // ═══════════════════════════════════════════════════════
    // 4. QUEUE and DEQUE
    // ═══════════════════════════════════════════════════════
    static void queueDemo() {
        System.out.println("\n--- 4. Queue & Deque ---");

        // Queue (FIFO) — prefer ArrayDeque over LinkedList for queue
        Queue<String> queue = new ArrayDeque<>();
        queue.offer("First");   // offer (no exception on failure)
        queue.offer("Second");
        queue.offer("Third");
        System.out.println("Queue: " + queue);
        System.out.println("peek: " + queue.peek());    // view head, no remove
        System.out.println("poll: " + queue.poll());    // remove and return head
        System.out.println("After poll: " + queue);

        // PriorityQueue: min-heap by default
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.addAll(Arrays.asList(5, 1, 8, 3, 7, 2));
        System.out.print("PriorityQueue (min-heap) poll order: ");
        while (!minHeap.isEmpty()) System.out.print(minHeap.poll() + " ");
        System.out.println();

        // Max-heap with reverseOrder
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.addAll(Arrays.asList(5, 1, 8, 3, 7, 2));
        System.out.print("PriorityQueue (max-heap) poll order: ");
        while (!maxHeap.isEmpty()) System.out.print(maxHeap.poll() + " ");
        System.out.println();

        // Deque as Stack (LIFO) — preferred over java.util.Stack
        Deque<String> stack = new ArrayDeque<>();
        stack.push("Bottom"); stack.push("Middle"); stack.push("Top");
        System.out.println("Deque as Stack: " + stack);
        System.out.println("pop: " + stack.pop());
        System.out.println("peek: " + stack.peek());
    }

    // ═══════════════════════════════════════════════════════
    // 5. COLLECTIONS UTILITY CLASS
    // ═══════════════════════════════════════════════════════
    static void collectionsUtilDemo() {
        System.out.println("\n--- 5. Collections Utility Methods ---");

        List<Integer> nums = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6));
        System.out.println("Original  : " + nums);

        Collections.sort(nums);
        System.out.println("Sorted    : " + nums);

        Collections.reverse(nums);
        System.out.println("Reversed  : " + nums);

        Collections.shuffle(nums, new Random(42));
        System.out.println("Shuffled  : " + nums);

        System.out.println("Max: " + Collections.max(nums));
        System.out.println("Min: " + Collections.min(nums));
        System.out.println("Frequency of 1: " + Collections.frequency(nums, 1));

        // Unmodifiable views
        List<String> mutable = new ArrayList<>(Arrays.asList("a","b","c"));
        List<String> immutable = Collections.unmodifiableList(mutable);
        System.out.println("Unmodifiable: " + immutable);
        try { immutable.add("d"); }
        catch (UnsupportedOperationException e) {
            System.out.println("Caught: Cannot modify unmodifiable list");
        }

        // Synchronized wrapper (for thread-safety)
        List<String> syncList = Collections.synchronizedList(new ArrayList<>());
        System.out.println("Synchronized list created: " + syncList.getClass().getSimpleName());
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Collections Framework =====\n");
        listDemo();
        setDemo();
        mapDemo();
        queueDemo();
        collectionsUtilDemo();
    }
}
