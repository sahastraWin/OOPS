package scenarioBased.part1;/*
/*
 * Question 8 - Chapter 3: Loops and Decisions
 * Write a program that simulates a simple guessing game.
 * The program picks a number between 1 and 100 and the user tries
 * to guess it, with hints for higher/lower.
 */

import java.util.Random;
import java.util.Scanner;

public class Q08_GuessingGame {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        int secret = rand.nextInt(100) + 1;
        int attempts = 0;

        System.out.println("Guess the number between 1 and 100!");
        while (true) {
            System.out.print("Your guess: ");
            int guess = sc.nextInt();
            attempts++;
            if (guess < secret) System.out.println("Too low! Try higher.");
            else if (guess > secret) System.out.println("Too high! Try lower.");
            else {
                System.out.printf("Correct! You guessed it in %d attempt(s).%n", attempts);
                break;
            }
        }
        sc.close();
    }
}
