/*
 * Question: Write a program to print duplicate characters from the given String?
 * Ex: Programming -> r, g, m
 *
 * Intuition: 
 * We use a frequency array. After counting, we iterate through the string and 
 * print only those characters that have a frequency > 1. We reset the count 
 * after printing to avoid duplicates in the output.
 *
 * TC: O(n)
 * SC: O(1)
 */
package basics;

import java.util.Scanner;

public class PrintDuplicates {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String: ");
        String str = sc.nextLine();
        
        int[] freq = new int[256];
        for (int i = 0; i < str.length(); i++) {
            freq[str.charAt(i)]++;
        }
        
        System.out.print("Duplicate characters: ");
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (freq[ch] > 1 && ch != ' ') {
                System.out.print(ch + " ");
                freq[ch] = 0; 
            }
        }
        sc.close();
    }
}