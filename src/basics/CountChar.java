/*
 * Question: Count occurrence of a given character from the String?
 *
 * Intuition: Loop through the string and increment a counter whenever a match is found.
 *
 * TC: O(n)
 * SC: O(1)
 */
package basics;

import java.util.Scanner;

public class CountChar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("String: ");
        String str = sc.nextLine();
        System.out.print("Target Char: ");
        char target = sc.next().charAt(0);
        
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == target) count++;
        }
        System.out.println("Occurrence: " + count);
        sc.close();
    }
}