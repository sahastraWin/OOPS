/*
 * Question: Whether Given String is palindrome String or not?
 *
 * Intuition: Use two pointers (start and end). Compare characters. If mismatch, not palindrome.
 *
 * TC: O(n)
 * SC: O(1)
 */
package basics;

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("String: ");
        String str = sc.nextLine();
        
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                System.out.println("Not Palindrome");
                return;
            }
            i++; j--;
        }
        System.out.println("Palindrome");
        sc.close();
    }
}