package scenarioBased.part3;/*
 * Chapter 3, Exercise 12
 * Create a four-function calculator for fractions.
 * Formulas:
 *   Addition:       a/b + c/d = (a*d + b*c) / (b*d)
 *   Subtraction:    a/b - c/d = (a*d - b*c) / (b*d)
 *   Multiplication: a/b * c/d = (a*c) / (b*d)
 *   Division:       a/b / c/d = (a*d) / (b*c)
 * The user should type the first fraction, an operator, and a second fraction.
 * The program should then display the result and ask whether the user wants to continue.
 */

import java.util.Scanner;

public class Ch3_Ex12_FractionCalc {

    private static long gcd(long x, long y) {
        x = Math.abs(x); y = Math.abs(y);
        while (y != 0) { long t = y; y = x % y; x = t; }
        return x;
    }

    private static String reduceFraction(long num, long den) {
        if (den < 0) { num = -num; den = -den; }
        long g = gcd(Math.abs(num), den);
        return (num / g) + "/" + (den / g);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char again;

        do {
            System.out.print("Enter: fraction operator fraction (e.g. 1/2 + 3/4): ");
            String f1 = scanner.next();
            char op = scanner.next().charAt(0);
            String f2 = scanner.next();

            String[] p1 = f1.split("/"), p2 = f2.split("/");
            long a = Long.parseLong(p1[0]), b = Long.parseLong(p1[1]);
            long c = Long.parseLong(p2[0]), d = Long.parseLong(p2[1]);

            long rNum, rDen;
            switch (op) {
                case '+': rNum = a*d + b*c; rDen = b*d; break;
                case '-': rNum = a*d - b*c; rDen = b*d; break;
                case '*': rNum = a*c; rDen = b*d; break;
                case '/': rNum = a*d; rDen = b*c; break;
                default: System.out.println("Unknown operator."); rNum = 0; rDen = 1;
            }

            System.out.println("Result = " + reduceFraction(rNum, rDen));
            System.out.print("Do another (y/n)? ");
            again = scanner.next().charAt(0);
        } while (again == 'y' || again == 'Y');

        scanner.close();
    }
}
