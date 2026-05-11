/*
 * Question: Write a program to check whether given string contains Special Characters or not?
 *
 * Intuition: 
 * A special character is anything that is NOT a letter and NOT a digit.
 *
 * TC: O(n)
 * SC: O(1)
 */
package basics;

import java.util.Scanner;

public class CheckSpecial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String: ");
        String str = sc.nextLine();
        
        boolean hasSpecial = false;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!Character.isLetterOrDigit(ch) && ch != ' ') {
                hasSpecial = true;
                break;
            }
        }
        
        System.out.println(hasSpecial ? "Contains Special Characters" : "No Special Characters");
        sc.close();
    }
}