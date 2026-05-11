package scenarioBased.part2;/*
 * Question 3: Write a java program which creates class Student
 * (Rollno, Name, Number of subjects, Marks of each subject)
 * (Number of subjects varies for each student)
 * Write a parameterized constructor which initializes roll no, name &
 * Number of subjects and create the array of marks dynamically.
 * Display the details of all students with percentage and class obtained.
 */

import java.util.Scanner;

class Student {
    int rollNo;
    String name;
    int numSubjects;
    int[] marks; // Dynamic array for marks

    // Parameterized constructor
    Student(int rollNo, String name, int numSubjects) {
        this.rollNo = rollNo;
        this.name = name;
        this.numSubjects = numSubjects;
        this.marks = new int[numSubjects]; // Dynamic array creation
    }

    // Calculate percentage
    double getPercentage() {
        int total = 0;
        for (int m : marks) total += m;
        return (double) total / (numSubjects * 100) * 100;
    }

    // Determine class based on percentage
    String getClassObtained() {
        double perc = getPercentage();
        if (perc >= 75) return "Distinction";
        else if (perc >= 60) return "First Class";
        else if (perc >= 50) return "Second Class";
        else if (perc >= 40) return "Pass Class";
        else return "Fail";
    }

    void display() {
        System.out.println("\nRoll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.print("Marks: ");
        for (int i = 0; i < numSubjects; i++)
            System.out.print("Sub" + (i + 1) + "=" + marks[i] + " ");
        System.out.printf("%nPercentage: %.2f%%%n", getPercentage());
        System.out.println("Class: " + getClassObtained());
    }
}

public class Q3_Student {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        Student[] students = new Student[n];

        // Accept details for each student
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Student " + (i + 1) + " ---");
            System.out.print("Roll No: ");
            int roll = sc.nextInt();
            sc.nextLine();
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Number of Subjects: ");
            int ns = sc.nextInt();

            students[i] = new Student(roll, name, ns);

            // Accept marks for each subject
            for (int j = 0; j < ns; j++) {
                System.out.print("Marks for Subject " + (j + 1) + " (out of 100): ");
                students[i].marks[j] = sc.nextInt();
            }
        }

        // Display all students
        System.out.println("\n========= Student Report =========");
        for (Student s : students) {
            s.display();
        }
    }
}
