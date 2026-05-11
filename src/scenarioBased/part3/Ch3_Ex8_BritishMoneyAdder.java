package scenarioBased.part3;/*
 * Chapter 3, Exercise 8
 * Write a program that repeatedly asks the user to enter two money amounts expressed in
 * old-style British currency: pounds, shillings, and pence. The program should then
 * add the two amounts and display the answer in pounds, shillings, and pence.
 * Use a do loop that asks the user whether the program should be terminated.
 * Example:
 *   Enter first amount: £5.10.6
 *   Enter second amount: £3.2.6
 *   Total is £8.13.0
 *   Do you wish to continue (y/n)?
 *
 * To add: carry 1 shilling when pence > 11, carry 1 pound when shillings > 19.
 */

import java.util.Scanner;

public class Ch3_Ex8_BritishMoneyAdder {

    /** Parse "£P.S.D" or "P.S.D" string into int[]{pounds, shillings, pence} */
    private static int[] parse(String s) {
        s = s.replace("\u00a3", "").trim();
        String[] parts = s.split("\\.");
        return new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char again;

        do {
            System.out.print("Enter first amount (P.S.D): ");
            int[] a = parse(scanner.next());
            System.out.print("Enter second amount (P.S.D): ");
            int[] b = parse(scanner.next());

            int pence = a[2] + b[2];
            int shillings = a[1] + b[1] + pence / 12;
            pence %= 12;
            int pounds = a[0] + b[0] + shillings / 20;
            shillings %= 20;

            System.out.printf("Total is \u00a3%d.%d.%d%n", pounds, shillings, pence);

            System.out.print("Do you wish to continue (y/n)? ");
            again = scanner.next().charAt(0);
        } while (again == 'y' || again == 'Y');

        scanner.close();
    }
}
