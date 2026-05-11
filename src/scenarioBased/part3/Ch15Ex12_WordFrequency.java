package scenarioBased.part3;/*
 * Chapter 15 (STL/Collections), Exercise 12:
 * A frequency table lists words and the number of times each word appears in a text file.
 * Write a program that creates a frequency table for a file whose name is entered by the user.
 * You can use a map of string-int pairs. You may want to use the ispunct() function (in header
 * file CTYPE.H) to check for punctuation so you can strip it off the end of a word. Also, the
 * tolower() function may prove handy for uncapitalizing words.
 * (In Java: HashMap<String, Integer> simulates C++ map<string, int>.)
 */

import java.io.*;
import java.util.*;

public class Ch15Ex12_WordFrequency {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Word Frequency Table (map<string,int>) ===");
        System.out.print("Enter filename: ");
        String filename = sc.nextLine().trim();

        // map<string, int> — sorted map for output in alphabetical order
        Map<String, Integer> freqMap = new TreeMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split on whitespace
                String[] tokens = line.split("\\s+");
                for (String token : tokens) {
                    if (token.isEmpty()) continue;

                    // Strip leading/trailing punctuation (simulates ispunct())
                    String word = token.toLowerCase(); // tolower()
                    word = word.replaceAll("^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", "");

                    if (!word.isEmpty()) {
                        freqMap.merge(word, 1, Integer::sum); // map[word]++
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File '" + filename + "' not found.");
            sc.close(); return;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            sc.close(); return;
        }

        if (freqMap.isEmpty()) {
            System.out.println("No words found in file.");
            sc.close(); return;
        }

        // Display frequency table
        System.out.println("\n--- Word Frequency Table ---");
        System.out.printf("%-25s %s%n", "Word", "Count");
        System.out.println("-".repeat(35));

        freqMap.forEach((word, count) ->
            System.out.printf("%-25s %d%n", word, count)
        );

        System.out.printf("%n%d unique words total.%n", freqMap.size());

        // Top 5 most frequent
        System.out.println("\n--- Top 5 Most Frequent ---");
        freqMap.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(5)
            .forEach(e -> System.out.printf("%-25s %d%n", e.getKey(), e.getValue()));

        sc.close();
    }
}
