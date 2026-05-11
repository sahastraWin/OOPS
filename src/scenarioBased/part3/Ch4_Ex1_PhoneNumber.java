package scenarioBased.part3;/*
 * Chapter 4, Exercise 1
 * A phone number such as (212) 767-8900 can be thought of as having three parts:
 * the area code (212), the exchange (767), and the number (8900).
 * Write a program that uses a structure to store these three parts of a phone number separately.
 * Call the structure phone. Create two structure variables of type phone.
 * Initialize one, and have the user input a number for the other one. Then display both numbers.
 * Example:
 *   Enter your area code, exchange, and number: 415 555 1212
 *   My number is (212) 767-8900
 *   Your number is (415) 555-1212
 */

import java.util.Scanner;

public class Ch4_Ex1_PhoneNumber {

    static class Phone {
        int areaCode, exchange, number;

        Phone(int areaCode, int exchange, int number) {
            this.areaCode = areaCode;
            this.exchange = exchange;
            this.number = number;
        }

        @Override
        public String toString() {
            return String.format("(%d) %d-%04d", areaCode, exchange, number);
        }
    }

    public static void main(String[] args) {
        Phone mine = new Phone(212, 767, 8900);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your area code, exchange, and number: ");
        int ac = scanner.nextInt(), ex = scanner.nextInt(), num = scanner.nextInt();
        Phone yours = new Phone(ac, ex, num);

        System.out.println("My number is " + mine);
        System.out.println("Your number is " + yours);
        scanner.close();
    }
}
