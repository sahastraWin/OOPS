package scenarioBased.part3;/*
 * Chapter 12, Exercise 6:
 * Make a class called name from the data in Exercise 4 (first name, middle initial, last name,
 * employee number). Create member functions for this class that read and write an object's
 * data to a disk file, using ofstream, and read it back using ifstream. Use formatted data
 * with the << and >> operators. The read and write member functions should be self-contained:
 * they should include statements to open the appropriate stream and read or write a record.
 *
 * The write function simply appends data to the end of the file. The read function will need
 * to select which record it's going to read using a record number parameter. It reads records
 * sequentially until it finds the one at the requested position.
 *
 * In main(), call these member functions to allow the user to enter data for a number of
 * objects that are written to a file as they are entered. The program then displays all this
 * data by reading it from the file.
 */

import java.io.*;
import java.util.Scanner;

public class Ch12Ex6_NameClass {

    static final String FILENAME = "nameclass.txt";

    static class Name {
        private String firstName;
        private char   midInitial;
        private String lastName;
        private long   empNumber;

        public Name() {}
        public Name(String fn, char mi, String ln, long en) {
            firstName = fn; midInitial = mi; lastName = ln; empNumber = en;
        }

        // Write (append) this record to file
        public void writeName() {
            try (PrintWriter pw = new PrintWriter(new FileWriter(FILENAME, true))) {
                pw.printf("%s %c %s %d%n", firstName, midInitial, lastName, empNumber);
            } catch (IOException e) {
                System.out.println("Error writing: " + e.getMessage());
            }
        }

        // Read a specific record by record number (0-based)
        public boolean readName(int recNum) {
            try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
                String line;
                int count = 0;
                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty()) continue;
                    if (count == recNum) {
                        String[] parts = line.trim().split("\\s+");
                        firstName  = parts[0];
                        midInitial = parts[1].charAt(0);
                        lastName   = parts[2];
                        empNumber  = Long.parseLong(parts[3]);
                        return true;
                    }
                    count++;
                }
            } catch (IOException e) {
                System.out.println("Error reading: " + e.getMessage());
            }
            return false;
        }

        public void getData(Scanner sc) {
            System.out.print("First name: ");     firstName  = sc.nextLine().trim();
            System.out.print("Middle initial: "); midInitial = sc.nextLine().trim().charAt(0);
            System.out.print("Last name: ");      lastName   = sc.nextLine().trim();
            System.out.print("Employee #: ");     empNumber  = Long.parseLong(sc.nextLine().trim());
        }

        public void putData() {
            System.out.printf("%-12s %c. %-12s | Emp#: %d%n", firstName, midInitial, lastName, empNumber);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Name Class with File I/O ===");
        int written = 0;

        // Write phase
        while (true) {
            Name n = new Name();
            n.getData(sc);
            n.writeName();
            written++;
            System.out.print("Enter another? (y/n): ");
            if (!sc.nextLine().trim().equalsIgnoreCase("y")) break;
        }

        // Read all records back and display
        System.out.println("\n--- All Records from File ---");
        System.out.printf("%-12s %-4s %-12s %-12s%n", "First", "MI", "Last", "Emp#");
        System.out.println("-".repeat(45));

        Name temp = new Name();
        for (int i = 0; i < written; i++) {
            if (temp.readName(i)) temp.putData();
        }

        sc.close();
    }
}
