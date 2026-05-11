package scenarioBased.part1;/*
/*
 * Question 67 - Classic Scenario: MATRIX CLASS
 * Create a class Matrix for 3×3 integer matrices.
 * Implement add() and multiply() methods for matrix operations.
 * Include an input function and display function. Test in main().
 */

import java.util.Scanner;

public class Q67_MatrixClassic {
    static class Matrix {
        private int[][] data = new int[3][3];

        void input(Scanner sc) {
            System.out.println("Enter 9 values (row by row):");
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    data[i][j] = sc.nextInt();
        }

        Matrix add(Matrix other) {
            Matrix result = new Matrix();
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    result.data[i][j] = this.data[i][j] + other.data[i][j];
            return result;
        }

        Matrix multiply(Matrix other) {
            Matrix result = new Matrix();
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    for (int k = 0; k < 3; k++)
                        result.data[i][j] += this.data[i][k] * other.data[k][j];
            return result;
        }

        void display() {
            for (int[] row : data) {
                for (int v : row) System.out.printf("%6d", v);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Matrix m1 = new Matrix(), m2 = new Matrix();

        System.out.println("Matrix 1:"); m1.input(sc);
        System.out.println("Matrix 2:"); m2.input(sc);

        System.out.println("\nMatrix 1:");    m1.display();
        System.out.println("Matrix 2:");    m2.display();
        System.out.println("M1 + M2:");     m1.add(m2).display();
        System.out.println("M1 * M2:");     m1.multiply(m2).display();
        sc.close();
    }
}
