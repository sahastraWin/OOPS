/*
 * Question: Reverse individual words of the Given String?
 * Ex: Today is Monday -> yadoT si yadnoM
 *
 * Intuition: Split into words, reverse each word using a loop/StringBuilder, then join.
 *
 * TC: O(n)
 * SC: O(n)
 */
package basics;

import java.util.Scanner;

public class ReverseEachWord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Sentence: ");
        String str = sc.nextLine();
        
        String[] words = str.split(" ");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            StringBuilder temp = new StringBuilder(words[i]);
            result.append(temp.reverse()).append(" ");
        }
        System.out.println("Result: " + result.toString().trim());
        sc.close();
    }
}