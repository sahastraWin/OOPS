package scenarioBased.part1;/*
/*
 * Question 62 - Classic Scenario: INVENTORY SYSTEM
 * Model a store inventory with a class Item containing:
 * item code, name, price, and quantity.
 * Write a menu-driven program to:
 *   1. Add items
 *   2. Update stock
 *   3. Search by item code
 *   4. Display full inventory
 */

import java.util.*;

public class Q62_InventoryClassic {
    static class Item {
        int code; String name; double price; int quantity;

        Item(int code, String name, double price, int qty) {
            this.code = code; this.name = name; this.price = price; this.quantity = qty;
        }

        void display() {
            System.out.printf("Code: %04d | %-20s | Price: %8.2f | Qty: %4d%n",
                              code, name, price, quantity);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Item> inventory = new ArrayList<>();

        while (true) {
            System.out.println("\n1=Add  2=Update Stock  3=Search  4=Display All  5=Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Code: "); int code = sc.nextInt();
                System.out.print("Name: "); String name = sc.next();
                System.out.print("Price: "); double price = sc.nextDouble();
                System.out.print("Quantity: "); int qty = sc.nextInt();
                inventory.add(new Item(code, name, price, qty));
                System.out.println("Item added.");
            } else if (choice == 2) {
                System.out.print("Enter item code to update: "); int code = sc.nextInt();
                boolean found = false;
                for (Item item : inventory) {
                    if (item.code == code) {
                        System.out.print("New quantity: "); item.quantity = sc.nextInt();
                        System.out.println("Updated."); found = true; break;
                    }
                }
                if (!found) System.out.println("Item not found.");
            } else if (choice == 3) {
                System.out.print("Enter item code: "); int code = sc.nextInt();
                inventory.stream().filter(i -> i.code == code)
                         .findFirst().ifPresentOrElse(Item::display,
                         () -> System.out.println("Item not found."));
            } else if (choice == 4) {
                if (inventory.isEmpty()) System.out.println("Inventory is empty.");
                else inventory.forEach(Item::display);
            } else { System.out.println("Goodbye!"); break; }
        }
        sc.close();
    }
}
