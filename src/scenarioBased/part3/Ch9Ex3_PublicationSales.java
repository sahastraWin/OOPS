package scenarioBased.part3;/*
 * Chapter 9, Exercise 3 (Starred):
 * Start with the publication, book, and tape classes of Exercise 1. Add a base class sales
 * that holds an array of three floats so that it can record the dollar sales of a particular
 * publication for the last three months. Include a getdata() function to get three sales amounts
 * from the user, and a putdata() function to display the sales figures. Alter the book and tape
 * classes so they are derived from both publication and sales. An object of class book or tape
 * should input and output sales data along with its other data. Write a main() function to create
 * a book object and a tape object and exercise their input/output capabilities.
 */

import java.util.Scanner;

public class Ch9Ex3_PublicationSales {

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

    static class Sales {
        protected float[] monthlySales = new float[3];

        public void getdata() {
            System.out.println("Enter sales for last 3 months:");
            for (int i = 0; i < 3; i++) {
                System.out.printf("  Month %d: $", i + 1);
                monthlySales[i] = Float.parseFloat(sc.nextLine().trim());
            }
        }

        public void putdata() {
            System.out.println("  Monthly Sales:");
            for (int i = 0; i < 3; i++)
                System.out.printf("    Month %d: $%.2f%n", i + 1, monthlySales[i]);
        }
    }

    // Multiple inheritance via composition (Java doesn't support multiple class inheritance)
    static class Book extends Publication {
        private int pageCount;
        private Sales sales = new Sales();

        @Override
        public void getdata() {
            super.getdata();
            System.out.print("Enter page count: "); pageCount = Integer.parseInt(sc.nextLine().trim());
            sales.getdata();
        }

        @Override
        public void putdata() {
            System.out.println("[BOOK]");
            super.putdata();
            System.out.println("  Pages: " + pageCount);
            sales.putdata();
        }
    }

    static class Tape extends Publication {
        private float playingTime;
        private Sales sales = new Sales();

        @Override
        public void getdata() {
            super.getdata();
            System.out.print("Enter playing time (minutes): "); playingTime = Float.parseFloat(sc.nextLine().trim());
            sales.getdata();
        }

        @Override
        public void putdata() {
            System.out.println("[TAPE]");
            super.putdata();
            System.out.printf("  Playing time: %.1f minutes%n", playingTime);
            sales.putdata();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Publication Sales System ===");

        Book b = new Book();
        System.out.println("Enter book data:");
        b.getdata();

        Tape t = new Tape();
        System.out.println("\nEnter tape data:");
        t.getdata();

        System.out.println("\n--- Book Data ---");
        b.putdata();
        System.out.println("\n--- Tape Data ---");
        t.putdata();

        sc.close();
    }
}
