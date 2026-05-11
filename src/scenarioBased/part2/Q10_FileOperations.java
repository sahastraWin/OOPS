package scenarioBased.part2;/*
 * Question 10: Write a Java program to create 2 files F1 and F2.
 * Copy the contents of file F1 by changing the case into file F2. F2 = F1
 * Also copy the contents of F1 and F2 in F3. F3 = F1 + F2
 * Display the contents of F3.
 * Use Command Line Arguments.
 * Usage: java Q10_FileOperations file1.txt file2.txt file3.txt
 */

import java.io.*;

public class Q10_FileOperations {
    public static void main(String[] args) {
        // Check command line arguments
        if (args.length < 3) {
            System.out.println("Usage: java Q10_FileOperations <file1> <file2> <file3>");
            return;
        }

        String f1 = args[0]; // Source file
        String f2 = args[1]; // Case-converted copy
        String f3 = args[2]; // F1 + F2 combined

        try {
            // Step 1: Create F1 with some content (if it doesn't exist)
            if (!new File(f1).exists()) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(f1));
                bw.write("Hello World from File F1.\n");
                bw.write("This is a Test of File Operations in Java.\n");
                bw.write("Java Programming is Fun and Interesting.\n");
                bw.close();
                System.out.println("File " + f1 + " created with sample content.");
            }

            // Step 2: Read F1, change case, write to F2
            BufferedReader br = new BufferedReader(new FileReader(f1));
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(f2));
            String line;
            while ((line = br.readLine()) != null) {
                // Toggle case: uppercase -> lowercase, lowercase -> uppercase
                StringBuilder toggled = new StringBuilder();
                for (char c : line.toCharArray()) {
                    if (Character.isUpperCase(c))
                        toggled.append(Character.toLowerCase(c));
                    else if (Character.isLowerCase(c))
                        toggled.append(Character.toUpperCase(c));
                    else
                        toggled.append(c);
                }
                bw2.write(toggled.toString());
                bw2.newLine();
            }
            br.close();
            bw2.close();
            System.out.println("File " + f2 + " created with case-toggled content.");

            // Step 3: Combine F1 + F2 into F3
            BufferedWriter bw3 = new BufferedWriter(new FileWriter(f3));

            // Copy F1 to F3
            br = new BufferedReader(new FileReader(f1));
            bw3.write("--- Contents of " + f1 + " ---\n");
            while ((line = br.readLine()) != null) {
                bw3.write(line);
                bw3.newLine();
            }
            br.close();

            // Copy F2 to F3
            br = new BufferedReader(new FileReader(f2));
            bw3.write("--- Contents of " + f2 + " ---\n");
            while ((line = br.readLine()) != null) {
                bw3.write(line);
                bw3.newLine();
            }
            br.close();
            bw3.close();
            System.out.println("File " + f3 + " created with combined content.");

            // Step 4: Display F3 contents
            System.out.println("\n===== Contents of " + f3 + " =====");
            br = new BufferedReader(new FileReader(f3));
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
