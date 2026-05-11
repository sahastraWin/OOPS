/*
 * Question: Write a program to print the reverse of the String?
 * Ex: Nacre -> ercaN
 *
 * Intuition: 
 * To reverse a string efficiently, we convert the string into a character array. 
 * We use two pointers: 'i' starting at the beginning and 'j' at the end. 
 * We swap the characters at these positions and move the pointers towards each other 
 * until they meet in the middle. This avoids creating multiple string objects.
 *
 * TC: O(n) - where n is the length of the string.
 * SC: O(n) - to store the character array.
 */
package basics;

import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String to reverse: ");
        String str = sc.nextLine();
        
        char[] charArray = str.toCharArray();
        int i = 0;
        int j = charArray.length - 1;
        
        while (i < j) {
            char temp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = temp;
            i++;
            j--;
        }
        
        System.out.println("Reversed String: " + new String(charArray));
        sc.close();
    }
}