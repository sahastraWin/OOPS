package scenarioBased.part1;/*
/*
 * Question 49 - Chapter 13: Multifile Programs
 * Split a Student grade-tracking program.
 * A Student class stores name, ID, and an array of grades.
 * It calculates average, highest, and lowest grades.
 * main() demonstrates creating multiple students.
 * (In Java, modeled as inner classes as equivalent of header/source split.)
 */

import java.util.Scanner;

public class Q49_StudentGrades {
    static class Student {
        private String name;
        private int id;
        private double[] grades;

        Student(String name, int id, double... grades) {
            this.name = name; this.id = id; this.grades = grades;
        }

        double average() {
            double sum = 0; for (double g : grades) sum += g; return sum / grades.length;
        }

        double highest() {
            double max = grades[0]; for (double g : grades) if (g > max) max = g; return max;
        }

        double lowest() {
            double min = grades[0]; for (double g : grades) if (g < min) min = g; return min;
        }

        void display() {
            System.out.printf("Name: %-15s ID: %04d | Avg: %.2f | High: %.2f | Low: %.2f%n",
                              name, id, average(), highest(), lowest());
        }
    }

    public static void main(String[] args) {
        Student[] students = {
            new Student("Alice",   101, 88, 92, 78, 95, 85),
            new Student("Bob",     102, 72, 68, 75, 80, 70),
            new Student("Charlie", 103, 95, 98, 92, 96, 99),
            new Student("Diana",   104, 60, 65, 70, 55, 62)
        };

        System.out.println("=== Student Grade Report ===");
        for (Student s : students) s.display();
    }
}
