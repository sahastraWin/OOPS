package scenarioBased.part1;/*
/*
 * Question 39 - Chapter 10: Pointers
 * Create a class called MyString that uses a char array to store a string.
 * Include a copy constructor and proper value semantics.
 * (In Java, memory management is automatic. This demonstrates proper
 * string copying/cloning patterns as the Java equivalent.)
 */

import java.util.Arrays;

public class Q39_StringCopy {
	static class MyString {
		private char[] data;

		MyString(String s) {
			data = s.toCharArray();
		}

		// Copy constructor equivalent
		MyString(MyString other) {
			data = Arrays.copyOf(other.data, other.data.length);
		}

		MyString concat(MyString other) {
			String combined = new String(data) + new String(other.data);
			return new MyString(combined);
		}

		@Override
		public String toString() {
			return new String(data);
		}
	}

	public static void main(String[] args) {
		MyString s1 = new MyString("Hello, ");
		MyString s2 = new MyString("World!");
		MyString copy = new MyString(s1); // copy constructor
		MyString concat = s1.concat(s2);

		System.out.println("s1: " + s1);
		System.out.println("s2: " + s2);
		System.out.println("Copy of s1: " + copy);
		System.out.println("Concat: " + concat);
	}
}
