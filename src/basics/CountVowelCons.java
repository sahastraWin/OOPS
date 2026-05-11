/*
 * Question: Write a program to count the consonants and vowels from the given String?
 *
 * Intuition: 
 * Check for letters first, then classify as vowel or consonant.
 *
 * TC: O(n)
 * SC: O(1)
 */
package basics;

import java.util.Scanner;

public class CountVowelCons {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String: ");
        String str = sc.nextLine().toLowerCase();
        
        int v = 0, c = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isLetter(ch)) {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') v++;
                else c++;
            }
        }
        
        System.out.println("Vowels: " + v + "\nConsonants: " + c);
        sc.close();
    }
}