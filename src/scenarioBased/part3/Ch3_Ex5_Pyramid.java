package scenarioBased.part3;/*
 * Chapter 3, Exercise 5
 * Use for loops to construct a program that displays a pyramid of Xs on the screen.
 * The pyramid should be 20 lines high (showing 5 lines here for illustration):
 *         X
 *        XXX
 *       XXXXX
 *      XXXXXXX
 *     XXXXXXXXX
 * Use nested loops: one inner loop to print spaces, one to print Xs,
 * inside an outer loop that steps down the screen line by line.
 */

public class Ch3_Ex5_Pyramid {
    private static final int LINES = 20;

    public static void main(String[] args) {
        for (int line = 1; line <= LINES; line++) {
            // Print spaces
            for (int space = 0; space < LINES - line; space++) System.out.print(" ");
            // Print X's
            for (int x = 0; x < 2 * line - 1; x++) System.out.print("X");
            System.out.println();
        }
    }
}
