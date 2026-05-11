package scenarioBased.part3;/*
 * Chapter 7, Exercise 2
 * Create a class called employee that contains a name (an object of class String) and an
 * employee number (type long). Include a member function called getdata() to get data from
 * the user for insertion into the object, and another function called putdata() to display the data.
 * Assume the name has no embedded blanks.
 * Write a main() program to exercise this class. It should create an array of type employee,
 * and then invite the user to input data for up to 100 employees. Finally, it should print
 * out the data for all the employees.
 */

import java.util.Scanner;

public class Ch7_Ex2_EmployeeArray {

    static class Employee {
        private String name;
        private long number;

        void getdata(Scanner scanner) {
            System.out.print("Enter name (no spaces): ");
            name = scanner.next();
            System.out.print("Enter employee number: ");
            number = scanner.nextLong();
        }

        void putdata() {
            System.out.printf("Name: %-20s  Number: %d%n", name, number);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Employee[] employees = new Employee[100];
        int count = 0;

        System.out.println("Enter employee data (enter 'done' as name to stop):");
        while (count < 100) {
            System.out.printf("Employee #%d%n", count + 1);
            System.out.print("Enter name (or 'done' to stop): ");
            String name = scanner.next();
            if (name.equalsIgnoreCase("done")) break;

            employees[count] = new Employee();
            employees[count].name = name;
            System.out.print("Enter employee number: ");
            employees[count].number = scanner.nextLong();
            count++;
        }

        System.out.println("\nAll Employees:");
        for (int i = 0; i < count; i++) employees[i].putdata();
        scanner.close();
    }
}
