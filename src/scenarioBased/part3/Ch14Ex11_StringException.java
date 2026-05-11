package scenarioBased.part3;/*
 * Chapter 14 (Templates & Exceptions), Exercise 11:
 * Start with the STRPLUS program in Chapter 8. Add an exception class, and throw an
 * exception in the one-argument constructor if the initialization string is too long.
 * Throw another in the overloaded + operator if the result will be too long when two
 * strings are concatenated. Report which of these errors has occurred.
 */

public class Ch14Ex11_StringException {

    // Custom exception classes
    static class StringTooLongException extends RuntimeException {
        private final String source;
        public StringTooLongException(String msg, String src) {
            super(msg); source = src;
        }
        public String getSource() { return source; }
    }

    static class ConcatTooLongException extends StringTooLongException {
        public ConcatTooLongException(int resultLen, int max) {
            super(String.format("Concatenation result too long: %d chars (max %d)", resultLen, max), "concat");
        }
    }

    static class InitTooLongException extends StringTooLongException {
        public InitTooLongException(int len, int max) {
            super(String.format("Initialization string too long: %d chars (max %d)", len, max), "init");
        }
    }

    static class MyString {
        private char[] str;
        private static final int MAX_SIZE = 20; // small size to easily trigger exception

        public MyString() { str = new char[0]; }

        // One-argument constructor — throws if too long
        public MyString(String s) {
            if (s.length() >= MAX_SIZE)
                throw new InitTooLongException(s.length(), MAX_SIZE - 1);
            str = s.toCharArray();
        }

        // Overloaded + (concatenation) — throws if result too long
        public MyString concat(MyString s2) {
            int newLen = this.str.length + s2.str.length;
            if (newLen >= MAX_SIZE)
                throw new ConcatTooLongException(newLen, MAX_SIZE - 1);
            char[] result = new char[newLen];
            System.arraycopy(this.str, 0, result, 0, this.str.length);
            System.arraycopy(s2.str, 0, result, this.str.length, s2.str.length);
            MyString ms = new MyString();
            ms.str = result;
            return ms;
        }

        public void display() { System.out.println(new String(str)); }
        public int length()   { return str.length; }
        public String getValue() { return new String(str); }
    }

    public static void main(String[] args) {
        System.out.println("=== String with Exception Handling ===");
        System.out.println("MAX_SIZE = 20 characters\n");

        // Test 1: valid construction
        try {
            MyString s1 = new MyString("Hello");
            MyString s2 = new MyString(" World");
            System.out.print("s1 + s2 = "); s1.concat(s2).display();
        } catch (StringTooLongException e) {
            System.out.println("Exception [" + e.getSource() + "]: " + e.getMessage());
        }

        // Test 2: too-long initialization
        try {
            MyString s3 = new MyString("This string is definitely too long for the buffer!");
        } catch (InitTooLongException e) {
            System.out.println("\nCaught init exception: " + e.getMessage());
        }

        // Test 3: concatenation too long
        try {
            MyString s4 = new MyString("Hello there!"); // 12 chars
            MyString s5 = new MyString("How are you?"); // 12 chars — concat=24 > 19
            s4.concat(s5).display();
        } catch (ConcatTooLongException e) {
            System.out.println("\nCaught concat exception: " + e.getMessage());
        }

        System.out.println("\nProgram continues normally after exceptions.");
    }
}
