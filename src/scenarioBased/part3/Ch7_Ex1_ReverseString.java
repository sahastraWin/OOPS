package scenarioBased.part3;/*
 * Chapter 7, Exercise 1
 * Write a function called reversit() that reverses a C-string (an array of char).
 * Use a for loop that swaps the first and last characters, then the second and
 * next-to-last characters, and so on.
 * Write a program to exercise reversit(). The program should get a string from the user,
 * call reversit(), and print out the result. Use an input method that allows embedded blanks.
 * Test the program with Napoleon's famous phrase, "Able was I ere I saw Elba."
 */

import java.util.Scanner;

public class Ch7_Ex1_ReverseString {

    static char[] reversit(char[] s) {
        int left = 0, right = s.length - 1;
        for (; left < right; left++, right--) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
        }
        return s;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        char[] chars = input.toCharArray();
        reversit(chars);
        System.out.println("Reversed: " + new String(chars));
        scanner.close();
    }
}
