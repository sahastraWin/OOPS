package scenarioBased.part1;/*
spackage scenarioBased.part1;/*
/*
 * Question 48 - Chapter 13: Multifile Programs
 * Implement a simple library management system.
 * A Book class holds title, author, and ISBN.
 * A Library class manages a collection of books:
 * add, remove, and search by title or author.
 * (In Java, all in one file with inner classes as the equivalent of multifile.)
 */

import java.util.*;
import java.util.stream.Collectors;

public class Q48_LibrarySystem {
    static class Book {
        private String title, author, isbn;

        Book(String title, String author, String isbn) {
            this.title = title; this.author = author; this.isbn = isbn;
        }

        String getTitle()  { return title; }
        String getAuthor() { return author; }
        String getIsbn()   { return isbn; }

        @Override public String toString() {
            return String.format("\"%-30s\" by %-20s [ISBN: %s]", title, author, isbn);
        }
    }

    static class Library {
        private List<Book> books = new ArrayList<>();

        void addBook(Book book) { books.add(book); System.out.println("Added: " + book.getTitle()); }

        boolean removeBook(String isbn) {
            return books.removeIf(b -> b.getIsbn().equals(isbn));
        }

        List<Book> searchByTitle(String keyword) {
            String kw = keyword.toLowerCase();
            return books.stream()
                        .filter(b -> b.getTitle().toLowerCase().contains(kw))
                        .collect(Collectors.toList());
        }

        List<Book> searchByAuthor(String author) {
            String a = author.toLowerCase();
            return books.stream()
                        .filter(b -> b.getAuthor().toLowerCase().contains(a))
                        .collect(Collectors.toList());
        }

        void displayAll() {
            if (books.isEmpty()) { System.out.println("Library is empty."); return; }
            books.forEach(b -> System.out.println("  " + b));
        }
    }

    public static void main(String[] args) {
        Library lib = new Library();
        lib.addBook(new Book("Clean Code", "Robert C. Martin", "978-0132350884"));
        lib.addBook(new Book("Effective Java", "Joshua Bloch", "978-0134685991"));
        lib.addBook(new Book("Design Patterns", "Gang of Four", "978-0201633610"));
        lib.addBook(new Book("Java: The Complete Reference", "Herbert Schildt", "978-1260440232"));

        System.out.println("\n--- All Books ---");
        lib.displayAll();

        System.out.println("\n--- Search by author 'Robert' ---");
        lib.searchByAuthor("Robert").forEach(b -> System.out.println("  " + b));

        System.out.println("\n--- Search by title 'Java' ---");
        lib.searchByTitle("Java").forEach(b -> System.out.println("  " + b));

        lib.removeBook("978-0201633610");
        System.out.println("\n--- After removal ---");
        lib.displayAll();
    }
}
