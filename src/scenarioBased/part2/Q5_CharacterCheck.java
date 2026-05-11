package scenarioBased.part2;/*
 * Question 5: Write a program in java to check whether the entered character
 * is alphabet, digit or space character.
 * If it is alphabet then print whether it is capital or small alphabet.
 * Also change the alphabet into the reverse case.
 */

import java.util.Scanner;

public class Q5_CharacterCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a character: ");
        char ch = sc.next().charAt(0);

        if (Character.isLetter(ch)) {
            // It's an alphabet
            System.out.println("'" + ch + "' is an Alphabet.");

            if (Character.isUpperCase(ch)) {
                // Capital letter
                System.out.println("It is a Capital (Uppercase) alphabet.");
                System.out.println("Reverse case (lowercase): " + Character.toLowerCase(ch));
            } else {
                // Small letter
                System.out.println("It is a Small (Lowercase) alphabet.");
                System.out.println("Reverse case (uppercase): " + Character.toUpperCase(ch));
            }
        } else if (Character.isDigit(ch)) {
            // It's a digit
            System.out.println("'" + ch + "' is a Digit.");
        } else if (ch == ' ') {
            // It's a space
            System.out.println("The entered character is a Space.");
        } else {
            // Special character
            System.out.println("'" + ch + "' is a Special Character.");
        }
    }
}
