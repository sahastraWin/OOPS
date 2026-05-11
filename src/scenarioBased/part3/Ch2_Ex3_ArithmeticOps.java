package scenarioBased.part3;/*
 * Chapter 2, Exercise 3
 * Write a program that generates the following output:
 *   10
 *   20
 *   19
 * Use an integer constant for the 10, an arithmetic assignment operator to generate the 20,
 * and a decrement operator to generate the 19.
 */

public class Ch2_Ex3_ArithmeticOps {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(n);
        n *= 2;
        System.out.println(n);
        n--;
        System.out.println(n);
    }
}
