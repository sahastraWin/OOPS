package scenarioBased.part1;/*
/*
 * Question 15 - Chapter 5: Functions
 * Write a function that takes a number and returns true if it is prime,
 * false otherwise.
 * Use this function to display all prime numbers up to 1,000.
 */

public class Q15_PrimeNumbers {
    static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; (long) i * i <= n; i += 2)
            if (n % i == 0) return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Prime numbers up to 1000:");
        int count = 0;
        for (int i = 2; i <= 1000; i++) {
            if (isPrime(i)) {
                System.out.printf("%4d", i);
                if (++count % 10 == 0) System.out.println();
            }
        }
        System.out.println("\nTotal primes: " + count);
    }
}
