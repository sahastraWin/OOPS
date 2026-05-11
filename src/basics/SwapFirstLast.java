/*
 * Question: Swap first and last character of a given String?
 * Ex: NacrE -> EacrN
 *
 * Intuition: Use a char array to swap indices 0 and length-1.
 *
 * TC: O(n)
 * SC: O(n)
 */
package basics;

import java.util.Scanner;

public class SwapFirstLast {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        if (str.length() < 2) { System.out.println(str); return; }
        
        char[] arr = str.toCharArray();
        char temp = arr[0];
        arr[0] = arr[arr.length - 1];
        arr[arr.length - 1] = temp;
        
        System.out.println(new String(arr));
        sc.close();
    }
}