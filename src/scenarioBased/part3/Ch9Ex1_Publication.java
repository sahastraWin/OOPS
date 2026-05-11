package scenarioBased.part3;/*
 * Chapter 9, Exercise 1 (Starred):
 * Imagine a publishing company that markets both book and audiocassette versions of its
 * works. Create a class publication that stores the title (a string) and price (type float)
 * of a publication. From this class derive two classes: book, which adds a page count (type int),
 * and tape, which adds a playing time in minutes (type float). Each of these three classes
 * should have a getdata() function to get its data from the user at the keyboard, and a
 * putdata() function to display its data.
 * Write a main() program to test the book and tape classes by creating instances of them,
 * asking the user to fill in data with getdata(), and then displaying the data with putdata().
 */

import java.util.Scanner;

public class Ch9Ex1_Publication {

    static Scanner sc = new Scanner(System.in);

    static class Publication {
        protected String title;
        protected float price;

        public void getdata() {
            System.out.print("Enter title: "); title = sc.nextLine();
            System.out.print("Enter price: $"); price = Float.parseFloat(sc.nextLine().trim());
        }

        public void putdata() {
            System.out.printf("  Title: %s%n  Price: $%.2f%n", title, price);
        }
    }

    static class Book extends Publication {
        private int pageCount;

        @Override
        public void getdata() {
            super.getdata();
            System.out.print("Enter page count: "); pageCount = Integer.parseInt(sc.nextLine().trim());
        }

        @Override
        public void putdata() {
            System.out.println("[BOOK]");
            super.putdata();
            System.out.println("  Pages: " + pageCount);
        }
    }

    static class Tape extends Publication {
        private float playingTime; // in minutes

        @Override
        public void getdata() {
            super.getdata();
            System.out.print("Enter playing time (minutes): "); playingTime = Float.parseFloat(sc.nextLine().trim());
        }

        @Override
        public void putdata() {
            System.out.println("[TAPE]");
            super.putdata();
            System.out.printf("  Playing time: %.1f minutes%n", playingTime);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Publication System ===");

        Book b = new Book();
        System.out.println("\nEnter book data:");
        b.getdata();

        Tape t = new Tape();
        System.out.println("\nEnter tape data:");
        t.getdata();

        System.out.println("\n--- Stored Data ---");
        b.putdata();
        t.putdata();

        sc.close();
    }
}
