package scenarioBased.part1;/*
/*
 * Question 24 - Chapter 7: Arrays and Strings
 * Design a class called MyString that wraps a String.
 * Include member functions to get the string from the user, display it,
 * concatenate two MyStrings, and compare two MyStrings.
 */

import java.util.Scanner;

public class Q24_StringClass {
    static class MyString {
        private String data;

        MyString() { data = ""; }
        MyString(String s) { data = s; }

        void getFromUser() {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter string: ");
            data = sc.nextLine();
        }

        void display() { System.out.println("String: \"" + data + "\""); }

        MyString concat(MyString other) { return new MyString(data + other.data); }

        boolean equals(MyString other) { return data.equals(other.data); }

        int compareTo(MyString other) { return data.compareTo(other.data); }
    }

    public static void main(String[] args) {
        MyString s1 = new MyString();
        MyString s2 = new MyString();
        System.out.print("First ");  s1.getFromUser();
        System.out.print("Second "); s2.getFromUser();
        System.out.print("Concatenated: "); s1.concat(s2).display();
        System.out.println("Equal? " + s1.equals(s2));
        int cmp = s1.compareTo(s2);
        System.out.println("Compare: " + (cmp < 0 ? "s1 < s2" : cmp > 0 ? "s1 > s2" : "s1 == s2"));
    }
}
