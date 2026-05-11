package scenarioBased.part2;/*
 * Question 13: Write a Java program to accept 'n' numbers through the command
 * line and store all the prime numbers and perfect numbers into different arrays
 * and display both the arrays.
 * Usage: java Q13_PrimePerfect 2 3 4 6 7 12 28 10 13
 */

import java.util.ArrayList;

public class Q13_PrimePerfect {

    // Method to check if a number is prime
    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // Method to check if a number is perfect
    // A perfect number equals the sum of its proper divisors
    static boolean isPerfect(int n) {
        if (n < 2) return false;
        int sum = 1;
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) sum += i;
        }
        return sum == n;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Q13_PrimePerfect <num1> <num2> ...");
            System.out.println("Example: java Q13_PrimePerfect 2 3 4 6 7 12 28");
            return;
        }

        // Use ArrayLists to collect prime and perfect numbers
        ArrayList<Integer> primes = new ArrayList<>();
        ArrayList<Integer> perfects = new ArrayList<>();

        System.out.print("Input numbers: ");
        for (String arg : args) {
            try {
                int num = Integer.parseInt(arg);
                System.out.print(num + " ");
                if (isPrime(num))   primes.add(num);
                if (isPerfect(num)) perfects.add(num);
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid number: " + arg);
            }
        }

        // Convert to arrays
        int[] primeArray = primes.stream().mapToInt(Integer::intValue).toArray();
        int[] perfectArray = perfects.stream().mapToInt(Integer::intValue).toArray();

        // Display prime numbers
        System.out.println("\n\n--- Prime Numbers ---");
        if (primeArray.length == 0) {
            System.out.println("No prime numbers found.");
        } else {
            for (int p : primeArray) System.out.print(p + " ");
            System.out.println();
        }

        // Display perfect numbers
        System.out.println("--- Perfect Numbers ---");
        if (perfectArray.length == 0) {
            System.out.println("No perfect numbers found.");
        } else {
            for (int p : perfectArray) System.out.print(p + " ");
            System.out.println();
        }
    }
}
