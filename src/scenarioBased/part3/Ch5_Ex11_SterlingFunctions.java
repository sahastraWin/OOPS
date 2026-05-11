package scenarioBased.part3;/*
 * Chapter 5, Exercise 11
 * Write a program, based on the sterling structure of Exercise 10 in Chapter 4, that
 * obtains from the user two money amounts in old-style British format (£9:19:11), adds them,
 * and displays the result in old-style format. Use three functions:
 *   1. getSterling() – gets a pounds-shillings-pence value from user, returns Sterling
 *   2. addSterling() – takes two Sterling arguments, returns their sum as Sterling
 *   3. putSterling() – takes a Sterling argument and displays its value
 */

import java.util.Scanner;

public class Ch5_Ex11_SterlingFunctions {

    static class Sterling {
        int pounds, shillings, pence;
        Sterling(int p, int s, int d) { this.pounds = p; this.shillings = s; this.pence = d; }
    }

    static Sterling getSterling(Scanner scanner) {
        System.out.print("Enter amount (P.S.D, e.g. 9.19.11): ");
        String s = scanner.next().replace("\u00a3", "");
        String[] parts = s.split("\\.");
        return new Sterling(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }

    static Sterling addSterling(Sterling a, Sterling b) {
        int pence = a.pence + b.pence;
        int shillings = a.shillings + b.shillings + pence / 12;
        pence %= 12;
        int pounds = a.pounds + b.pounds + shillings / 20;
        shillings %= 20;
        return new Sterling(pounds, shillings, pence);
    }

    static void putSterling(Sterling s) {
        System.out.printf("\u00a3%d.%d.%d%n", s.pounds, s.shillings, s.pence);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sterling s1 = getSterling(scanner);
        Sterling s2 = getSterling(scanner);
        Sterling sum = addSterling(s1, s2);
        System.out.print("Total: ");
        putSterling(sum);
        scanner.close();
    }
}
