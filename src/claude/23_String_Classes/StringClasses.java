/**
 * ============================================================
 *  TOPIC: String, StringBuilder, StringBuffer in Java
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW:
 *
 *  String:
 *  - IMMUTABLE: every operation creates a NEW String object
 *  - Stored in String Pool (Heap's special area)
 *  - Literals: "abc" → goes to pool; new String("abc") → Heap
 *  - intern() → moves to pool / returns pooled reference
 *
 *  StringBuilder (Java 1.5+):
 *  - MUTABLE: modifies in place (no new object)
 *  - NOT thread-safe
 *  - Preferred for string concatenation in single thread
 *
 *  StringBuffer (Java 1.0):
 *  - MUTABLE + thread-safe (synchronized methods)
 *  - Slower than StringBuilder due to synchronization
 *
 *  Comparison:
 *  ┌──────────────┬──────────┬───────────────┬──────────────┐
 *  │              │ String   │ StringBuilder │ StringBuffer │
 *  ├──────────────┼──────────┼───────────────┼──────────────┤
 *  │ Mutable      │ No       │ Yes           │ Yes          │
 *  │ Thread-safe  │ Yes      │ No            │ Yes          │
 *  │ Performance  │ Slow     │ Fast          │ Medium       │
 *  │ Storage      │ Pool/Heap│ Heap          │ Heap         │
 *  └──────────────┴──────────┴───────────────┴──────────────┘
 *
 *  String Pool:
 *  - String literals are interned in the pool automatically
 *  - "abc" == "abc" → true (same pool object)
 *  - new String("abc") == "abc" → false (different objects)
 *  - "abc".equals(new String("abc")) → true (same content)
 * ╚══════════════════════════════════════════════════════════╝
 */
public class StringClasses {

    public static void main(String[] args) {
        System.out.println("===== String, StringBuilder, StringBuffer =====\n");

        // ─── 1. String Immutability ──────────────────────────
        System.out.println("--- 1. String Immutability ---");
        String s1 = "Hello";
        String s2 = s1.concat(" World");
        System.out.println("s1 (unchanged): " + s1);
        System.out.println("s2 (new):       " + s2);
        System.out.println("s1 == s2: " + (s1 == s2));

        // ─── 2. String Pool ──────────────────────────────────
        System.out.println("\n--- 2. String Pool (INTERVIEW FAVOURITE) ---");
        String a = "Java";
        String b = "Java";
        String c = new String("Java");
        String d = c.intern();   // force into pool

        System.out.println("a == b (both literals):  " + (a == b));     // true  (same pool obj)
        System.out.println("a == c (literal vs new): " + (a == c));     // false (different obj)
        System.out.println("a == d (intern'd):       " + (a == d));     // true  (pool)
        System.out.println("a.equals(c):             " + a.equals(c));  // true  (same content)

        // ─── 3. String methods ───────────────────────────────
        System.out.println("\n--- 3. String Methods ---");
        String str = "  Hello, Java World!  ";
        System.out.println("Original:          \"" + str + "\"");
        System.out.println("trim():            \"" + str.trim() + "\"");
        System.out.println("strip():           \"" + str.strip() + "\"");   // Java 11, Unicode-aware
        System.out.println("toLowerCase():     " + str.trim().toLowerCase());
        System.out.println("toUpperCase():     " + str.trim().toUpperCase());
        System.out.println("length():          " + str.length());
        System.out.println("charAt(7):         " + str.trim().charAt(7));
        System.out.println("indexOf('J'):      " + str.indexOf('J'));
        System.out.println("contains(\"Java\"): " + str.contains("Java"));
        System.out.println("startsWith(\"  H\"):" + str.startsWith("  H"));
        System.out.println("endsWith(\"  \"):   " + str.endsWith("  "));
        System.out.println("replace:           " + str.trim().replace("Java", "Python"));
        System.out.println("replaceAll(regex): " + str.trim().replaceAll("[aeiou]", "*"));
        System.out.println("substring(7,11):   " + str.trim().substring(7, 11));

        // split
        String csv = "Alice,Bob,Charlie,Diana";
        String[] parts = csv.split(",");
        System.out.println("split: " + java.util.Arrays.toString(parts));

        // join
        String joined = String.join(" | ", "Alpha", "Beta", "Gamma");
        System.out.println("join: " + joined);

        // format
        String formatted = String.format("Name: %-10s Age: %3d Score: %.2f", "Alice", 25, 98.5);
        System.out.println("format: " + formatted);

        // Java 11+ methods
        System.out.println("isBlank(\"  \"):    " + "  ".isBlank());
        System.out.println("repeat(\"ab\",3):   " + "ab".repeat(3));
        System.out.println("lines count:");
        "Line1\nLine2\nLine3".lines().forEach(l -> System.out.println("  " + l));

        // char operations
        char[] chars = "Hello".toCharArray();
        System.out.println("toCharArray: " + java.util.Arrays.toString(chars));
        System.out.println("new String(chars): " + new String(chars));

        // ─── 4. StringBuilder ────────────────────────────────
        System.out.println("\n--- 4. StringBuilder ---");
        StringBuilder sb = new StringBuilder();
        sb.append("Hello");
        sb.append(", ");
        sb.append("Java");
        sb.append("!");
        System.out.println("append: " + sb);

        sb.insert(5, " World");
        System.out.println("insert(5): " + sb);

        sb.delete(5, 11);
        System.out.println("delete(5,11): " + sb);

        sb.replace(7, 11, "World");
        System.out.println("replace(7,11): " + sb);

        sb.reverse();
        System.out.println("reverse(): " + sb);
        sb.reverse();

        System.out.println("charAt(0): " + sb.charAt(0));
        System.out.println("length(): " + sb.length());
        System.out.println("capacity(): " + sb.capacity());
        sb.setCharAt(0, 'h');
        System.out.println("setCharAt(0,'h'): " + sb);
        System.out.println("indexOf(Java): " + sb.indexOf("Java"));
        System.out.println("deleteCharAt(0): " + sb.deleteCharAt(0));

        // Performance: + vs StringBuilder
        System.out.println("\n--- 4b. String concatenation performance ---");
        long start, end;

        start = System.nanoTime();
        String result = "";
        for (int i = 0; i < 10_000; i++) result += i;  // creates 10000 objects
        end = System.nanoTime();
        System.out.println("String + loop (10k): " + (end-start)/1_000_000 + " ms");

        start = System.nanoTime();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 10_000; i++) sb2.append(i);
        String result2 = sb2.toString();
        end = System.nanoTime();
        System.out.println("StringBuilder loop : " + (end-start)/1_000_000 + " ms");

        // ─── 5. StringBuffer (thread-safe) ───────────────────
        System.out.println("\n--- 5. StringBuffer (thread-safe) ---");
        StringBuffer buffer = new StringBuffer("Thread");
        buffer.append("-Safe").append("-Buffer");
        System.out.println("StringBuffer: " + buffer);
        System.out.println("synchronized: YES (all methods)");

        // ─── 6. String comparison methods ────────────────────
        System.out.println("\n--- 6. String Comparison ---");
        String s = "Hello";
        System.out.println("equals:              " + s.equals("Hello"));
        System.out.println("equalsIgnoreCase:    " + s.equalsIgnoreCase("hello"));
        System.out.println("compareTo:           " + s.compareTo("Hello"));
        System.out.println("compareToIgnoreCase: " + s.compareToIgnoreCase("hello"));
        System.out.println("matches(regex):      " + s.matches("[A-Z][a-z]+"));
    }
}
