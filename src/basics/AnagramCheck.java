/*
 * Question: Write a program to check whether two given Strings are anagram or not?
 *
 * Intuition: 
 * Two strings are anagrams if they have the same characters with the same frequencies. 
 * Use a single frequency array: increment for String A, decrement for String B. 
 * If all counts are zero, they are anagrams.
 *
 * TC: O(n)
 * SC: O(1)
 */
package basics;

import java.util.Scanner;

public class AnagramCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Str1: ");
        String s1 = sc.next().toLowerCase();
        System.out.print("Str2: ");
        String s2 = sc.next().toLowerCase();
        
        if (s1.length() != s2.length()) {
            System.out.println("Not Anagrams");
            return;
        }
        
        int[] freq = new int[256];
        for (int i = 0; i < s1.length(); i++) {
            freq[s1.charAt(i)]++;
            freq[s2.charAt(i)]--;
        }
        
        for (int i = 0; i < 256; i++) {
            if (freq[i] != 0) {
                System.out.println("Not Anagrams");
                return;
            }
        }
        System.out.println("Anagrams");
        sc.close();
    }
}