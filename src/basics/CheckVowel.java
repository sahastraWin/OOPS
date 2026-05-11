/*
 * Question: Write a program to check whether given string contains vowels or not?
 *
 * Intuition: 
 * Check each character against the set {a, e, i, o, u} in both cases.
 *
 * TC: O(n)
 * SC: O(1)
 */
package basics;

import java.util.Scanner;

public class CheckVowel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String: ");
        String str = sc.nextLine().toLowerCase();
        
        boolean hasVowel = false;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                hasVowel = true;
                break;
            }
        }
        
        System.out.println(hasVowel ? "Contains Vowels" : "No Vowels");
        sc.close();
    }
}