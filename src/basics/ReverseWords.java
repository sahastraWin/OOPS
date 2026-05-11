/*
 * Question: Reverse words in a given String?
 * Ex: Java is best -> best is Java
 *
 * Intuition: Split the string by spaces into an array. Iterate backwards through the array.
 *
 * TC: O(n)
 * SC: O(n)
 */
package basics;

import java.util.Scanner;

public class ReverseWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Sentence: ");
        String str = sc.nextLine();
        
        String[] words = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]).append(" ");
        }
        System.out.println("Result: " + sb.toString().trim());
        sc.close();
    }
}