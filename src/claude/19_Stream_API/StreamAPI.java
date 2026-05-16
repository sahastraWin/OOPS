/**
 * ============================================================
 *  TOPIC: Stream API (Java 8)
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW:
 *
 *  Stream = sequence of elements supporting sequential/parallel
 *  aggregate operations. NOT a data structure — doesn't store data.
 *
 *  Three stages:
 *  1. SOURCE       → collection, array, generator, I/O channel
 *  2. INTERMEDIATE → lazy, return a new Stream (filter, map, etc.)
 *  3. TERMINAL     → triggers execution, returns result (collect, etc.)
 *
 *  LAZY EVALUATION: Intermediate operations are NOT executed until
 *  a terminal operation is called. This enables optimization.
 *
 *  Intermediate Operations (return Stream):
 *    filter(Predicate)    → keep elements matching predicate
 *    map(Function)        → transform each element
 *    flatMap(Function)    → flatten nested streams
 *    distinct()           → remove duplicates
 *    sorted()             → sort elements
 *    limit(n)             → take first n elements
 *    skip(n)              → skip first n elements
 *    peek(Consumer)       → debug/side-effect without consuming
 *
 *  Terminal Operations (return non-Stream):
 *    collect(Collector)   → gather into collection/map/string
 *    forEach(Consumer)    → apply action to each element
 *    reduce(BinaryOp)     → fold to single value
 *    count()              → number of elements
 *    findFirst/findAny()  → Optional<T>
 *    anyMatch/allMatch/noneMatch(Predicate) → boolean
 *    min/max(Comparator)  → Optional<T>
 *    toArray()            → array
 *
 *  Stream vs Collection:
 *  - Stream cannot be reused (consumed once)
 *  - Stream is lazy, Collection is eager
 * ╚══════════════════════════════════════════════════════════╝
 */

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class StreamAPI {

    static class Employee {
        String name, dept;
        double salary;
        int    age;

        Employee(String n, String d, double s, int a) {
            name=n; dept=d; salary=s; age=a;
        }

        public String getName()   { return name; }
        public String getDept()   { return dept; }
        public double getSalary() { return salary; }
        public int    getAge()    { return age; }

        @Override public String toString() {
            return name + "(" + dept + ",$" + (int)salary + ",age" + age + ")";
        }
    }

    static List<Employee> employees = Arrays.asList(
        new Employee("Alice",   "Engineering", 90000, 28),
        new Employee("Bob",     "Marketing",   65000, 35),
        new Employee("Charlie", "Engineering", 85000, 32),
        new Employee("Diana",   "HR",          55000, 26),
        new Employee("Eve",     "Engineering", 95000, 30),
        new Employee("Frank",   "Marketing",   70000, 42),
        new Employee("Grace",   "HR",          60000, 29),
        new Employee("Hank",    "Engineering", 80000, 45)
    );

    // ═══════════════════════════════════════════════════════
    // 1. CREATING STREAMS
    // ═══════════════════════════════════════════════════════
    static void creatingStreams() {
        System.out.println("--- 1. Creating Streams ---");

        // From collection
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.stream().forEach(n -> System.out.print(n + " "));
        System.out.println();

        // Stream.of()
        Stream.of(1, 2, 3, 4, 5).forEach(n -> System.out.print(n + " "));
        System.out.println();

        // From array
        int[] arr = {10, 20, 30};
        Arrays.stream(arr).forEach(n -> System.out.print(n + " "));
        System.out.println();

        // Infinite stream with generate
        Stream.generate(Math::random).limit(3)
            .forEach(d -> System.out.printf("%.4f ", d));
        System.out.println();

        // Infinite stream with iterate
        Stream.iterate(0, n -> n + 2).limit(5)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // IntStream range
        IntStream.range(1, 6).forEach(n -> System.out.print(n + " "));
        System.out.println();
        IntStream.rangeClosed(1, 5).forEach(n -> System.out.print(n + " "));
        System.out.println();
    }

    // ═══════════════════════════════════════════════════════
    // 2. INTERMEDIATE OPERATIONS
    // ═══════════════════════════════════════════════════════
    static void intermediateOps() {
        System.out.println("\n--- 2. Intermediate Operations ---");

        // filter
        System.out.print("filter salary>80k: ");
        employees.stream()
            .filter(e -> e.salary > 80000)
            .map(Employee::getName)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // map
        System.out.print("map to salary: ");
        employees.stream()
            .map(Employee::getSalary)
            .limit(4)
            .forEach(s -> System.out.print(s + " "));
        System.out.println();

        // flatMap — flatten nested collections
        List<List<Integer>> nested = Arrays.asList(
            Arrays.asList(1,2,3), Arrays.asList(4,5), Arrays.asList(6,7,8,9));
        System.out.print("flatMap: ");
        nested.stream()
            .flatMap(Collection::stream)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // distinct, sorted, limit, skip
        List<Integer> nums = Arrays.asList(5,3,1,3,2,5,4,1,2);
        System.out.print("distinct+sorted: ");
        nums.stream().distinct().sorted().forEach(n -> System.out.print(n + " "));
        System.out.println();

        System.out.print("skip(2)+limit(3): ");
        nums.stream().distinct().sorted().skip(2).limit(3)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // peek (for debugging)
        System.out.println("peek example:");
        employees.stream()
            .filter(e -> e.dept.equals("Engineering"))
            .peek(e -> System.out.println("  After filter: " + e.name))
            .map(Employee::getSalary)
            .peek(s -> System.out.println("  After map: " + s))
            .limit(2)
            .forEach(s -> {});
    }

    // ═══════════════════════════════════════════════════════
    // 3. TERMINAL OPERATIONS
    // ═══════════════════════════════════════════════════════
    static void terminalOps() {
        System.out.println("\n--- 3. Terminal Operations ---");

        // collect → List
        List<String> engNames = employees.stream()
            .filter(e -> e.dept.equals("Engineering"))
            .map(Employee::getName)
            .collect(Collectors.toList());
        System.out.println("Engineering names: " + engNames);

        // collect → joining
        String joined = employees.stream()
            .map(Employee::getName)
            .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Joined: " + joined);

        // count
        long engCount = employees.stream()
            .filter(e -> e.dept.equals("Engineering"))
            .count();
        System.out.println("Engineering count: " + engCount);

        // sum / average / min / max
        OptionalDouble avgSalary = employees.stream()
            .mapToDouble(Employee::getSalary)
            .average();
        System.out.printf("Avg salary: %.2f%n", avgSalary.orElse(0));

        double totalSalary = employees.stream()
            .mapToDouble(Employee::getSalary)
            .sum();
        System.out.printf("Total salary: %.2f%n", totalSalary);

        Optional<Employee> highestPaid = employees.stream()
            .max(Comparator.comparingDouble(Employee::getSalary));
        System.out.println("Highest paid: " + highestPaid.map(Employee::getName).orElse("N/A"));

        // reduce
        OptionalDouble productOfAges = employees.stream()
            .mapToDouble(Employee::getAge)
            .reduce((a, b) -> a * b);
        System.out.println("Product of ages (last 3): "
            + employees.stream().mapToInt(Employee::getAge).limit(3).reduce(1, (a,b)->a*b));

        // anyMatch, allMatch, noneMatch
        System.out.println("Any salary > 90000: " + employees.stream().anyMatch(e -> e.salary > 90000));
        System.out.println("All salary > 50000: " + employees.stream().allMatch(e -> e.salary > 50000));
        System.out.println("None in Finance: "    + employees.stream().noneMatch(e -> e.dept.equals("Finance")));

        // findFirst
        Optional<Employee> firstEng = employees.stream()
            .filter(e -> e.dept.equals("Engineering"))
            .findFirst();
        firstEng.ifPresent(e -> System.out.println("First Engineer: " + e.name));
    }

    // ═══════════════════════════════════════════════════════
    // 4. COLLECTORS — groupingBy, partitioningBy, toMap
    // ═══════════════════════════════════════════════════════
    static void collectorsDemo() {
        System.out.println("\n--- 4. Collectors ---");

        // groupingBy
        Map<String, List<Employee>> byDept = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDept));
        byDept.forEach((dept, emps) -> {
            System.out.print(dept + ": ");
            emps.forEach(e -> System.out.print(e.name + " "));
            System.out.println();
        });

        // groupingBy + counting
        Map<String, Long> countByDept = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDept, Collectors.counting()));
        System.out.println("Count by dept: " + countByDept);

        // groupingBy + averaging
        Map<String, Double> avgByDept = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDept,
                Collectors.averagingDouble(Employee::getSalary)));
        avgByDept.forEach((d, avg) -> System.out.printf("  %s avg: $%.0f%n", d, avg));

        // partitioningBy
        Map<Boolean, List<Employee>> partitioned = employees.stream()
            .collect(Collectors.partitioningBy(e -> e.salary > 75000));
        System.out.println("High earners: " + partitioned.get(true).stream()
            .map(Employee::getName).collect(Collectors.toList()));

        // toMap
        Map<String, Double> salaryMap = employees.stream()
            .collect(Collectors.toMap(Employee::getName, Employee::getSalary));
        System.out.println("Alice's salary: " + salaryMap.get("Alice"));

        // statistics
        DoubleSummaryStatistics stats = employees.stream()
            .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.printf("Stats: count=%d min=%.0f max=%.0f avg=%.0f%n",
            stats.getCount(), stats.getMin(), stats.getMax(), stats.getAverage());
    }

    // ═══════════════════════════════════════════════════════
    // 5. PARALLEL STREAMS
    // ═══════════════════════════════════════════════════════
    static void parallelStreamDemo() {
        System.out.println("\n--- 5. Parallel Streams ---");

        /*
         * ╔══════════════════════════════════════════════╗
         *  Parallel streams split work across multiple threads
         *  using ForkJoinPool (common pool).
         *  - Good for: CPU-intensive, large datasets, independent ops
         *  - Bad for: small datasets, order-sensitive ops, shared state
         *  - Warning: parallel doesn't always mean faster!
         *    Overhead of splitting/merging can dominate.
         * ╚══════════════════════════════════════════════╝
         */
        long start, end;
        List<Integer> bigList = IntStream.rangeClosed(1, 1_000_000)
            .boxed().collect(Collectors.toList());

        start = System.currentTimeMillis();
        long sumSeq = bigList.stream().mapToLong(Integer::longValue).sum();
        end   = System.currentTimeMillis();
        System.out.println("Sequential sum: " + sumSeq + " (" + (end-start) + "ms)");

        start = System.currentTimeMillis();
        long sumPar = bigList.parallelStream().mapToLong(Integer::longValue).sum();
        end   = System.currentTimeMillis();
        System.out.println("Parallel   sum: " + sumPar + " (" + (end-start) + "ms)");

        // Order is not guaranteed in parallel
        System.out.print("Parallel (may be unordered): ");
        IntStream.range(1, 6).parallel()
            .forEach(n -> System.out.print(n + " "));
        System.out.println();

        System.out.print("Parallel forEachOrdered: ");
        IntStream.range(1, 6).parallel()
            .forEachOrdered(n -> System.out.print(n + " "));
        System.out.println();
    }

    // ═══════════════════════════════════════════════════════
    // MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("===== Stream API =====\n");
        creatingStreams();
        intermediateOps();
        terminalOps();
        collectorsDemo();
        parallelStreamDemo();
    }
}
