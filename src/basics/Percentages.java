/*
 * Question: Find percentages of Characters, Digits, Special characters, Vowels, Consonants, etc.
 *
 * Intuition: 
 * Calculate counts as done in previous problems, then divide by the total length 
 * of the string and multiply by 100.0 for floating-point precision.
 *
 * TC: O(n)
 * SC: O(1)
 */
package basics;

import java.util.Scanner;

public class Percentages {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String: ");
        String str = sc.nextLine();
        double len = str.length();
        
        int chars = 0, digits = 0, special = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isLetter(ch)) chars++;
            else if (Character.isDigit(ch)) digits++;
            else special++;
        }
        
        System.out.printf("Characters: %.2f%%\n", (chars / len) * 100);
        System.out.printf("Digits: %.2f%%\n", (digits / len) * 100);
        System.out.printf("Special: %.2f%%\n", (special / len) * 100);
        sc.close();
    }
}