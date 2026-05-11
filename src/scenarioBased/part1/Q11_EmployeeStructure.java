package scenarioBased.part1;/*
/*
 * Question 11 - Chapter 4: Structures
 * Create a structure called Employee with fields for name, ID number,
 * department, and salary.
 * Write a program that reads info for three employees and prints it out.
 */

import java.util.Scanner;

public class Q11_EmployeeStructure {
    static class Employee {
        String name, department;
        int id;
        double salary;

        Employee(String name, int id, String dept, double salary) {
            this.name = name; this.id = id; this.department = dept; this.salary = salary;
        }

        void display() {
            System.out.printf("Name: %-15s ID: %04d  Dept: %-15s Salary: %.2f%n",
                              name, id, department, salary);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Employee[] employees = new Employee[3];
        for (int i = 0; i < 3; i++) {
            System.out.println("--- Employee " + (i + 1) + " ---");
            System.out.print("Name: "); String name = sc.next();
            System.out.print("ID: "); int id = sc.nextInt();
            System.out.print("Department: "); String dept = sc.next();
            System.out.print("Salary: "); double sal = sc.nextDouble();
            employees[i] = new Employee(name, id, dept, sal);
        }
        System.out.println("\n--- Employee Records ---");
        for (Employee e : employees) e.display();
        sc.close();
    }
}
