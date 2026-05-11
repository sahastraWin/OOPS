package scenarioBased.part3;/*
 * Chapter 12, Exercise 7:
 * Another approach to adding file stream I/O to an object is to make the file stream itself
 * a static member of the object. This makes it conceptually easier to think of the stream
 * as being related to the class as a whole rather than to individual objects.
 * It's also more efficient: open a stream once, then read and write objects to it as needed.
 * Each time the read function is called, it returns the data for the next object in the file
 * (file pointer progresses automatically since the file is never closed between reads).
 *
 * Rewrite the program in Exercises 4 and 6 to use an fstream object as a static data item
 * of the name class. Write a static function to open this stream, and another static function
 * to reset the file pointer to the beginning of the file (call when done writing, before reading).
 */

import java.io.*;
import java.util.Scanner;

public class Ch12Ex7_NameStaticStream {

    static final String FILENAME = "namestream.txt";

    static class Name {
        private String firstName;
        private char   midInitial;
        private String lastName;
        private long   empNumber;

        // Static stream objects (equivalent to C++ static fstream members)
        private static PrintWriter outStream  = null;
        private static BufferedReader inStream = null;

        // Static: open output stream
        public static void openWrite() {
            try {
                outStream = new PrintWriter(new FileWriter(FILENAME, true));
            } catch (IOException e) {
                System.out.println("Error opening file for write: " + e.getMessage());
            }
        }

        // Static: close output stream
        public static void closeWrite() {
            if (outStream != null) { outStream.close(); outStream = null; }
        }

        // Static: reset / open for reading (rewinds to beginning)
        public static void openRead() {
            try {
                if (inStream != null) inStream.close();
                inStream = new BufferedReader(new FileReader(FILENAME));
            } catch (IOException e) {
                System.out.println("Error opening file for read: " + e.getMessage());
            }
        }

        // Static: close input stream
        public static void closeRead() {
            try { if (inStream != null) { inStream.close(); inStream = null; } }
            catch (IOException e) { /* ignore */ }
        }

        // Write using static stream
        public void writeName() {
            if (outStream != null)
                outStream.printf("%s %c %s %d%n", firstName, midInitial, lastName, empNumber);
        }

        // Read next record using static stream
        public boolean readName() {
            try {
                String line;
                while ((line = inStream.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        String[] parts = line.trim().split("\\s+");
                        firstName  = parts[0];
                        midInitial = parts[1].charAt(0);
                        lastName   = parts[2];
                        empNumber  = Long.parseLong(parts[3]);
                        return true;
                    }
                }
            } catch (IOException e) { System.out.println("Read error: " + e.getMessage()); }
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
        System.out.println("=== Name Class with Static Stream ===");

        // Write phase
        Name.openWrite();
        while (true) {
            Name n = new Name();
            n.getData(sc);
            n.writeName();
            System.out.print("Enter another? (y/n): ");
            if (!sc.nextLine().trim().equalsIgnoreCase("y")) break;
        }
        Name.closeWrite();

        // Read all records back
        System.out.println("\n--- All Records ---");
        Name.openRead();
        Name temp = new Name();
        while (temp.readName()) temp.putData();
        Name.closeRead();

        sc.close();
    }
}
