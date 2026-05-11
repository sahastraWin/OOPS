package scenarioBased.part1;/*
/*
 * Question 55 - Classic Scenario: TOLLBOOTH
 * Imagine a TollBooth at a bridge. Cars pay Rs 50 toll; some pass without paying.
 * The tollbooth tracks total cars and total cash collected.
 * Create a class TollBooth with payingCar() and noPayCar() functions.
 * A main() lets the user press keys:
 *   '1' for paying cars, '2' for non-paying, 'q' to print totals and exit.
 */

import java.util.Scanner;

public class Q55_TollBooth {
    static class TollBooth {
        private static final double TOLL = 50.0;
        private int totalCars = 0;
        private double totalCash = 0;

        void payingCar()  { totalCars++; totalCash += TOLL; System.out.println("Car paid Rs " + TOLL); }
        void noPayCar()   { totalCars++; System.out.println("Car passed without paying."); }
        void printTotals() {
            System.out.println("=== Tollbooth Report ===");
            System.out.println("Total cars:  " + totalCars);
            System.out.printf("Total cash:  Rs %.2f%n", totalCash);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TollBooth booth = new TollBooth();
        System.out.println("Tollbooth: '1' = paying, '2' = non-paying, 'q' = quit");
        while (sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            if (input.equals("1"))       booth.payingCar();
            else if (input.equals("2"))  booth.noPayCar();
            else if (input.equalsIgnoreCase("q")) { booth.printTotals(); break; }
            else System.out.println("Invalid input. Use '1', '2', or 'q'.");
        }
        sc.close();
    }
}
