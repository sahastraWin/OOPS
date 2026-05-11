/*
 * Question: input: {1,2,3,4,5,6,7,8,9,10} output: 1, 10, 2, 9, 3, 8, 4, 7, 5, 6
 *
 * Intuition: Sort the array first. Use two pointers (i at start, j at end). 
 * Alternately pick elements from both ends.
 *
 * TC: O(n log n)
 * SC: O(n)
 */
package basics;

import java.util.*;

public class SpecialSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        
        Arrays.sort(arr);
        int[] result = new int[n];
        int i = 0, j = n - 1, k = 0;
        
        while (i <= j) {
            if (k < n) result[k++] = arr[i++];
            if (k < n) result[k++] = arr[j--];
        }
        
        for (int x : result) System.out.print(x + " ");
        sc.close();
    }
}