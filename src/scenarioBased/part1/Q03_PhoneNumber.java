package scenarioBased.part1;/*
/*
 * Question 3 - Chapter 2: Java Programming Basics
 * A phone number such as (212) 767-8900 can be represented in a structure
 * with three integer members: area code (212), exchange (767), and number (8900).
 * Write a program that gets a phone number from the user and stores it in a structure.
 */

import java.util.Scanner;

public class Q03_PhoneNumber {
    static class PhoneNumber {
        int areaCode;
        int exchange;
        int number;

        PhoneNumber(int areaCode, int exchange, int number) {
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
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter area code: ");
        int area = sc.nextInt();
        System.out.print("Enter exchange: ");
        int exchange = sc.nextInt();
        System.out.print("Enter number: ");
        int number = sc.nextInt();

        PhoneNumber phone = new PhoneNumber(area, exchange, number);
        System.out.println("Phone number stored: " + phone);
        sc.close();
    }
}
