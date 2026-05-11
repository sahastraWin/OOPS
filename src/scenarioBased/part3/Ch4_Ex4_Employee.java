package scenarioBased.part3;/*
 * Chapter 4, Exercise 4
 * Create a structure called employee that contains two members: an employee number
 * (type int) and the employee's compensation (in dollars; type float).
 * Ask the user to fill in this data for three employees, store it in three variables
 * of type struct employee, and then display the information for each employee.
 */

import java.util.Scanner;

public class Ch4_Ex4_Employee {

    static class Employee {
        int number;
        float compensation;

        Employee(int number, float compensation) {
            this.number = number;
            this.compensation = compensation;
        }

        @Override
        public String toString() {
            return String.format("Employee #%d, Compensation: $%.2f", number, compensation);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Employee[] employees = new Employee[3];

        for (int i = 0; i < 3; i++) {
            System.out.printf("Enter employee #%d number and compensation: ", i + 1);
            int num = scanner.nextInt();
            float comp = scanner.nextFloat();
            employees[i] = new Employee(num, comp);
        }

        System.out.println("\nEmployee Data:");
        for (Employee e : employees) System.out.println(e);
        scanner.close();
    }
}
