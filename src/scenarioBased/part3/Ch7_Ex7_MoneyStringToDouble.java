package scenarioBased.part3;/*
 * Chapter 7, Exercise 7
 * One of the weaknesses of C++ is that it does not contain a built-in type for monetary values.
 * Write a function called mstold() that takes a money string like "$1,234,567,890,123.99"
 * as an argument, and returns the equivalent value as a double (representing long double in C++).
 * You'll need to treat the money string as an array of characters, going through it character
 * by character, copying only digits (1-9) and the decimal point into another string.
 * Ignore everything else (dollar sign and commas). You can then use Double.parseDouble()
 * to convert the resulting pure string to a double.
 * Write a main() program to test mstold() by repeatedly obtaining a money string from the
 * user and displaying the corresponding double.
 */

import java.util.Scanner;

public class Ch7_Ex7_MoneyStringToDouble {

    static double mstold(String moneyStr) {
        StringBuilder sb = new StringBuilder();
        for (char ch : moneyStr.toCharArray()) {
            if (Character.isDigit(ch) || ch == '.') {
                sb.append(ch);
            }
        }
        if (sb.length() == 0) return 0.0;
        return Double.parseDouble(sb.toString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char again;

        do {
            System.out.print("Enter a money string (e.g. $1,234,567.89): ");
            String moneyStr = scanner.next();
            double value = mstold(moneyStr);
            System.out.printf("Numeric value: %.2f%n", value);
            System.out.print("Do another (y/n)? ");
            again = scanner.next().charAt(0);
        } while (again == 'y' || again == 'Y');

        scanner.close();
    }
}
