/*
 * Question: Count the characters, digits and Special Characters from given String?
 *
 * Intuition: 
 * Use a single loop to categorize each character into one of three counters.
 *
 * TC: O(n)
 * SC: O(1)
 */
package basics;

import java.util.Scanner;

public class CountAll {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String: ");
        String str = sc.nextLine();
        
        int chars = 0, digits = 0, special = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isLetter(ch)) chars++;
            else if (Character.isDigit(ch)) digits++;
            else if (ch != ' ') special++;
        }
        
        System.out.println("Characters: " + chars);
        System.out.println("Digits: " + digits);
        System.out.println("Special: " + special);
        sc.close();
    }
}