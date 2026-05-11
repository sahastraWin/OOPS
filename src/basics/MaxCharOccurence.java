/*
 * Question: Write a program to print Highest occurred character from given String?
 * Ex: aaaaabbcddddd -> a (or d)
 *
 * Intuition: 
 * Use a frequency array to store counts. Maintain a 'max' variable and 'resultChar'. 
 * If a current character's frequency is higher than 'max', update 'max' and 'resultChar'.
 *
 * TC: O(n)
 * SC: O(1)
 */
package basics;

import java.util.Scanner;

public class MaxCharOccurence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String: ");
        String str = sc.nextLine();
        
        int[] freq = new int[256];
        int max = -1;
        char resultChar = ' ';
        
        for (int i = 0; i < str.length(); i++) {
            freq[str.charAt(i)]++;
        }
        
        for (int i = 0; i < str.length(); i++) {
            if (max < freq[str.charAt(i)]) {
                max = freq[str.charAt(i)];
                resultChar = str.charAt(i);
            }
        }
        
        System.out.println("Highest occurred character: " + resultChar);
        sc.close();
    }
}