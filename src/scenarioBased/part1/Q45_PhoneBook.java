package scenarioBased.part1;/*
/*
 * Question 45 - Chapter 12: Streams and Files
 * Write a program that acts as a simple phone book.
 * Store entries (name, phone number) in a file.
 * The user can add entries, look up a name, and display all entries.
 */

import java.io.*;
import java.util.*;

public class Q45_PhoneBook {
    static final String FILE = "phonebook.txt";

    static void addEntry(String name, String phone) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE, true))) {
            pw.println(name + "," + phone);
        }
        System.out.println("Added: " + name + " -> " + phone);
    }

    static void lookup(String name) throws IOException {
        boolean found = false;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(name)) {
                    System.out.println("Found: " + parts[0] + " -> " + parts[1]);
                    found = true;
                }
            }
        }
        if (!found) System.out.println("No entry found for: " + name);
    }

    static void displayAll() throws IOException {
        File f = new File(FILE);
        if (!f.exists()) { System.out.println("Phone book is empty."); return; }
        System.out.println("=== Phone Book ===");
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                System.out.printf("%-20s %s%n", p[0], p[1]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1=Add  2=Lookup  3=Display All  4=Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: System.out.print("Name: "); String n = sc.next();
                        System.out.print("Phone: "); String p = sc.next();
                        addEntry(n, p); break;
                case 2: System.out.print("Name to lookup: "); lookup(sc.next()); break;
                case 3: displayAll(); break;
                case 4: System.out.println("Goodbye!"); sc.close(); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }
}
