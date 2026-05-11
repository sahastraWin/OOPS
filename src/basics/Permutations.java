/*
 * Question: Write a program of permutation?
 *
 * Intuition: Use recursion and backtracking to swap characters at different positions.
 *
 * TC: O(n * n!)
 * SC: O(n) - recursion depth
 */
package basics;

import java.util.Scanner;

public class Permutations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        permute(str.toCharArray(), 0);
        sc.close();
    }
    
    private static void permute(char[] arr, int i) {
        if (i == arr.length - 1) {
            System.out.println(new String(arr));
            return;
        }
        for (int j = i; j < arr.length; j++) {
            swap(arr, i, j);
            permute(arr, i + 1);
            swap(arr, i, j); // backtrack
        }
    }
    
    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}