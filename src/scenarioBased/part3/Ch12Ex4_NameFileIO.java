package scenarioBased.part3;/*
 * Chapter 12, Exercise 4:
 * In a loop, prompt the user to enter name data consisting of a first name, middle initial,
 * last name, and employee number (type unsigned long). Then, using formatted I/O with the
 * insertion (<<) operator, write these four data items to an ofstream object. Don't forget
 * that strings must be terminated with a space or other whitespace character. When the user
 * indicates that no more name data will be entered, close the ofstream object, open an
 * ifstream object, read and display all the data in the file, and terminate the program.
 */

import java.io.*;
import java.util.Scanner;

public class Ch12Ex4_NameFileIO {

    static final String FILENAME = "names.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Name Data File I/O ===");

        // Write phase (ofstream equivalent)
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILENAME))) {
            while (true) {
                System.out.print("First name: ");    String firstName = sc.nextLine().trim();
                System.out.print("Middle initial: "); char midInitial  = sc.nextLine().trim().charAt(0);
                System.out.print("Last name: ");      String lastName  = sc.nextLine().trim();
                System.out.print("Employee number: "); long empNum     = Long.parseLong(sc.nextLine().trim());

                // Write with spaces between fields (formatted I/O)
                pw.printf("%s %c %s %d%n", firstName, midInitial, lastName, empNum);

                System.out.print("Enter another? (y/n): ");
                if (!sc.nextLine().trim().equalsIgnoreCase("y")) break;
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
            return;
        }

        // Read phase (ifstream equivalent)
        System.out.println("\n--- Data Read from File ---");
        System.out.printf("%-15s %-5s %-15s %-12s%n", "First", "MI", "Last", "Emp#");
        System.out.println("-".repeat(50));

        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.trim().split("\\s+");
                if (parts.length >= 4) {
                    System.out.printf("%-15s %-5s %-15s %-12s%n",
                        parts[0], parts[1], parts[2], parts[3]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        sc.close();
    }
}
