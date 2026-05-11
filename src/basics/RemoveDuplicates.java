/*
 * Question: Write a program to remove the duplicate characters from the given String?
 * Ex: banaans -> bans
 *
 * Intuition: 
 * We use a boolean array of size 256 to keep track of characters we have already seen. 
 * We traverse the string and if a character has not been "seen" yet, we append it 
 * to a StringBuilder and mark it as seen.
 *
 * TC: O(n)
 * SC: O(1) - Boolean array size is fixed.
 */
package basics;

import java.util.Scanner;

public class RemoveDuplicates {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String: ");
        String str = sc.nextLine();
        
        boolean[] seen = new boolean[256];
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!seen[ch] && ch != ' ') {
                sb.append(ch);
                seen[ch] = true;
            }
        }
        
        System.out.println("Result: " + sb.toString());
        sc.close();
    }
}