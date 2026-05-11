package scenarioBased.part3;/*
 * Chapter 3, Exercise 4
 * Create the equivalent of a four-function calculator. The program should ask the user to
 * enter a number, an operator, and another number. (Use floating point.) It should then
 * carry out the specified arithmetical operation: adding, subtracting, multiplying, or
 * dividing the two numbers. Use a switch statement to select the operation. Display the result.
 * When it finishes the calculation, ask whether the user wants to do another calculation.
 * The response can be 'y' or 'n'.
 * Example:
 *   Enter first number, operator, second number: 10 / 3
 *   Answer = 3.333333
 *   Do another (y/n)? y
 *   Enter first number, operator, second number: 12 + 100
 *   Answer = 112
 *   Do another (y/n)? n
 */

import java.util.Scanner;

public class Ch3_Ex4_Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char again;

        do {
            System.out.print("Enter first number, operator, second number: ");
            double a = scanner.nextDouble();
            char op = scanner.next().charAt(0);
            double b = scanner.nextDouble();

            double result = 0;
            boolean valid = true;

            switch (op) {
                case '+': result = a + b; break;
                case '-': result = a - b; break;
                case '*': result = a * b; break;
                case '/':
                    if (b == 0) { System.out.println("Error: Division by zero."); valid = false; }
                    else result = a / b;
                    break;
                default:
                    System.out.println("Unknown operator."); valid = false;
            }

            if (valid) System.out.printf("Answer = %g%n", result);

            System.out.print("Do another (y/n)? ");
            again = scanner.next().charAt(0);
        } while (again == 'y' || again == 'Y');

        scanner.close();
    }
}
