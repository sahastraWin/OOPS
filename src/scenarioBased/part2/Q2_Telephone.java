package scenarioBased.part2;/*
 * Question 2: Create a class Telephone containing name, telephone number & city
 * and write necessary member functions for the following:
 * - Search the telephone number with given name.
 * - Search the name with given telephone number.
 * - Search all customers in a given city.
 * (Use function overloading)
 */

import java.util.Scanner;

class Telephone {
    String name;
    String telephoneNumber;
    String city;

    Telephone(String name, String telephoneNumber, String city) {
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.city = city;
    }

    void display() {
        System.out.printf("Name: %-20s Phone: %-15s City: %s%n", name, telephoneNumber, city);
    }
}

public class Q2_Telephone {
    static Telephone[] directory = new Telephone[50];
    static int count = 0;

    // Overloaded search: search by name -> returns phone number
    static void search(String name) {
        System.out.println("\n--- Search by Name: " + name + " ---");
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (directory[i].name.equalsIgnoreCase(name)) {
                System.out.println("Phone Number: " + directory[i].telephoneNumber + " | City: " + directory[i].city);
                found = true;
            }
        }
        if (!found) System.out.println("No record found for name: " + name);
    }

    // Overloaded search: search by phone number -> returns name
    static void search(long phoneNumber) {
        System.out.println("\n--- Search by Phone Number: " + phoneNumber + " ---");
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (directory[i].telephoneNumber.equals(String.valueOf(phoneNumber))) {
                System.out.println("Name: " + directory[i].name + " | City: " + directory[i].city);
                found = true;
            }
        }
        if (!found) System.out.println("No record found for phone: " + phoneNumber);
    }

    // Overloaded search: search by city -> returns all customers in that city
    static void search(String city, boolean searchByCity) {
        System.out.println("\n--- Customers in City: " + city + " ---");
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (directory[i].city.equalsIgnoreCase(city)) {
                directory[i].display();
                found = true;
            }
        }
        if (!found) System.out.println("No customers found in city: " + city);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pre-load some data
        directory[count++] = new Telephone("Alice", "9876543210", "Pune");
        directory[count++] = new Telephone("Bob", "9123456789", "Mumbai");
        directory[count++] = new Telephone("Charlie", "9000011111", "Pune");
        directory[count++] = new Telephone("Diana", "8888888888", "Delhi");

        int choice;
        do {
            System.out.println("\n===== Telephone Directory Menu =====");
            System.out.println("1. Add new entry");
            System.out.println("2. Search by Name");
            System.out.println("3. Search by Telephone Number");
            System.out.println("4. Search by City");
            System.out.println("5. Display All");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: "); String n = sc.nextLine();
                    System.out.print("Phone: "); String ph = sc.nextLine();
                    System.out.print("City: "); String ci = sc.nextLine();
                    directory[count++] = new Telephone(n, ph, ci);
                    System.out.println("Entry added.");
                    break;
                case 2:
                    System.out.print("Enter name to search: ");
                    search(sc.nextLine());
                    break;
                case 3:
                    System.out.print("Enter phone number to search: ");
                    search(sc.nextLong());
                    break;
                case 4:
                    System.out.print("Enter city to search: ");
                    search(sc.nextLine(), true);
                    break;
                case 5:
                    System.out.println("\n--- All Records ---");
                    for (int i = 0; i < count; i++) directory[i].display();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }
}
