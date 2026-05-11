/*
 * Question: Rotate matrix clockwise and anti-clockwise.
 *
 * Intuition: 
 * Clockwise: Transpose then reverse each row.
 * Anti-clockwise: Transpose then reverse each column.
 *
 * TC: O(r * c)
 * SC: O(1) - in-place rotation
 */
package basics;

import java.util.Scanner;

public class MatrixRotate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] mat = new int[n][n];
        for (int i = 0; i < n; i++) 
            for (int j = 0; j < n; j++) 
                mat[i][j] = sc.nextInt();
        
        // Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }
        
        // Clockwise: Reverse each row
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                int temp = mat[i][left];
                mat[i][left] = mat[i][right];
                mat[i][right] = temp;
                left++; right--;
            }
        }
        
        // Printing
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) System.out.print(mat[i][j] + " ");
            System.out.println();
        }
        sc.close();
    }
}