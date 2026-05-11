/*
 * Question: Write a program to concat two Strings?
 *
 * Intuition: Use StringBuilder for efficiency over '+' in loops, or simple '+' for one-off.
 *
 * TC: O(n + m)
 * SC: O(n + m)
 */
package basics;

import java.util.Scanner;

public class ConcatStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();
        System.out.println(s1 + " " + s2);
        sc.close();
    }
}