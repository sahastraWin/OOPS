package scenarioBased.part3;/*
 * Chapter 12, Exercise 12:
 * To the EMPL_IO program in this chapter add the ability to search through all the employee
 * objects in a disk file, looking for one with a specified employee number. If it finds a
 * match, it should display the data for the employee. The user can invoke this find() function
 * by typing the 'f' character. The function should then prompt for the employee number.
 * The search and display operation should not interfere with the data in memory.
 */

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Ch12Ex12_EmployeeSearch {

    static final String FILENAME = "employees.txt";

    static class Employee {
        private String name;
        private long   empNumber;
        private double salary;

        public Employee() {}
        public Employee(String n, long e, double s) { name = n; empNumber = e; salary = s; }

        public void getData(Scanner sc) {
            System.out.print("Name: ");            name      = sc.nextLine().trim();
            System.out.print("Employee number: "); empNumber = Long.parseLong(sc.nextLine().trim());
            System.out.print("Salary: $");         salary    = Double.parseDouble(sc.nextLine().trim());
        }

        public void putData() {
            System.out.printf("  Name: %-20s  Emp#: %-8d  Salary: $%.2f%n", name, empNumber, salary);
        }

        public String toFileString() {
            return name + "|" + empNumber + "|" + salary;
        }

        public static Employee fromFileString(String line) {
            String[] p = line.split("\\|");
            return new Employee(p[0], Long.parseLong(p[1]), Double.parseDouble(p[2]));
        }

        public long getEmpNumber() { return empNumber; }
    }

    static ArrayList<Employee> employees = new ArrayList<>();

    static void saveAll() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILENAME))) {
            for (Employee e : employees) pw.println(e.toFileString());
            System.out.println("Saved " + employees.size() + " employee(s) to file.");
        } catch (IOException e) { System.out.println("Error saving: " + e.getMessage()); }
    }

    // find() - search file without disturbing in-memory data
    static void findEmployee(long empNum) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                Employee e = Employee.fromFileString(line);
                if (e.getEmpNumber() == empNum) {
                    System.out.println("Found:");
                    e.putData();
                    found = true;
                }
            }
            if (!found) System.out.println("Employee #" + empNum + " not found in file.");
        } catch (FileNotFoundException e) {
            System.out.println("No employee file found. Save first.");
        } catch (IOException e) { System.out.println("Error: " + e.getMessage()); }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Employee File System ===");

        while (true) {
            System.out.println("\na=Add  d=Display  s=Save  f=Find  q=Quit");
            System.out.print("Choice: ");
            char choice = sc.nextLine().trim().toLowerCase().charAt(0);

            switch (choice) {
                case 'a':
                    Employee emp = new Employee();
                    emp.getData(sc);
                    employees.add(emp);
                    System.out.println("Employee added.");
                    break;
                case 'd':
                    if (employees.isEmpty()) { System.out.println("No employees in memory."); break; }
                    System.out.println("--- Employees in Memory ---");
                    for (Employee e : employees) e.putData();
                    break;
                case 's':
                    saveAll();
                    break;
                case 'f':
                    System.out.print("Enter employee number to find: ");
                    try {
                        long num = Long.parseLong(sc.nextLine().trim());
                        findEmployee(num);
                    } catch (NumberFormatException e) { System.out.println("Invalid number."); }
                    break;
                case 'q':
                    sc.close(); return;
                default:
                    System.out.println("Unknown command.");
            }
        }
    }
}
