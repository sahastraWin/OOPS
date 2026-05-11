/*
 * Question: Write a program to remove the given Character from the given String?
 * Ex: nacre Software, remove 'a' -> ncre Softwre
 *
 * Intuition: 
 * Use a StringBuilder to construct a new string, skipping the character that matches 
 * the input character to be removed.
 *
 * TC: O(n)
 * SC: O(n) - for the result string.
 */
package basics;

import java.util.Scanner;

public class RemoveChar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String: ");
        String str = sc.nextLine();
        System.out.print("Enter char to remove: ");
        char ch = sc.next().charAt(0);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ch) {
                sb.append(str.charAt(i));
            }
        }
        
        System.out.println("Result: " + sb.toString());
        sc.close();
    }
}