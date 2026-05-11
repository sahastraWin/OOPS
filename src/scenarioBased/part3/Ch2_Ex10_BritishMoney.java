package scenarioBased.part3;/*
 * Chapter 2, Exercise 10
 * In the heyday of the British empire, Great Britain used a monetary system based on
 * pounds, shillings, and pence. There were 20 shillings to a pound, and 12 pence to a
 * shilling. The notation used the pound sign and two decimal points, e.g., £5.2.8 meant
 * 5 pounds, 2 shillings, and 8 pence.
 * The new monetary system consists of only pounds and pence, with 100 pence to a pound.
 * Write a program to convert old pounds-shillings-pence format to decimal pounds.
 * Example:
 *   Enter pounds: 7
 *   Enter shillings: 17
 *   Enter pence: 9
 *   Decimal pounds = £7.89
 */

import java.util.Scanner;

public class Ch2_Ex10_BritishMoney {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter pounds: ");
        int pounds = scanner.nextInt();
        System.out.print("Enter shillings: ");
        int shillings = scanner.nextInt();
        System.out.print("Enter pence: ");
        int pence = scanner.nextInt();

        // Convert: total pence = pounds*240 + shillings*12 + pence
        // New decimal pounds = totalPence / 100
        double totalOldPence = pounds * 240.0 + shillings * 12.0 + pence;
        double decimalPounds = totalOldPence / 100.0;

        System.out.printf("Decimal pounds = \u00a3%.2f%n", decimalPounds);
        scanner.close();
    }
}
