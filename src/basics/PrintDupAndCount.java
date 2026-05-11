/*
 * Question: Write a program to print all duplicate character and their count?
 * Ex: Programming -> r:2, g:2, m:2
 *
 * Intuition: 
 * Similar to Problem 6, but we print both the character and its frequency.
 *
 * TC: O(n)
 * SC: O(1)
 */
package basics;

import java.util.Scanner;

public class PrintDupAndCount {
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
            if (freq[ch] > 1 && ch != ' ') {
                System.out.println(ch + " : " + freq[ch]);
                freq[ch] = 0;
            }
        }
        sc.close();
    }
}