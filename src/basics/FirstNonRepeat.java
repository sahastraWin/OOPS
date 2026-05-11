/*
 * Question: Write a program to print First non-repeated character from given String?
 * Ex: Software Services -> o
 *
 * Intuition: 
 * Instead of nested loops (O(n^2)), we use a frequency array of size 256 (for ASCII). 
 * In the first pass, we count occurrences of every character. 
 * In the second pass, we check the original string and return the first character 
 * whose count in the frequency array is 1.
 *
 * TC: O(n)
 * SC: O(1) - The frequency array size is constant (256).
 */
package basics;

import java.util.Scanner;

public class FirstNonRepeat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String: ");
        String str = sc.nextLine();
        
        int[] freq = new int[256];
        for (int i = 0; i < str.length(); i++) {
            freq[str.charAt(i)]++;
        }
        
        boolean found = false;
        for (int i = 0; i < str.length(); i++) {
            if (freq[str.charAt(i)] == 1 && str.charAt(i) != ' ') {
                System.out.println("First non-repeated character: " + str.charAt(i));
                found = true;
                break;
            }
        }
        
        if (!found) System.out.println("No non-repeated character found.");
        sc.close();
    }
}