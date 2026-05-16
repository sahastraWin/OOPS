/**
 * ============================================================
 *  TOPIC: Wrapper Classes & Autoboxing in Java
 * ============================================================
 *
 * ╔══════════════════════════════════════════════════════════╗
 *  INTERVIEW MUST-KNOW:
 *
 *  Wrapper Classes (java.lang):
 *  ┌───────────┬──────────┬───────────────────────────────┐
 *  │ Primitive │ Wrapper  │ Cache Range                   │
 *  ├───────────┼──────────┼───────────────────────────────┤
 *  │ byte      │ Byte     │ -128 to 127                   │
 *  │ short     │ Short    │ -128 to 127                   │
 *  │ int       │ Integer  │ -128 to 127 (Integer Cache)   │
 *  │ long      │ Long     │ -128 to 127                   │
 *  │ float     │ Float    │ no cache                      │
 *  │ double    │ Double   │ no cache                      │
 *  │ char      │ Character│ 0 to 127                      │
 *  │ boolean   │ Boolean  │ TRUE and FALSE cached         │
 *  └───────────┴──────────┴───────────────────────────────┘
 *
 *  Autoboxing: primitive → wrapper (automatic)
 *    int i = 5; Integer I = i;   // compiler: Integer.valueOf(i)
 *
 *  Unboxing:   wrapper → primitive (automatic)
 *    Integer I = 5; int i = I;   // compiler: I.intValue()
 *
 *  INTERVIEW TRAP: Integer cache!
 *    Integer a = 127; Integer b = 127; a == b → true (cached)
 *    Integer a = 128; Integer b = 128; a == b → false (new objects)
 *    ALWAYS use .equals() to compare wrapper objects!
 *
 *  NullPointerException with unboxing:
 *    Integer x = null; int y = x;  // NullPointerException!
 * ╚══════════════════════════════════════════════════════════╝
 */

import java.util.*;

public class WrapperClassesAutoboxing {

    public static void main(String[] args) {
        System.out.println("===== Wrapper Classes & Autoboxing =====\n");

        // ─── 1. Boxing and Unboxing ──────────────────────────
        System.out.println("--- 1. Boxing and Unboxing ---");

        // Manual boxing (old way pre-Java 5)
        Integer boxed = Integer.valueOf(42);
        int unboxed   = boxed.intValue();
        System.out.println("Manual boxed=" + boxed + " unboxed=" + unboxed);

        // Autoboxing (Java 5+)
        Integer autoBoxed = 100;       // compiler → Integer.valueOf(100)
        int     autoUnboxed = autoBoxed; // compiler → autoBoxed.intValue()
        System.out.println("Auto boxed=" + autoBoxed + " unboxed=" + autoUnboxed);

        // In collections (primitives can't go in collections)
        List<Integer> ints = new ArrayList<>();
        ints.add(1);    // autoboxed
        ints.add(2);
        ints.add(3);
        int sum = 0;
        for (int n : ints) sum += n;  // unboxed in loop
        System.out.println("Sum via autoboxing: " + sum);

        // ─── 2. Integer Cache Trap ───────────────────────────
        System.out.println("\n--- 2. Integer Cache Trap (INTERVIEW FAVOURITE) ---");

        Integer a = 127, b = 127;
        Integer c = 128, d = 128;

        System.out.println("127 == 127 : " + (a == b));    // true  (cached)
        System.out.println("128 == 128 : " + (c == d));    // false (not cached)
        System.out.println("128.equals: " + c.equals(d));  // true  (always use equals!)

        /*
         * ╔══════════════════════════════════════════════╗
         *  WHY? Integer.valueOf() caches -128 to 127.
         *  Same object returned for values in this range.
         *  Outside this range, new Integer objects are created.
         *  RULE: Always use .equals() to compare wrapper values!
         * ╚══════════════════════════════════════════════╝
         */
        Integer x = new Integer(100);  // bypasses cache (deprecated Java 9+)
        Integer y = new Integer(100);
        System.out.println("new Integer(100) == new Integer(100): " + (x == y));  // false
        System.out.println("new Integer(100).equals(...): " + x.equals(y));       // true

        // ─── 3. Parsing and Converting ───────────────────────
        System.out.println("\n--- 3. Parsing and Converting ---");

        // String → primitive
        int    parsedInt    = Integer.parseInt("42");
        double parsedDouble = Double.parseDouble("3.14");
        boolean parsedBool  = Boolean.parseBoolean("true");
        System.out.println("parseInt:     " + parsedInt);
        System.out.println("parseDouble:  " + parsedDouble);
        System.out.println("parseBoolean: " + parsedBool);

        // primitive → String
        String fromInt    = Integer.toString(42);
        String fromDouble = Double.toString(3.14);
        String concatWay  = "" + 100;   // using concatenation
        String valueOfWay = String.valueOf(100);
        System.out.println("toString: " + fromInt + " " + fromDouble);

        // Number conversions (widening/narrowing)
        Integer bigInt = 1000;
        System.out.println("intValue:   " + bigInt.intValue());
        System.out.println("doubleValue:" + bigInt.doubleValue());
        System.out.println("longValue:  " + bigInt.longValue());

        // Binary, Octal, Hex conversion
        System.out.println("toBinaryString(255): " + Integer.toBinaryString(255));
        System.out.println("toOctalString(255):  " + Integer.toOctalString(255));
        System.out.println("toHexString(255):    " + Integer.toHexString(255));
        System.out.println("parseInt(\"FF\",16):   " + Integer.parseInt("FF", 16));

        // ─── 4. Wrapper utility methods ──────────────────────
        System.out.println("\n--- 4. Wrapper Utility Methods ---");
        System.out.println("Integer.MAX_VALUE: " + Integer.MAX_VALUE);
        System.out.println("Integer.MIN_VALUE: " + Integer.MIN_VALUE);
        System.out.println("Integer.BYTES:     " + Integer.BYTES);
        System.out.println("Integer.SIZE:      " + Integer.SIZE + " bits");
        System.out.println("Integer.compare(5,10): " + Integer.compare(5, 10));
        System.out.println("Integer.max(5,10):     " + Integer.max(5, 10));
        System.out.println("Integer.sum(5,10):     " + Integer.sum(5, 10));
        System.out.println("Integer.bitCount(255): " + Integer.bitCount(255));
        System.out.println("Character.isLetter('A'):" + Character.isLetter('A'));
        System.out.println("Character.toLowerCase('Z'):" + Character.toLowerCase('Z'));
        System.out.println("Character.isDigit('5'):" + Character.isDigit('5'));

        // ─── 5. Autoboxing NPE trap ───────────────────────────
        System.out.println("\n--- 5. Autoboxing NullPointerException Trap ---");
        /*
         * ╔══════════════════════════════════════════════╗
         *  DANGEROUS: Unboxing null causes NPE!
         *  Integer result = null;
         *  int r = result;  // NullPointerException at unboxing!
         * ╚══════════════════════════════════════════════╝
         */
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 90);
        // Integer bobScore = scores.get("Bob");   // null (Bob not in map)
        // int bScore = bobScore;                  // NPE!

        // Safe way:
        int aliceScore = scores.getOrDefault("Alice", 0);  // safe
        int bobScore   = scores.getOrDefault("Bob",   0);  // 0 instead of null
        System.out.println("Alice: " + aliceScore + " Bob: " + bobScore);

        // Also dangerous in arithmetic:
        Integer count = null;
        try {
            int result = count + 1;  // NPE: unboxing null
        } catch (NullPointerException e) {
            System.out.println("NPE caught during unboxing of null: " + e);
        }

        // ─── 6. Performance: when autoboxing is expensive ─────
        System.out.println("\n--- 6. Performance Impact of Autoboxing ---");
        long start, end;

        // Using Integer (with autoboxing overhead)
        start = System.nanoTime();
        Long sumWrapped = 0L;
        for (long i = 0; i < 100_000; i++) sumWrapped += i;  // autoboxing each iteration
        end = System.nanoTime();
        System.out.println("Wrapped Long sum: " + sumWrapped + " (" + (end-start)/1_000 + " μs)");

        // Using long primitive (no boxing)
        start = System.nanoTime();
        long sumPrimitive = 0L;
        for (long i = 0; i < 100_000; i++) sumPrimitive += i;
        end = System.nanoTime();
        System.out.println("Primitive sum:   " + sumPrimitive + " (" + (end-start)/1_000 + " μs)");
    }
}
