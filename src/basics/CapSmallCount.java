/*
 * Question: Write a program to count the Capital letters and Small letters?
 *
 * Intuition: 
 * Use Character.isUpperCase() and Character.isLowerCase().
 *
 * TC: O(n)
 * SC: O(1)
 */
package basics;

import java.util.Scanner;

public class CapSmallCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String: ");
        String str = sc.nextLine();
        
        int cap = 0, small = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isUpperCase(ch)) cap++;
            else if (Character.isLowerCase(ch)) small++;
        }
        
        System.out.println("Capital: " + cap);
        System.out.println("Small: " + small);
        sc.close();
    }
}