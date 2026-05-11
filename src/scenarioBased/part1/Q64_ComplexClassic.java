package scenarioBased.part1;/*
/*
 * Question 64 - Classic Scenario: COMPLEX NUMBERS
 * Design a class Complex for complex numbers (real and imaginary parts).
 * Implement add(), subtract(), and multiply() methods.
 * Override toString() to display in a+bi format.
 * Write a main() to demonstrate arithmetic on complex numbers.
 */

public class Q64_ComplexClassic {
    static class Complex {
        private double real, imag;

        Complex(double real, double imag) { this.real = real; this.imag = imag; }

        Complex add(Complex o)      { return new Complex(real + o.real, imag + o.imag); }
        Complex subtract(Complex o) { return new Complex(real - o.real, imag - o.imag); }
        Complex multiply(Complex o) {
            return new Complex(real * o.real - imag * o.imag,
                               real * o.imag + imag * o.real);
        }
        double modulus() { return Math.sqrt(real * real + imag * imag); }

        @Override
        public String toString() {
            return String.format("%.2f%s%.2fi", real, imag >= 0 ? "+" : "", imag);
        }
    }

    public static void main(String[] args) {
        Complex c1 = new Complex(3, 4);
        Complex c2 = new Complex(1, -2);
        Complex c3 = new Complex(-2, 3);

        System.out.println("c1 = " + c1 + "  |modulus| = " + String.format("%.4f", c1.modulus()));
        System.out.println("c2 = " + c2);
        System.out.println("c3 = " + c3);
        System.out.println("c1 + c2 = " + c1.add(c2));
        System.out.println("c1 - c2 = " + c1.subtract(c2));
        System.out.println("c1 * c2 = " + c1.multiply(c2));
        System.out.println("c1 + c2 + c3 = " + c1.add(c2).add(c3));
    }
}
