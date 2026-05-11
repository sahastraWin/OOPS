package scenarioBased.part3;/*
 * Chapter 3, Exercise 3
 * Operators such as >>, which read input from the keyboard, must be able to convert
 * a series of digits into a number. Write a program that does the same thing.
 * It should allow the user to type up to six digits, and then display the resulting
 * number as a type long integer. The digits should be read individually as characters.
 * Constructing the number involves multiplying the existing value by 10 and then adding
 * the new digit. (Hint: Subtract 48 or '0' to go from ASCII to a numerical digit.)
 * Example:
 *   Enter a number: 123456
 *   Number is: 123456
 */

import java.util.Scanner;

public class Ch3_Ex3_DigitsToNumber {
    public static void main(String[] args) throws Exception {
        System.out.print("Enter up to 6 digits: ");
        String input = new Scanner(System.in).nextLine().trim();
        if (input.length() > 6) input = input.substring(0, 6);

        long number = 0;
        for (char ch : input.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                number = number * 10 + (ch - '0');
            }
        }
        System.out.println("Number is: " + number);
    }
}
