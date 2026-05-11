package scenarioBased.part1;/*
/*
 * Question 61 - Classic Scenario: STUDENT GRADES
 * Create a class Student with name, roll number, and marks in 5 subjects.
 * Include functions to calculate total, percentage, and grade (A, B, C, D, F).
 * Write a main() that inputs data for N students and displays
 * their results sorted by percentage.
 */

import java.util.*;

public class Q61_StudentGradesClassic {
    static class Student {
        String name;
        int rollNo;
        double[] marks;

        Student(String name, int roll, double... marks) {
            this.name = name; this.rollNo = roll; this.marks = marks;
        }

        double total() { double s = 0; for (double m : marks) s += m; return s; }

        double percentage() { return total() / marks.length; }

        char grade() {
            double p = percentage();
            if (p >= 90) return 'A';
            if (p >= 75) return 'B';
            if (p >= 60) return 'C';
            if (p >= 40) return 'D';
            return 'F';
        }

        void display() {
            System.out.printf("Roll: %03d | %-12s | Total: %6.1f | %%: %5.1f | Grade: %c%n",
                              rollNo, name, total(), percentage(), grade());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Number of students: ");
        int n = sc.nextInt();
        Student[] students = new Student[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Name: "); String name = sc.next();
            System.out.print("Roll: "); int roll = sc.nextInt();
            double[] marks = new double[5];
            for (int j = 0; j < 5; j++) {
                System.out.print("Subject " + (j + 1) + " marks: ");
                marks[j] = sc.nextDouble();
            }
            students[i] = new Student(name, roll, marks);
        }

        Arrays.sort(students, (a, b) -> Double.compare(b.percentage(), a.percentage()));
        System.out.println("\n=== Results (sorted by percentage) ===");
        for (Student s : students) s.display();
        sc.close();
    }
}
