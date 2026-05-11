/*
 * Question: Write a program to check whether given string contains digits or not?
 *
 * Intuition: 
 * Iterate through the string. Use Character.isDigit() or check ASCII range ('0'-'9'). 
 * Return true immediately upon finding the first digit.
 *
 * TC: O(n)
 * SC: O(1)
 */
package basics;

import java.util.Scanner;

public class CheckDigit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String: ");
        String str = sc.nextLine();
        
        boolean hasDigit = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                hasDigit = true;
                break;
            }
        }
        
        System.out.println(hasDigit ? "Contains Digits" : "Does not contain Digits");
        sc.close();
    }
}