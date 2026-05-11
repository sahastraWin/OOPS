package scenarioBased.part1;/*
/*
 * Question 46 - Chapter 12: Streams and Files
 * Design a program that reads a text file, counts the number of words,
 * lines, and characters, and displays the statistics.
 * The filename is supplied by the user.
 */

import java.io.*;
import java.util.Scanner;

public class Q46_FileStats {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String filename = sc.next();

        File f = new File(filename);
        if (!f.exists()) { System.out.println("File not found: " + filename); return; }

        long lines = 0, words = 0, chars = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines++;
                chars += line.length() + 1; // +1 for newline
                String[] w = line.trim().split("\\s+");
                if (!line.trim().isEmpty()) words += w.length;
            }
        }

        System.out.println("=== File Statistics: " + filename + " ===");
        System.out.println("Lines:      " + lines);
        System.out.println("Words:      " + words);
        System.out.println("Characters: " + chars);
        sc.close();
    }
}
