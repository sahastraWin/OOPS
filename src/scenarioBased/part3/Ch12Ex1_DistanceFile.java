package scenarioBased.part3;/*
 * Chapter 12, Exercise 1 (Starred):
 * Start with the Distance class from the ENGLCON example in Chapter 6, "Objects and Classes."
 * Using a loop similar to that in the DISKFUN example in this chapter, get a number of
 * Distance values from the user, and write them to a disk file. Append them to existing
 * values in the file, if any. When the user signals that no more values will be input,
 * read the file and display all the values.
 */

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Ch12Ex1_DistanceFile {

    static class Distance {
        private int feet;
        private double inches;

        public Distance() { feet = 0; inches = 0.0; }
        public Distance(int f, double i) {
            feet = f; inches = i;
            if (inches >= 12.0) { feet += (int)(inches / 12); inches %= 12.0; }
        }

        public void getdist(Scanner sc) {
            System.out.print("Enter feet: ");   feet   = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Enter inches: "); inches = Double.parseDouble(sc.nextLine().trim());
            if (inches >= 12.0) { feet += (int)(inches / 12); inches %= 12.0; }
        }

        public void showdist() {
            System.out.printf("%d'-%5.2f\"%n", feet, inches);
        }

        // Serialize to string for file storage
        public String toFileString() { return feet + " " + inches; }

        // Deserialize from string
        public static Distance fromFileString(String s) {
            String[] parts = s.trim().split("\\s+");
            return new Distance(Integer.parseInt(parts[0]), Double.parseDouble(parts[1]));
        }
    }

    static final String FILENAME = "distances.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Distance File I/O ===");

        // Append distances to file
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILENAME, true))) {
            while (true) {
                Distance d = new Distance();
                d.getdist(sc);
                pw.println(d.toFileString());
                System.out.print("Enter another? (y/n): ");
                if (!sc.nextLine().trim().equalsIgnoreCase("y")) break;
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
            return;
        }

        // Read and display all distances
        System.out.println("\n--- All Distances in File ---");
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Distance d = Distance.fromFileString(line);
                    System.out.printf("Distance %d: ", ++count);
                    d.showdist();
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        sc.close();
    }
}
