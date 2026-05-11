package scenarioBased.part1;/*
/*
 * Question 56 - Classic Scenario: BOOK CLASS
 * Define a class BOOK with members Book_No, Book_title, and Price.
 * Include total_cost() to calculate cost for N copies,
 * input() to read book data, and purchase() to ask for number
 * of copies and print the total cost.
 */

import java.util.Scanner;

public class Q56_BookClass {
    static class Book {
        private int bookNo;
        private String title;
        private double price;

        void input(Scanner sc) {
            System.out.print("Book Number: ");  bookNo = sc.nextInt();
            System.out.print("Book Title: ");   title  = sc.next();
            System.out.print("Book Price: ");   price  = sc.nextDouble();
        }

        double totalCost(int copies) { return price * copies; }

        void purchase(Scanner sc) {
            System.out.print("Enter number of copies: ");
            int n = sc.nextInt();
            System.out.printf("Total cost for %d copies of \"%s\": %.2f%n", n, title, totalCost(n));
        }

        void display() {
            System.out.printf("Book No: %d | Title: %-20s | Price: %.2f%n", bookNo, title, price);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Book book = new Book();
        book.input(sc);
        book.display();
        book.purchase(sc);
        sc.close();
    }
}
