package scenarioBased.part2;/*
 * Question 28: Write a class Student with attributes roll no, name, age and course.
 * Initialize values through parameterized constructor.
 * If age of student is not in between 15 and 21 then generate user-defined
 * exception "Age Not Within The Range".
 * If name contains numbers or special symbols raise exception "Name not valid".
 */

import java.util.Scanner;

// Custom Exception 1: Age validation
class AgeNotWithinRangeException extends Exception {
    AgeNotWithinRangeException(int age) {
        super("Age Not Within The Range! Age " + age + " must be between 15 and 21.");
    }
}

// Custom Exception 2: Name validation
class NameNotValidException extends Exception {
    NameNotValidException(String name) {
        super("Name not valid! Name '" + name + "' contains numbers or special symbols.");
    }
}

class StudentRecord {
    int rollNo;
    String name;
    int age;
    String course;

    // Parameterized constructor with validation
    StudentRecord(int rollNo, String name, int age, String course)
            throws AgeNotWithinRangeException, NameNotValidException {

        // Validate name: must only contain letters and spaces
        if (!name.matches("[a-zA-Z ]+")) {
            throw new NameNotValidException(name);
        }

        // Validate age: must be between 15 and 21
        if (age < 15 || age > 21) {
            throw new AgeNotWithinRangeException(age);
        }

        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    void display() {
        System.out.println("Roll No : " + rollNo);
        System.out.println("Name    : " + name);
        System.out.println("Age     : " + age);
        System.out.println("Course  : " + course);
    }
}

public class Q28_StudentValidation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int n = sc.nextInt(); sc.nextLine();

        StudentRecord[] students = new StudentRecord[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Student " + (i + 1) + " ---");
            System.out.print("Roll No: "); int roll = sc.nextInt(); sc.nextLine();
            System.out.print("Name: "); String name = sc.nextLine();
            System.out.print("Age: "); int age = sc.nextInt(); sc.nextLine();
            System.out.print("Course: "); String course = sc.nextLine();

            try {
                students[count] = new StudentRecord(roll, name, age, course);
                System.out.println("Student added successfully.");
                count++;
            } catch (AgeNotWithinRangeException | NameNotValidException e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }

        System.out.println("\n====== Valid Student Records ======");
        if (count == 0) {
            System.out.println("No valid students to display.");
        } else {
            for (int i = 0; i < count; i++) {
                students[i].display();
                System.out.println("----------------------------");
            }
        }
    }
}
