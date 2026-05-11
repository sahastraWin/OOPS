package scenarioBased.part3;/*
 * Chapter 6, Exercise 2
 * Imagine a tollbooth at a bridge. Cars passing by are expected to pay a 50 cent toll.
 * Mostly they do, but sometimes a car goes by without paying.
 * Model this tollbooth with a class called tollBooth. The two data items are:
 *   - unsigned int: total number of cars
 *   - double: total amount of money collected
 * A constructor initializes both to 0.
 * payingCar()  – increments car total and adds $0.50 to cash total
 * nopayCar()   – increments car total, adds nothing to cash total
 * display()    – displays the two totals
 * Include a program to test this class. The user presses one key to count a paying car,
 * another for a nonpaying car. Pressing Esc exits and prints totals.
 */

import java.util.Scanner;

public class Ch6_Ex2_TollBooth {

    static class TollBooth {
        private long totalCars;
        private double totalCash;

        TollBooth() { totalCars = 0; totalCash = 0.0; }

        void payingCar()  { totalCars++; totalCash += 0.50; }
        void nopayCar()   { totalCars++; }
        void display()    { System.out.printf("Total cars: %d, Total cash: $%.2f%n", totalCars, totalCash); }
    }

    public static void main(String[] args) {
        TollBooth booth = new TollBooth();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Press 'p' for paying car, 'n' for non-paying, 'q' to quit:");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim().toLowerCase();
            if (line.equals("q") || line.equals("quit")) break;
            else if (line.equals("p")) { booth.payingCar(); System.out.println("Paying car recorded."); }
            else if (line.equals("n")) { booth.nopayCar(); System.out.println("Non-paying car recorded."); }
            else System.out.println("Unknown input. Use 'p', 'n', or 'q'.");
        }

        booth.display();
        scanner.close();
    }
}
