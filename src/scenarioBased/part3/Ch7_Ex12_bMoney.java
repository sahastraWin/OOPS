package scenarioBased.part3;/*
 * Chapter 7, Exercise 12
 * Create a class called bMoney. It should store money amounts as long doubles (here: double).
 * Use the function mstold() to convert a money string entered as input into a double,
 * and the function ldtoms() to convert the double to a money string for display.
 * Call the input and output member functions getmoney() and putmoney().
 * Write another member function that adds two bMoney amounts; call it madd().
 * Adding bMoney objects is easy: just add the double member data amounts.
 * Write a main() program that repeatedly asks the user to enter two money strings,
 * and then displays the sum as a money string.
 *
 * class bMoney {
 *   private: long double money;
 *   public:
 *     bMoney();
 *     bMoney(char s[]);
 *     void madd(bMoney m1, bMoney m2);
 *     void getmoney();
 *     void putmoney();
 * };
 */

import java.util.Scanner;

public class Ch7_Ex12_bMoney {

    static class bMoney {
        private double money;

        bMoney()              { this.money = 0.0; }
        bMoney(String s)      { this.money = mstold(s); }

        void madd(bMoney m1, bMoney m2) { this.money = m1.money + m2.money; }

        void getmoney(Scanner scanner) {
            System.out.print("Enter money amount (e.g. $1,234.56): ");
            this.money = mstold(scanner.next());
        }

        void putmoney() { System.out.println(ldtoms(money)); }

        // ---- Static utility methods ----
        private static double mstold(String moneyStr) {
            StringBuilder sb = new StringBuilder();
            for (char ch : moneyStr.toCharArray()) {
                if (Character.isDigit(ch) || ch == '.') sb.append(ch);
            }
            return sb.length() == 0 ? 0.0 : Double.parseDouble(sb.toString());
        }

        private static String ldtoms(double value) {
            String numStr = String.format("%.2f", value);
            int dotPos = numStr.indexOf('.');
            String intPart = numStr.substring(0, dotPos).replaceFirst("^0+(?!$)", "");
            if (intPart.isEmpty()) intPart = "0";
            String decPart = numStr.substring(dotPos + 1);

            StringBuilder sb = new StringBuilder();
            int len = intPart.length();
            for (int i = 0; i < len; i++) {
                sb.append(intPart.charAt(i));
                int remaining = len - i - 1;
                if (remaining > 0 && remaining % 3 == 0) sb.append(',');
            }
            return "$" + sb + "." + decPart;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char again;

        do {
            bMoney m1 = new bMoney(), m2 = new bMoney(), sum = new bMoney();
            m1.getmoney(scanner);
            m2.getmoney(scanner);
            sum.madd(m1, m2);
            System.out.print("Sum = ");
            sum.putmoney();

            System.out.print("Do another (y/n)? ");
            again = scanner.next().charAt(0);
        } while (again == 'y' || again == 'Y');

        scanner.close();
    }
}
