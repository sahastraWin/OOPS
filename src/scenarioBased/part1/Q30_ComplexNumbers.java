package scenarioBased.part1;/*
/*
 * Question 30 - Chapter 8: Operator Overloading
 * Create a class called Complex to represent complex numbers.
 * Implement add(), subtract(), and multiply() methods for complex arithmetic,
 * and toString() to display them in a+bi format.
 */

public class Q30_ComplexNumbers {
    static class Complex {
        private double real, imag;

        Complex(double real, double imag) { this.real = real; this.imag = imag; }

        Complex add(Complex other)      { return new Complex(real + other.real, imag + other.imag); }
        Complex subtract(Complex other) { return new Complex(real - other.real, imag - other.imag); }
        Complex multiply(Complex other) {
            return new Complex(real * other.real - imag * other.imag,
                               real * other.imag + imag * other.real);
        }

        @Override
        public String toString() {
            return String.format("%.2f%s%.2fi", real, imag >= 0 ? "+" : "", imag);
        }
    }

    public static void main(String[] args) {
        Complex c1 = new Complex(3, 4);
        Complex c2 = new Complex(1, -2);
        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
        System.out.println("c1 + c2 = " + c1.add(c2));
        System.out.println("c1 - c2 = " + c1.subtract(c2));
        System.out.println("c1 * c2 = " + c1.multiply(c2));
    }
}
