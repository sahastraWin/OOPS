/*
 * Question: Write a program to print last non-repeated character from given String?
 * Ex: Software Services -> c
 *
 * Intuition: 
 * Similar to the first non-repeated character, we use a frequency array. 
 * To find the "last" one, we simply traverse the string from the end (right to left) 
 * and pick the first character we encounter that has a count of 1.
 *
 * TC: O(n)
 * SC: O(1)
 */
package basics;

import java.util.Scanner;

public class LastNonRepeat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String: ");
        String str = sc.nextLine();
        
        int[] freq = new int[256];
        for (int i = 0; i < str.length(); i++) {
            freq[str.charAt(i)]++;
        }
        
        for (int i = str.length() - 1; i >= 0; i--) {
            if (freq[str.charAt(i)] == 1 && str.charAt(i) != ' ') {
                System.out.println("Last non-repeated character: " + str.charAt(i));
                return;
            }
        }
        sc.close();
    }
}