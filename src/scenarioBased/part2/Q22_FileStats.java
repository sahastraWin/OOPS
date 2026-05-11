package scenarioBased.part2;/*
 * Question 22: Write a java program that displays the number of characters,
 * lines & words from a file.
 * Usage: java Q22_FileStats filename.txt
 */

import java.io.*;

public class Q22_FileStats {
    public static void main(String[] args) {
        String filename;

        // Use command-line argument or default to a test file
        if (args.length > 0) {
            filename = args[0];
        } else {
            // Create a sample file for demonstration
            filename = "sample.txt";
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
                bw.write("Hello World this is a sample file.\n");
                bw.write("It contains multiple lines and words.\n");
                bw.write("Java file handling is easy.\n");
                bw.close();
                System.out.println("Created sample file: " + filename);
            } catch (IOException ex) {
                System.out.println("Could not create sample file.");
                return;
            }
        }

        int charCount = 0;
        int lineCount = 0;
        int wordCount = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = br.readLine()) != null) {
                lineCount++;
                charCount += line.length(); // Characters per line (excluding newline)

                // Count words by splitting on whitespace
                String[] words = line.trim().split("\\s+");
                if (!line.trim().isEmpty()) {
                    wordCount += words.length;
                }
            }
            br.close();

            // Display results
            System.out.println("\n===== File Statistics: " + filename + " =====");
            System.out.println("Number of Lines     : " + lineCount);
            System.out.println("Number of Words     : " + wordCount);
            System.out.println("Number of Characters: " + charCount + " (excluding newlines)");

        } catch (FileNotFoundException e) {
            System.out.println("Error: File '" + filename + "' not found.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
