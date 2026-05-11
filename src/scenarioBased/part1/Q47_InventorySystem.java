package scenarioBased.part1;/*
/*
 * Question 47 - Chapter 12: Streams and Files
 * Create a class called Inventory with product name, quantity, and price.
 * Write a program that maintains an inventory file, allowing the user to
 * add items, update quantities, and display the full inventory.
 */

import java.io.*;
import java.util.*;

public class Q47_InventorySystem {
    static final String FILE = "inventory.txt";

    static class Item {
        String name; int quantity; double price;
        Item(String n, int q, double p) { name = n; quantity = q; price = p; }
        @Override public String toString() { return name + "," + quantity + "," + price; }
    }

    static List<Item> loadAll() throws IOException {
        List<Item> list = new ArrayList<>();
        File f = new File(FILE);
        if (!f.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                list.add(new Item(p[0], Integer.parseInt(p[1]), Double.parseDouble(p[2])));
            }
        }
        return list;
    }

    static void saveAll(List<Item> items) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Item i : items) pw.println(i);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1=Add Item  2=Update Qty  3=Display All  4=Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            List<Item> items = loadAll();

            if (choice == 1) {
                System.out.print("Name: "); String n = sc.next();
                System.out.print("Qty: "); int q = sc.nextInt();
                System.out.print("Price: "); double p = sc.nextDouble();
                items.add(new Item(n, q, p));
                saveAll(items); System.out.println("Item added.");
            } else if (choice == 2) {
                System.out.print("Item name to update: "); String n = sc.next();
                System.out.print("New quantity: "); int q = sc.nextInt();
                boolean found = false;
                for (Item i : items) if (i.name.equalsIgnoreCase(n)) { i.quantity = q; found = true; }
                if (found) { saveAll(items); System.out.println("Updated."); }
                else System.out.println("Item not found.");
            } else if (choice == 3) {
                System.out.printf("%-20s %8s %10s%n", "Name", "Qty", "Price");
                for (Item i : items) System.out.printf("%-20s %8d %10.2f%n", i.name, i.quantity, i.price);
            } else { System.out.println("Goodbye!"); sc.close(); return; }
        }
    }
}
