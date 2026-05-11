package scenarioBased.part3;/*
 * Chapter 2, Exercise 12
 * Write the inverse of Exercise 10: the user enters an amount in Great Britain's
 * new decimal-pounds notation (pounds and pence), and the program converts it to
 * the old pounds-shillings-pence notation.
 * Example:
 *   Enter decimal pounds: 3.51
 *   Equivalent in old notation = £3.10.2.
 *
 * Strategy: Use integer truncation to extract components.
 *   float decpounds;     // input from user (new-style pounds)
 *   int pounds;          // old-style (integer) pounds
 *   float decfrac;       // decimal fraction (smaller than 1.0)
 *   pounds = (int) decpounds;        // remove decimal fraction
 *   decfrac = decpounds - pounds;    // regain decimal fraction
 * Then multiply decfrac by 20 to find shillings. A similar operation obtains pence.
 */

import java.util.Scanner;

public class Ch2_Ex12_DecimalToOldBritish {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter decimal pounds: ");
        float decpounds = scanner.nextFloat();

        // Convert to total old pence (1 decimal pound = 240 old pence)
        int totalOldPence = Math.round(decpounds * 100);

        int pounds = totalOldPence / 100;
        int remaining = totalOldPence % 100;
        // remaining new pence -> shillings and old pence
        // 1 shilling = 12 old pence; 1 new penny = 2.4 old pence... 
        // Actually: total old pence = decpounds * 100, 
        // but old system: 1 pound = 240 old pence, 1 shilling = 12 old pence
        // Correct approach: total old pence from decimal pounds
        totalOldPence = Math.round(decpounds * 240);
        pounds = totalOldPence / 240;
        int shillings = (totalOldPence % 240) / 12;
        int pence = totalOldPence % 12;

        System.out.printf("Equivalent in old notation = \u00a3%d.%d.%d.%n", pounds, shillings, pence);
        scanner.close();
    }
}
