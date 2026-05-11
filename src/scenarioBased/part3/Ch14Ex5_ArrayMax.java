package scenarioBased.part3;/*
 * Chapter 14 (Templates & Exceptions), Exercise 5:
 * Create a function called amax() that returns the value of the largest element in an array.
 * The arguments to the function should be the address of the array and its size.
 * Make this function into a template so it will work with an array of any numerical type.
 * Write a main() program that applies this function to arrays of various types.
 */

public class Ch14Ex5_ArrayMax {

    // Generic amax for Comparable types
    static <T extends Comparable<T>> T amax(T[] arr, int size) {
        if (size <= 0) throw new IllegalArgumentException("Array must not be empty");
        T max = arr[0];
        for (int i = 1; i < size; i++) if (arr[i].compareTo(max) > 0) max = arr[i];
        return max;
    }

    // Primitive overloads
    static int    amax(int[]    arr, int size) { int    m = arr[0]; for (int i=1;i<size;i++) if(arr[i]>m) m=arr[i]; return m; }
    static long   amax(long[]   arr, int size) { long   m = arr[0]; for (int i=1;i<size;i++) if(arr[i]>m) m=arr[i]; return m; }
    static double amax(double[] arr, int size) { double m = arr[0]; for (int i=1;i<size;i++) if(arr[i]>m) m=arr[i]; return m; }
    static float  amax(float[]  arr, int size) { float  m = arr[0]; for (int i=1;i<size;i++) if(arr[i]>m) m=arr[i]; return m; }
    static char   amax(char[]   arr, int size) { char   m = arr[0]; for (int i=1;i<size;i++) if(arr[i]>m) m=arr[i]; return m; }

    public static void main(String[] args) {
        System.out.println("=== Generic amax() Template ===\n");

        int[]    intArr  = {3, 1, 42, 17, 8};
        System.out.println("int max:    " + amax(intArr,  intArr.length));

        long[]   longArr = {100L, 5000L, 999L, 12345L};
        System.out.println("long max:   " + amax(longArr, longArr.length));

        double[] dblArr  = {1.5, 3.14, 2.71, 9.99, 0.01};
        System.out.println("double max: " + amax(dblArr,  dblArr.length));

        float[]  fltArr  = {2.2f, 8.8f, 4.4f, 6.6f};
        System.out.println("float max:  " + amax(fltArr,  fltArr.length));

        char[]   chrArr  = {'b', 'z', 'm', 'a', 'q'};
        System.out.println("char max:   " + amax(chrArr,  chrArr.length));

        // Generic version with Integer wrapper
        Integer[] intWrap = {7, 3, 99, 42, 15};
        System.out.println("Integer[] max: " + amax(intWrap, intWrap.length));

        // Generic version with String
        String[] strArr = {"banana", "apple", "zucchini", "mango"};
        System.out.println("String max: " + amax(strArr, strArr.length));
    }
}
