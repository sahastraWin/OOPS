package scenarioBased.part3;/*
 * Chapter 9, Exercise 2 (Starred):
 * Recall the STRCONV example from Chapter 8. The String class in this example has a flaw:
 * It does not protect itself if its objects are initialized to have too many characters.
 * (The SZ constant has the value 80.)
 * With String as a base class, derive a class Pstring (for "protected string") that prevents
 * buffer overflow when too long a string constant is used in a definition. A new constructor
 * in the derived class should copy only SZ-1 characters into str if the string constant is
 * longer, but copy the entire constant if it's shorter. Write a main() program to test
 * different lengths of strings.
 */

public class Ch9Ex2_Pstring {

    static class MyString {
        protected static final int SZ = 80;
        protected char[] str;

        public MyString() { str = new char[SZ]; }

        public MyString(String s) {
            str = new char[SZ];
            int len = Math.min(s.length(), SZ - 1);
            for (int i = 0; i < len; i++) str[i] = s.charAt(i);
            str[len] = '\0';
        }

        public void display() { System.out.println(new String(str).trim()); }
        public int length() {
            int i = 0;
            while (i < SZ && str[i] != '\0' && str[i] != 0) i++;
            return i;
        }
    }

    static class Pstring extends MyString {

        public Pstring() { super(); }

        // Protected string constructor - truncates if too long
        public Pstring(String s) {
            super();
            if (s.length() >= SZ) {
                System.out.printf("Warning: String truncated from %d to %d chars.%n", s.length(), SZ - 1);
                int copyLen = SZ - 1;
                for (int i = 0; i < copyLen; i++) str[i] = s.charAt(i);
                str[copyLen] = '\0';
            } else {
                for (int i = 0; i < s.length(); i++) str[i] = s.charAt(i);
                str[s.length()] = '\0';
            }
        }

        @Override
        public void display() {
            System.out.print("Pstring[" + length() + "]: ");
            super.display();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Protected String (Pstring) ===");

        Pstring ps1 = new Pstring("Hello, World!");
        ps1.display();

        Pstring ps2 = new Pstring("This is a normal length string that fits within 80 characters easily.");
        ps2.display();

        // String longer than SZ (80 chars)
        String longStr = "This string will surely exceed the width of the screen, which is what the SZ constant represents and beyond!";
        System.out.println("\nAttempting string of length " + longStr.length() + ":");
        Pstring ps3 = new Pstring(longStr);
        ps3.display();
    }
}
