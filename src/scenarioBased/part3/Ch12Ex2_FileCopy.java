package scenarioBased.part3;/*
 * Chapter 12, Exercise 2 (Starred):
 * Write a program that emulates the DOS COPY command. That is, it should copy the
 * contents of a text file (such as any .cpp file) to another file. Invoke the program
 * with two command-line arguments — the source file and the destination file — like this:
 *   C> ocopy srcfile.cpp destfile.cpp
 * In the program, check that the user has typed the correct number of command-line
 * arguments, and that the files specified can be opened.
 */

import java.io.*;

public class Ch12Ex2_FileCopy {

    public static void main(String[] args) {
        // Check correct number of command-line arguments
        if (args.length != 2) {
            System.out.println("Usage: java Ch12Ex2_FileCopy <sourcefile> <destfile>");
            System.out.println("Example: java Ch12Ex2_FileCopy srcfile.txt destfile.txt");
            System.exit(1);
        }

        String srcPath  = args[0];
        String destPath = args[1];

        File srcFile = new File(srcPath);

        // Check source file exists and is readable
        if (!srcFile.exists()) {
            System.out.println("Error: Source file '" + srcPath + "' does not exist.");
            System.exit(1);
        }
        if (!srcFile.canRead()) {
            System.out.println("Error: Cannot read source file '" + srcPath + "'.");
            System.exit(1);
        }

        // Copy file contents
        long bytesCopied = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(srcPath));
             PrintWriter writer  = new PrintWriter(new FileWriter(destPath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.println(line);
                bytesCopied += line.length() + 1; // +1 for newline
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not open file: " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Error during copy: " + e.getMessage());
            System.exit(1);
        }

        System.out.printf("Copied '%s' to '%s' (%d bytes).%n", srcPath, destPath, bytesCopied);
    }
}
