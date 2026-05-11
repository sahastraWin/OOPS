package scenarioBased.part3;/*
 * Chapter 8, Exercise 2 (Starred):
 * Write a program that substitutes an overloaded += operator for the overloaded + operator
 * in the STRPLUS program in this chapter. This operator should allow statements like:
 *   s1 += s2;
 * where s2 is added (concatenated) to s1 and the result is left in s1.
 * The operator should also permit the results of the operation to be used in other
 * calculations, as in:
 *   s3 = s1 += s2;
 */

public class Ch8Ex2_StringConcat {

    static class MyString {
        private char[] str;
        private static final int MAX_SIZE = 80;

        public MyString() { str = new char[0]; }

        public MyString(String s) {
            str = s.toCharArray();
        }

        // Overloaded += (concatenation assignment)
        public MyString appendAssign(MyString s2) {
            char[] newStr = new char[str.length + s2.str.length];
            System.arraycopy(str, 0, newStr, 0, str.length);
            System.arraycopy(s2.str, 0, newStr, str.length, s2.str.length);
            str = newStr;
            return this;
        }

        public void display() {
            System.out.println(new String(str));
        }

        public String getValue() { return new String(str); }
    }

    public static void main(String[] args) {
        MyString s1 = new MyString("Hello, ");
        MyString s2 = new MyString("World!");
        MyString s3 = new MyString();

        System.out.println("Before: s1 = " + s1.getValue());
        System.out.println("Before: s2 = " + s2.getValue());

        // s1 += s2
        s1.appendAssign(s2);
        System.out.println("After s1 += s2: s1 = " + s1.getValue());

        // s3 = s1 += s2 (chain)
        MyString s4 = new MyString("Java ");
        MyString s5 = new MyString("Programming");
        MyString s6 = new MyString();

        // s6 = (s4 += s5)
        s6 = s4.appendAssign(s5); // appendAssign returns 'this'
        System.out.println("s6 = s4 += s5: " + s6.getValue());
        System.out.println("s4 after: " + s4.getValue());
    }
}
