package scenarioBased.part2;/*
 * Question 29: Write a Java program to accept list of files as command line
 * arguments. Display the name and size of all the files.
 * Delete all files with extension as '.html' from the current directory.
 * Appropriate error messages should be printed.
 * (Use Vector Array)
 * Usage: java Q29_FileManager file1.txt file2.html index.html notes.java
 */

import java.io.*;
import java.util.Vector;

public class Q29_FileManager {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Q29_FileManager <file1> <file2> ...");
            System.out.println("Creating sample files for demonstration...\n");

            // Create sample files for demo
            String[] sampleFiles = {"doc1.txt", "page.html", "index.html", "Main.java"};
            for (String f : sampleFiles) {
                try {
                    FileWriter fw = new FileWriter(f);
                    fw.write("Sample content for " + f);
                    fw.close();
                } catch (IOException e) {
                    System.out.println("Could not create " + f);
                }
            }
            args = sampleFiles;
        }

        // Store file info in a Vector
        Vector<File> fileVector = new Vector<>();

        // Add all files from command line args to vector
        for (String arg : args) {
            fileVector.add(new File(arg));
        }

        // Display name and size of all files
        System.out.println("===== File Details =====");
        System.out.printf("%-25s %-15s %-10s%n", "File Name", "Size (bytes)", "Exists");
        System.out.println("-".repeat(55));

        for (File f : fileVector) {
            if (f.exists()) {
                System.out.printf("%-25s %-15d %-10s%n", f.getName(), f.length(), "Yes");
            } else {
                System.out.printf("%-25s %-15s %-10s%n", f.getName(), "N/A", "No - File not found");
            }
        }

        // Delete all .html files
        System.out.println("\n===== Deleting .html Files =====");
        Vector<String> deletedFiles = new Vector<>();
        Vector<String> failedFiles = new Vector<>();

        for (File f : fileVector) {
            if (f.getName().endsWith(".html")) {
                if (f.exists()) {
                    if (f.delete()) {
                        deletedFiles.add(f.getName());
                        System.out.println("Deleted: " + f.getName());
                    } else {
                        failedFiles.add(f.getName());
                        System.out.println("Error: Could not delete " + f.getName());
                    }
                } else {
                    System.out.println("Error: File not found - " + f.getName());
                }
            }
        }

        // Summary
        System.out.println("\n===== Summary =====");
        System.out.println("Successfully deleted: " + deletedFiles.size() + " .html file(s)");
        if (!deletedFiles.isEmpty()) {
            System.out.println("  Files deleted: " + deletedFiles);
        }
        if (!failedFiles.isEmpty()) {
            System.out.println("Failed to delete: " + failedFiles);
        }
    }
}
