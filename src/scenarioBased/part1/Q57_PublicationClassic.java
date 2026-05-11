package scenarioBased.part1;/*
/*
 * Question 57 - Classic Scenario: PUBLICATION HIERARCHY
 * A publishing company markets book and CD versions of its works.
 * Create a base class Publication (title, price).
 * Derive Book (adds page count) and CD (adds playing time).
 * Each class has getData() and putData().
 * Write a main() creating instances of both.
 */

import java.util.Scanner;

public class Q57_PublicationClassic {
    static class Publication {
        protected String title;
        protected double price;

        void getData(Scanner sc) {
            System.out.print("  Title: ");  title = sc.next();
            System.out.print("  Price: ");  price = sc.nextDouble();
        }

        void putData() {
            System.out.printf("  Title: %s | Price: %.2f", title, price);
        }
    }

    static class Book extends Publication {
        private int pageCount;

        @Override void getData(Scanner sc) {
            super.getData(sc);
            System.out.print("  Pages: "); pageCount = sc.nextInt();
        }

        @Override void putData() {
            super.putData();
            System.out.println(" | Pages: " + pageCount);
        }
    }

    static class CD extends Publication {
        private float playTime;

        @Override void getData(Scanner sc) {
            super.getData(sc);
            System.out.print("  Play time (min): "); playTime = sc.nextFloat();
        }

        @Override void putData() {
            super.putData();
            System.out.printf(" | Play time: %.1f min%n", playTime);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Book b = new Book(); CD c = new CD();

        System.out.println("Enter Book details:");  b.getData(sc);
        System.out.println("Enter CD details:");    c.getData(sc);

        System.out.println("\nBook: "); b.putData();
        System.out.println("CD: ");   c.putData();
        sc.close();
    }
}
