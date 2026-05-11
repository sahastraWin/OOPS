package scenarioBased.part1;/*
/*
 * Question 25 - Chapter 7: Arrays and Strings
 * Write a class called Sales that stores weekly sales figures in a 52-element array.
 * Include member functions to set the data, calculate the minimum, maximum,
 * and average weekly sales, and display all data.
 */

import java.util.Random;

public class Q25_SalesData {
    static class Sales {
        private double[] weekly = new double[52];

        void setData() {
            // Populate with random data for demonstration
            Random rand = new Random();
            for (int i = 0; i < 52; i++)
                weekly[i] = 1000 + rand.nextDouble() * 9000;
        }

        double min() {
            double m = weekly[0];
            for (double v : weekly) if (v < m) m = v;
            return m;
        }

        double max() {
            double m = weekly[0];
            for (double v : weekly) if (v > m) m = v;
            return m;
        }

        double average() {
            double sum = 0;
            for (double v : weekly) sum += v;
            return sum / weekly.length;
        }

        void display() {
            for (int i = 0; i < 52; i++)
                System.out.printf("Week %2d: %.2f%n", i + 1, weekly[i]);
        }
    }

    public static void main(String[] args) {
        Sales s = new Sales();
        s.setData();
        s.display();
        System.out.printf("%nMin: %.2f | Max: %.2f | Avg: %.2f%n", s.min(), s.max(), s.average());
    }
}
