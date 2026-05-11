package scenarioBased.part3;/*
 * Chapter 12, Exercise 8:
 * Starting with the LINKLIST program in Chapter 10, "Pointers," create a program that gives
 * the user four options, which can be selected by pressing a key:
 *   1. Add a link to the list in memory (the user supplies the data, which is one integer)
 *   2. Display the data from all the links in memory
 *   3. Write the data for all the links to a disk file (creating or truncating the file)
 *   4. Read all the data back from the file, and construct a new linked list to store it
 *
 * The file should store only the data (integers); no need to store pointer contents.
 */

import java.io.*;
import java.util.Scanner;

public class Ch12Ex8_LinkedListFile {

    static class Link {
        int data;
        Link next;
        public Link(int d) { data = d; next = null; }
    }

    static class LinkList {
        private Link first;

        public LinkList() { first = null; }

        public void addItem(int d) {
            Link newLink = new Link(d);
            newLink.next = first;
            first = newLink;
        }

        public void displayList() {
            if (first == null) { System.out.println("List is empty."); return; }
            System.out.print("List: ");
            Link cur = first;
            while (cur != null) { System.out.print(cur.data + (cur.next != null ? " -> " : "")); cur = cur.next; }
            System.out.println();
        }

        public void writeToFile(String filename) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
                Link cur = first;
                while (cur != null) { pw.println(cur.data); cur = cur.next; }
                System.out.println("List written to '" + filename + "'.");
            } catch (IOException e) {
                System.out.println("Error writing: " + e.getMessage());
            }
        }

        public void readFromFile(String filename) {
            first = null; // clear current list
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (!line.trim().isEmpty()) addItem(Integer.parseInt(line.trim()));
                }
                System.out.println("List loaded from '" + filename + "'.");
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + filename);
            } catch (IOException e) {
                System.out.println("Error reading: " + e.getMessage());
            }
        }
    }

    static final String FILENAME = "linklist.txt";

    public static void main(String[] args) {
        Scanner sc   = new Scanner(System.in);
        LinkList list = new LinkList();
        System.out.println("=== Linked List with File Persistence ===");

        while (true) {
            System.out.println("\n1. Add item   2. Display   3. Write to file   4. Read from file   5. Quit");
            System.out.print("Choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter integer: ");
                    try { list.addItem(Integer.parseInt(sc.nextLine().trim())); }
                    catch (NumberFormatException e) { System.out.println("Invalid integer."); }
                    break;
                case "2": list.displayList();           break;
                case "3": list.writeToFile(FILENAME);   break;
                case "4": list.readFromFile(FILENAME); list.displayList(); break;
                case "5": sc.close(); return;
                default:  System.out.println("Invalid choice.");
            }
        }
    }
}
