/*
 * Question: Replace given character to other given Character?
 *
 * Intuition: Convert to char array, iterate, and swap if character matches.
 *
 * TC: O(n)
 * SC: O(n)
 */
package basics;

import java.util.Scanner;

public class ReplaceChar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("String: ");
        String str = sc.nextLine();
        System.out.print("Replace: ");
        char oldChar = sc.next().charAt(0);
        System.out.print("With: ");
        char newChar = sc.next().charAt(0);
        
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == oldChar) arr[i] = newChar;
        }
        System.out.println("Result: " + new String(arr));
        sc.close();
    }
}