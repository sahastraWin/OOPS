package scenarioBased.part3;/*
 * Chapter 4, Exercise 10
 * Create a structure called sterling that stores money amounts in the old-style British system
 * (pounds, shillings, pence - all type int). The program should ask the user to enter a money
 * amount in new-style decimal pounds (type double), convert it to the old-style system,
 * store it in a variable of type struct sterling, and then display this amount in
 * pounds-shillings-pence format.
 */

import java.util.Scanner;

public class Ch4_Ex10_Sterling {

    static class Sterling {
        int pounds, shillings, pence;

        Sterling(double decimalPounds) {
            int totalOldPence = (int) Math.round(decimalPounds * 240);
            this.pounds = totalOldPence / 240;
            this.shillings = (totalOldPence % 240) / 12;
            this.pence = totalOldPence % 12;
        }

        @Override
        public String toString() {
            return String.format("\u00a3%d.%d.%d", pounds, shillings, pence);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount in decimal pounds: ");
        double amount = scanner.nextDouble();

        Sterling s = new Sterling(amount);
        System.out.println("In old notation: " + s);
        scanner.close();
    }
}
