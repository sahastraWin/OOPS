package scenarioBased.part2;/*
 * Question 9: Define an Employee class with suitable attributes having
 * getSalary() method, which returns salary withdrawn by a particular employee.
 * Write a class Manager which extends a class Employee, override the getSalary()
 * method, which will return salary of manager by adding traveling allowance,
 * house rent allowance etc.
 */

import java.util.Scanner;

// Base class Employee
class Employee {
    int empId;
    String empName;
    double basicSalary;

    Employee(int empId, String empName, double basicSalary) {
        this.empId = empId;
        this.empName = empName;
        this.basicSalary = basicSalary;
    }

    // Returns the salary for a regular employee
    double getSalary() {
        return basicSalary;
    }

    void displayInfo() {
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + empName);
        System.out.printf("Salary: Rs. %.2f%n", getSalary());
    }
}

// Manager extends Employee
class Manager extends Employee {
    double travelingAllowance;
    double houseRentAllowance;
    double dearessAllowance;

    Manager(int empId, String empName, double basicSalary,
            double ta, double hra, double da) {
        super(empId, empName, basicSalary);
        this.travelingAllowance = ta;
        this.houseRentAllowance = hra;
        this.dearessAllowance = da;
    }

    // Overriding getSalary() to include allowances
    @Override
    double getSalary() {
        return basicSalary + travelingAllowance + houseRentAllowance + dearessAllowance;
    }

    @Override
    void displayInfo() {
        System.out.println("Manager ID: " + empId);
        System.out.println("Name: " + empName);
        System.out.printf("Basic Salary: Rs. %.2f%n", basicSalary);
        System.out.printf("Traveling Allowance: Rs. %.2f%n", travelingAllowance);
        System.out.printf("House Rent Allowance: Rs. %.2f%n", houseRentAllowance);
        System.out.printf("Dearness Allowance: Rs. %.2f%n", dearessAllowance);
        System.out.printf("Total Salary: Rs. %.2f%n", getSalary());
    }
}

public class Q9_EmployeeManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Enter Employee Details ---");
        System.out.print("Employee ID: "); int eid = sc.nextInt(); sc.nextLine();
        System.out.print("Name: "); String ename = sc.nextLine();
        System.out.print("Basic Salary: "); double esal = sc.nextDouble();

        Employee emp = new Employee(eid, ename, esal);

        System.out.println("\n--- Enter Manager Details ---");
        System.out.print("Manager ID: "); int mid = sc.nextInt(); sc.nextLine();
        System.out.print("Name: "); String mname = sc.nextLine();
        System.out.print("Basic Salary: "); double msal = sc.nextDouble();
        System.out.print("Traveling Allowance: "); double ta = sc.nextDouble();
        System.out.print("House Rent Allowance: "); double hra = sc.nextDouble();
        System.out.print("Dearness Allowance: "); double da = sc.nextDouble();

        Manager mgr = new Manager(mid, mname, msal, ta, hra, da);

        System.out.println("\n======= Employee Info =======");
        emp.displayInfo();

        System.out.println("\n======= Manager Info =======");
        mgr.displayInfo();
    }
}
