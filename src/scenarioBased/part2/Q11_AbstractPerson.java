package scenarioBased.part2;/*

 * Question 11: Create an abstract class Person. Derive two classes Employee
 * and Worker from it. Use proper method to accept and display the details for
 * the same.
 * Fields of Employee: Emp_no, Emp_name, address.
 * Fields of Worker: name and working hours.
 */

import java.util.Scanner;

// Abstract base class
abstract class Person {
    String name;

    // Abstract methods to be implemented by subclasses
    abstract void accept(Scanner sc);
    abstract void display();
}

// Employee class derived from Person
class EmpPerson extends Person {
    int empNo;
    String address;

    @Override
    void accept(Scanner sc) {
        System.out.print("Employee Number: ");
        empNo = sc.nextInt();
        sc.nextLine();
        System.out.print("Employee Name: ");
        name = sc.nextLine();
        System.out.print("Address: ");
        address = sc.nextLine();
    }

    @Override
    void display() {
        System.out.println("Employee No: " + empNo);
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
    }
}

// Worker class derived from Person
class Worker extends Person {
    double workingHours;

    @Override
    void accept(Scanner sc) {
        System.out.print("Worker Name: ");
        name = sc.nextLine();
        System.out.print("Working Hours per Day: ");
        workingHours = sc.nextDouble();
        sc.nextLine();
    }

    @Override
    void display() {
        System.out.println("Worker Name: " + name);
        System.out.printf("Working Hours: %.1f hours/day%n", workingHours);
    }
}

public class Q11_AbstractPerson {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of employees: ");
        int ne = sc.nextInt(); sc.nextLine();
        EmpPerson[] employees = new EmpPerson[ne];
        for (int i = 0; i < ne; i++) {
            System.out.println("\n--- Employee " + (i + 1) + " ---");
            employees[i] = new EmpPerson();
            employees[i].accept(sc);
        }

        System.out.print("\nEnter number of workers: ");
        int nw = sc.nextInt(); sc.nextLine();
        Worker[] workers = new Worker[nw];
        for (int i = 0; i < nw; i++) {
            System.out.println("\n--- Worker " + (i + 1) + " ---");
            workers[i] = new Worker();
            workers[i].accept(sc);
        }

        // Display employees
        System.out.println("\n========= Employee Details =========");
        for (EmpPerson e : employees) {
            e.display();
            System.out.println("----------------------------");
        }

        // Display workers
        System.out.println("\n========= Worker Details =========");
        for (Worker w : workers) {
            w.display();
            System.out.println("----------------------------");
        }
    }
}
