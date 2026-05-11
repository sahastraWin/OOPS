package scenarioBased.part3;/*
 * Chapter 14 (Templates & Exceptions), Exercise 4:
 * Create a function called swaps() that interchanges the values of the two arguments sent
 * to it. (You will probably want to pass these arguments by reference.) Make the function
 * into a template, so it can be used with all numerical data types (char, int, float, and so on).
 * Write a main() program to exercise the function with several types.
 * (In Java, we use a generic wrapper array to simulate pass-by-reference swapping.)
 */

public class Ch14Ex4_Swaps {

    // Generic swap using single-element arrays to simulate pass-by-reference
    static <T> void swaps(T[] a, T[] b) {
        T temp = a[0];
        a[0] = b[0];
        b[0] = temp;
    }

    // Primitive overloads using int[]/double[]/char[] arrays as reference wrappers
    static void swaps(int[] a, int[] b) {
        int t = a[0]; a[0] = b[0]; b[0] = t;
    }

    static void swaps(double[] a, double[] b) {
        double t = a[0]; a[0] = b[0]; b[0] = t;
    }

    static void swaps(float[] a, float[] b) {
        float t = a[0]; a[0] = b[0]; b[0] = t;
    }

    static void swaps(char[] a, char[] b) {
        char t = a[0]; a[0] = b[0]; b[0] = t;
    }

    static void swaps(long[] a, long[] b) {
        long t = a[0]; a[0] = b[0]; b[0] = t;
    }

    public static void main(String[] args) {
        System.out.println("=== Generic swaps() Template ===\n");

        // int swap
        int[] a = {10}, b = {20};
        System.out.printf("int: before swap: a=%d, b=%d%n", a[0], b[0]);
        swaps(a, b);
        System.out.printf("int: after  swap: a=%d, b=%d%n%n", a[0], b[0]);

        // double swap
        double[] x = {3.14}, y = {2.71};
        System.out.printf("double: before swap: x=%.2f, y=%.2f%n", x[0], y[0]);
        swaps(x, y);
        System.out.printf("double: after  swap: x=%.2f, y=%.2f%n%n", x[0], y[0]);

        // char swap
        char[] p = {'A'}, q = {'Z'};
        System.out.printf("char: before swap: p=%c, q=%c%n", p[0], q[0]);
        swaps(p, q);
        System.out.printf("char: after  swap: p=%c, q=%c%n%n", p[0], q[0]);

        // String swap using generic version
        String[] s1 = {"Hello"}, s2 = {"World"};
        System.out.printf("String: before swap: s1=%s, s2=%s%n", s1[0], s2[0]);
        swaps(s1, s2);
        System.out.printf("String: after  swap: s1=%s, s2=%s%n", s1[0], s2[0]);

        // long swap
        long[] m = {1_000_000L}, n = {9_999_999L};
        System.out.printf("%nlong: before swap: m=%d, n=%d%n", m[0], n[0]);
        swaps(m, n);
        System.out.printf("long: after  swap: m=%d, n=%d%n", m[0], n[0]);
    }
}
