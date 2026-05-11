package scenarioBased.part1;/*
/*
 * Question 5 - Chapter 3: Loops and Decisions
 * Create a three-function calculator for old-style English currency
 * (pounds, shillings, pence).
 * Allow the user to add, subtract, or multiply money amounts.
 * 1 pound = 20 shillings, 1 shilling = 12 pence
 */

import java.util.Scanner;

public class Q05_EnglishCurrency {
    static class Currency {
        int pounds, shillings, pence;

        Currency(int p, int s, int d) {
            // Normalize
            int totalPence = p * 240 + s * 12 + d;
            pounds = totalPence / 240;
            totalPence %= 240;
            shillings = totalPence / 12;
            pence = totalPence % 12;
        }

        @Override
        public String toString() {
            return pounds + " pounds, " + shillings + " shillings, " + pence + " pence";
        }
    }

    static Currency read(Scanner sc, String label) {
        System.out.print(label + " - Enter pounds shillings pence: ");
        return new Currency(sc.nextInt(), sc.nextInt(), sc.nextInt());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Old English Currency Calculator");
        System.out.println("Operations: 1=Add  2=Subtract  3=Multiply");
        System.out.print("Choose operation: ");
        int op = sc.nextInt();

        Currency a = read(sc, "First amount");
        int totalA = a.pounds * 240 + a.shillings * 12 + a.pence;

        if (op == 3) {
            System.out.print("Enter multiplier: ");
            int mult = sc.nextInt();
            System.out.println("Result: " + new Currency(0, 0, totalA * mult));
        } else {
            Currency b = read(sc, "Second amount");
            int totalB = b.pounds * 240 + b.shillings * 12 + b.pence;
            int result = (op == 1) ? totalA + totalB : totalA - totalB;
            if (result < 0) { System.out.println("Result is negative!"); return; }
            System.out.println("Result: " + new Currency(0, 0, result));
        }
        sc.close();
    }
}
