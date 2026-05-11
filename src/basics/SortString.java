/*
 * Question: Write a program to sort the given String?
 * Ex: nacre -> acenr
 *
 * Intuition: 
 * Convert string to char array, use built-in Arrays.sort() which is Dual-Pivot Quicksort.
 *
 * TC: O(n log n)
 * SC: O(n)
 */
package basics;

import java.util.*;

public class SortString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String: ");
        String str = sc.nextLine();
        
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        
        System.out.println("Sorted: " + new String(arr));
        sc.close();
    }
}