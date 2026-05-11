package scenarioBased.part1;/*
/*
 * Question 32 - Chapter 9: Inheritance
 * A publishing company markets both book and compact disk versions.
 * Create a base class Publication that stores title (String) and price (float).
 * Derive two classes: Book (adds page count) and CD (adds playing time in minutes).
 * Each class has getData() and putData() methods.
 */

import java.util.Scanner;

public class Q32_PublicationHierarchy {
    static class Publication {
        protected String title;
        protected float price;

        void getData(Scanner sc) {
            System.out.print("Title: "); title = sc.next();
            System.out.print("Price: "); price = sc.nextFloat();
        }

        void putData() {
            System.out.printf("Title: %s | Price: %.2f%n", title, price);
        }
    }

    static class Book extends Publication {
        private int pageCount;

        @Override void getData(Scanner sc) {
            super.getData(sc);
            System.out.print("Page count: "); pageCount = sc.nextInt();
        }

        @Override void putData() {
            super.putData();
            System.out.println("Pages: " + pageCount);
        }
    }

    static class CD extends Publication {
        private float playingTime;

        @Override void getData(Scanner sc) {
            super.getData(sc);
            System.out.print("Playing time (minutes): "); playingTime = sc.nextFloat();
        }

        @Override void putData() {
            super.putData();
            System.out.printf("Playing time: %.1f min%n", playingTime);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Book book = new Book();
        CD cd = new CD();
        System.out.println("--- Enter Book Details ---"); book.getData(sc);
        System.out.println("--- Enter CD Details ---");   cd.getData(sc);
        System.out.println("\n--- Book Info ---");  book.putData();
        System.out.println("--- CD Info ---");    cd.putData();
        sc.close();
    }
}
