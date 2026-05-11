package scenarioBased.part3;/*
 * Chapter 14 (Templates & Exceptions), Exercise 12:
 * Make a class dofile that includes an exception class and member functions to read and
 * write files. A constructor to this class can take the filename as an argument and open
 * a file with that name. You may also want a member function to reset the file pointer to
 * the beginning of the file. Use the REWERR program in Chapter 12 as a model, and write a
 * main() program that provides the same functionality, but does so by calling on members
 * of the dofile class.
 */

import java.io.*;
import java.util.Scanner;

public class Ch14Ex12_DoFile {

    static class DoFile {

        // Inner exception class for file errors
        static class FileException extends RuntimeException {
            private final String filename;
            private final String operation;

            public FileException(String filename, String operation, String detail) {
                super(String.format("File error [%s] during '%s': %s", filename, operation, detail));
                this.filename  = filename;
                this.operation = operation;
            }

            public String getFilename()  { return filename; }
            public String getOperation() { return operation; }
        }

        private String         filename;
        private BufferedReader reader;
        private PrintWriter    writer;

        // Constructor: open file for writing (append mode optional)
        public DoFile(String fname, boolean append) {
            this.filename = fname;
            try {
                writer = new PrintWriter(new FileWriter(fname, append));
            } catch (IOException e) {
                throw new FileException(fname, "open-write", e.getMessage());
            }
        }

        // Write a line to the file
        public void writeLine(String line) {
            if (writer == null) throw new FileException(filename, "write", "file not open for writing");
            writer.println(line);
        }

        // Close writer
        public void closeWrite() {
            if (writer != null) { writer.close(); writer = null; }
        }

        // Open for reading (resets to beginning)
        public void openRead() {
            try {
                if (reader != null) reader.close();
                reader = new BufferedReader(new FileReader(filename));
            } catch (FileNotFoundException e) {
                throw new FileException(filename, "open-read", "file not found");
            } catch (IOException e) {
                throw new FileException(filename, "open-read", e.getMessage());
            }
        }

        // Read next line (returns null at EOF)
        public String readLine() {
            if (reader == null) throw new FileException(filename, "read", "file not open for reading");
            try {
                return reader.readLine();
            } catch (IOException e) {
                throw new FileException(filename, "read", e.getMessage());
            }
        }

        // Reset: close reader and reopen at beginning
        public void reset() {
            try {
                if (reader != null) reader.close();
                reader = new BufferedReader(new FileReader(filename));
            } catch (IOException e) {
                throw new FileException(filename, "reset", e.getMessage());
            }
        }

        public void closeRead() {
            try { if (reader != null) { reader.close(); reader = null; } }
            catch (IOException e) { /* ignore */ }
        }

        public String getFilename() { return filename; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== DoFile: File I/O with Exception Handling ===");

        System.out.print("Enter filename: ");
        String fname = sc.nextLine().trim();

        // Write phase
        try {
            DoFile df = new DoFile(fname, false);
            System.out.println("Opened '" + fname + "' for writing.");

            while (true) {
                System.out.print("Enter line (blank to stop): ");
                String line = sc.nextLine();
                if (line.trim().isEmpty()) break;
                df.writeLine(line);
            }
            df.closeWrite();
            System.out.println("Write complete.");

            // Read back all lines
            df.openRead();
            System.out.println("\n--- File Contents ---");
            String line;
            while ((line = df.readLine()) != null) System.out.println(line);
            df.closeRead();

        } catch (DoFile.FileException e) {
            System.out.println("Caught: " + e.getMessage());
            System.out.println("Operation: " + e.getOperation() + " on '" + e.getFilename() + "'");
        }

        // Test: try to open a non-existent file
        try {
            DoFile bad = new DoFile("nosuchfile.txt", false);
            bad.openRead(); // This would normally succeed after creation
            bad.readLine();
        } catch (DoFile.FileException e) {
            System.out.println("\nExpected error caught: " + e.getMessage());
        }

        sc.close();
    }
}
