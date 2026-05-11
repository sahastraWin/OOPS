/*
 * Question: Sanjeeva Reddy Nagar -> S R Nagar
 *
 * Intuition: Take the first character of each word except the last, followed by a space. 
 * For the last word, print it entirely.
 *
 * TC: O(n)
 * SC: O(n)
 */
package basics;

import java.util.Scanner;

public class ShortName {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] words = str.split(" ");
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length - 1; i++) {
            sb.append(words[i].charAt(0)).append(" ");
        }
        sb.append(words[words.length - 1]);
        
        System.out.println(sb.toString());
        sc.close();
    }
}