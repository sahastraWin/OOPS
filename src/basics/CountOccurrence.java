/*
 * Question: Write a program to count the number of occurrences of each character in a string?
 * Ex: apple -> a-1, p-2, l-1, e-1
 *
 * Intuition: 
 * We use an integer array to store frequencies. To print them in the order they 
 * appear without repeating, we check the frequency array and then set the frequency 
 * to 0 after printing to ensure it's not processed again.
 *
 * TC: O(n)
 * SC: O(1)
 */
package basics;

import java.util.Scanner;

public class CountOccurrence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String: ");
        String str = sc.nextLine();
        
        int[] freq = new int[256];
        for (int i = 0; i < str.length(); i++) {
            freq[str.charAt(i)]++;
        }
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (freq[ch] > 0 && ch != ' ') {
                System.out.println(ch + " : " + freq[ch]);
                freq[ch] = 0; // Prevent re-printing
            }
        }
        sc.close();
    }
}