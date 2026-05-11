package scenarioBased.part3;/*
 * Chapter 11, Exercise 1 (Starred):
 * Imagine the same publishing company described in Exercise 1 in Chapter 9 that markets
 * both book and audiocassette versions of its works. Create a class called publication that
 * stores the title (a string) and price (type float) of a publication.
 * From this class derive two classes: book, which adds a page count (type int); and tape,
 * which adds a playing time in minutes (type float). Each of the three classes should have
 * a getdata() function and a putdata() function.
 *
 * Write a main() program that creates an array of pointers to publication. In a loop, ask
 * the user for data about a particular book or tape, and use new to create an object of
 * type book or tape to hold the data. Put the pointer to the object in the array. When the
 * user has finished entering the data for all books and tapes, display the resulting data
 * for all the books and tapes entered, using a for loop and a single statement such as:
 *   pubarr[j]->putdata();
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Ch11Ex1_PublicationPolymorphism {

    static Scanner sc = new Scanner(System.in);

    static abstract class Publication {
        protected String title;
        protected float price;

        public void getdata() {
            System.out.print("  Title: "); title = sc.nextLine();
            System.out.print("  Price: $"); price = Float.parseFloat(sc.nextLine().trim());
        }

        public void putdata() {
            System.out.printf("  Title: %s  |  Price: $%.2f%n", title, price);
        }
    }

    static class Book extends Publication {
        private int pageCount;

        @Override
        public void getdata() {
            super.getdata();
            System.out.print("  Pages: "); pageCount = Integer.parseInt(sc.nextLine().trim());
        }

        @Override
        public void putdata() {
            System.out.print("[BOOK] "); super.putdata();
            System.out.println("         Pages: " + pageCount);
        }
    }

    static class Tape extends Publication {
        private float playingTime;

        @Override
        public void getdata() {
            super.getdata();
            System.out.print("  Playing time (min): "); playingTime = Float.parseFloat(sc.nextLine().trim());
        }

        @Override
        public void putdata() {
            System.out.print("[TAPE] "); super.putdata();
            System.out.printf("         Playing time: %.1f min%n", playingTime);
        }
    }

    public static void main(String[] args) {
        // Array of pointers to Publication (polymorphism)
        ArrayList<Publication> pubarr = new ArrayList<>();

        System.out.println("=== Publication Array with Polymorphism ===");
        System.out.println("Enter publications (b=book, t=tape, q=quit):");

        while (true) {
            System.out.print("\nType (b/t/q): ");
            String type = sc.nextLine().trim().toLowerCase();
            if (type.equals("q")) break;

            Publication pub;
            if (type.equals("b")) {
                pub = new Book();
                pub.getdata();
                pubarr.add(pub);
            } else if (type.equals("t")) {
                pub = new Tape();
                pub.getdata();
                pubarr.add(pub);
            } else {
                System.out.println("Invalid type.");
            }
        }

        System.out.println("\n=== All Publications ===");
        for (int j = 0; j < pubarr.size(); j++) {
            pubarr.get(j).putdata(); // simulates pubarr[j]->putdata()
        }

        sc.close();
    }
}
