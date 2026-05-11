/*
 * Question: Remove duplicates without predefined functions.
 *
 * Intuition: Use a frequency/boolean array to keep track of seen characters manually.
 *
 * TC: O(n)
 * SC: O(1)
 */
package basics;

import java.util.Scanner;

public class RemoveDupManual {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        boolean[] seen = new boolean[256];
        
        for (int i = 0; i < str.length(); i++) {
            if (!seen[str.charAt(i)]) {
                System.out.print(str.charAt(i));
                seen[str.charAt(i)] = true;
            }
        }
        sc.close();
    }
}