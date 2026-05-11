package scenarioBased.part3;/*
 * Chapter 7, Exercise 11
 * Write a function called ldtoms() to convert a number represented as type long double
 * (represented here as double) to the same value represented as a money string.
 * First check that the value is not too large (don't convert any number greater than
 * 9,999,999,999,999,990.00). Then convert the long double to a pure string stored in memory.
 * The resulting formatted string will have a dollar sign; copy one digit from the double string
 * into the new string every three digits, inserting a comma. Suppress leading zeros.
 * Display $3,124.95, not $0,000,000,000,003,124.95.
 * Write a main() program to exercise this function.
 */

import java.util.Scanner;

public class Ch7_Ex11_DoubleToMoneyString {

    static final double MAX_VALUE = 9_999_999_999_999_990.00;

    static String ldtoms(double value) {
        if (value < 0 || value > MAX_VALUE) {
            return "ERROR: Value out of range";
        }

        // Format to 2 decimal places
        String numStr = String.format("%.2f", value);

        // Split into integer and decimal parts
        int dotPos = numStr.indexOf('.');
        String intPart = numStr.substring(0, dotPos);
        String decPart = numStr.substring(dotPos + 1); // 2 digits

        // Strip leading zeros from integer part
        intPart = intPart.replaceFirst("^0+(?!$)", "");
        if (intPart.isEmpty()) intPart = "0";

        // Insert commas every 3 digits from the right
        StringBuilder sb = new StringBuilder();
        int len = intPart.length();
        for (int i = 0; i < len; i++) {
            sb.append(intPart.charAt(i));
            int remaining = len - i - 1;
            if (remaining > 0 && remaining % 3 == 0) sb.append(',');
        }

        return "$" + sb.toString() + "." + decPart;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char again;

        do {
            System.out.print("Enter a number (as long double): ");
            double value = scanner.nextDouble();
            System.out.println("Money string: " + ldtoms(value));
            System.out.print("Do another (y/n)? ");
            again = scanner.next().charAt(0);
        } while (again == 'y' || again == 'Y');

        scanner.close();
    }
}
