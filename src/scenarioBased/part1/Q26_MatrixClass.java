package scenarioBased.part1;/*
/*
 * Question 26 - Chapter 7: Arrays and Strings
 * Create a class called Matrix that stores a 3×3 matrix of float values.
 * Include functions to fill the matrix from user input, add two matrices,
 * and display the result.
 */

import java.util.Scanner;

public class Q26_MatrixClass {
    static class Matrix {
        private float[][] data = new float[3][3];

        void fill(Scanner sc) {
            System.out.println("Enter 9 values row by row:");
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    data[i][j] = sc.nextFloat();
        }

        Matrix add(Matrix other) {
            Matrix result = new Matrix();
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    result.data[i][j] = this.data[i][j] + other.data[i][j];
            return result;
        }

        void display() {
            for (float[] row : data) {
                for (float v : row) System.out.printf("%8.2f", v);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Matrix m1 = new Matrix(), m2 = new Matrix();
        System.out.println("Matrix 1:"); m1.fill(sc);
        System.out.println("Matrix 2:"); m2.fill(sc);
        System.out.println("Sum:"); m1.add(m2).display();
        sc.close();
    }
}
