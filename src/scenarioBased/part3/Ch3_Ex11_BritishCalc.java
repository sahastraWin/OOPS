package scenarioBased.part3;/*
 * Chapter 3, Exercise 11
 * Create a three-function calculator for old-style English currency, where money amounts
 * are specified in pounds, shillings, and pence. The calculator should allow the user to
 * add or subtract two money amounts, or to multiply a money amount by a floating-point number.
 * (Division is ignored.) Use the general style of the ordinary four-function calculator in Exercise 4.
 * Example:
 *   Enter amount, operator (+, -, *), amount/scalar: £5.10.6 + £3.2.6
 */

import java.util.Scanner;

public class Ch3_Ex11_BritishCalc {

    private static int[] parse(String s) {
        s = s.replace("\u00a3", "").trim();
        String[] parts = s.split("\\.");
        return new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])};
    }

    private static String format(int pounds, int shillings, int pence) {
        return String.format("\u00a3%d.%d.%d", pounds, shillings, pence);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char again;

        do {
            System.out.print("Enter amount, operator (+, -, *), amount or scalar: ");
            String first = scanner.next();
            char op = scanner.next().charAt(0);
            String second = scanner.next();

            int[] a = parse(first);
            int totalPenceA = a[0] * 240 + a[1] * 12 + a[2];

            int resultPence = 0;

            if (op == '+') {
                int[] b = parse(second);
                int totalPenceB = b[0] * 240 + b[1] * 12 + b[2];
                resultPence = totalPenceA + totalPenceB;
            } else if (op == '-') {
                int[] b = parse(second);
                int totalPenceB = b[0] * 240 + b[1] * 12 + b[2];
                resultPence = totalPenceA - totalPenceB;
            } else if (op == '*') {
                double scalar = Double.parseDouble(second);
                resultPence = (int) Math.round(totalPenceA * scalar);
            } else {
                System.out.println("Unknown operator.");
                System.out.print("Do another (y/n)? ");
                again = scanner.next().charAt(0);
                continue;
            }

            int pounds = resultPence / 240;
            int shillings = (resultPence % 240) / 12;
            int pence = resultPence % 12;
            System.out.println("Result = " + format(pounds, shillings, pence));

            System.out.print("Do another (y/n)? ");
            again = scanner.next().charAt(0);
        } while (again == 'y' || again == 'Y');

        scanner.close();
    }
}
