package scenarioBased.part3;/*
 * Chapter 12, Exercise 3 (Starred):
 * Write a program that returns the size in bytes of a program entered on the command line:
 *   C> filesize program.ext
 * The program should display the size of the specified file in bytes.
 */

import java.io.*;

public class Ch12Ex3_FileSize {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Ch12Ex3_FileSize <filename>");
            System.out.println("Example: java Ch12Ex3_FileSize program.txt");
            System.exit(1);
        }

        String filename = args[0];
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("Error: File '" + filename + "' does not exist.");
            System.exit(1);
        }

        if (!file.isFile()) {
            System.out.println("Error: '" + filename + "' is not a regular file.");
            System.exit(1);
        }

        long sizeBytes = file.length();
        double sizeKB  = sizeBytes / 1024.0;
        double sizeMB  = sizeKB / 1024.0;

        System.out.println("File: " + file.getAbsolutePath());
        System.out.printf("Size: %d bytes (%.2f KB / %.4f MB)%n", sizeBytes, sizeKB, sizeMB);
    }
}
