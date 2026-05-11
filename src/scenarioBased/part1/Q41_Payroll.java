package scenarioBased.part1;/*
 * Question 41 - Chapter 11: Virtual Functions and Other Subtleties
 * Model a company payroll. Create a base class Employee with a
 * pure virtual (abstract) function earnings().
 * Derive HourlyEmployee (wage × hours worked) and SalariedEmployee
 * (fixed monthly salary). Use an array of Employee references and display earnings.
 */

public class Q41_Payroll {
    abstract static class Employee {
        protected String name;
        Employee(String name) { this.name = name; }
        abstract double earnings();
        void display() {
            System.out.printf("%-15s Earnings: %.2f%n", name + ":", earnings());
        }
    }

    static class HourlyEmployee extends Employee {
        private double wage, hoursWorked;
        HourlyEmployee(String name, double wage, double hours) {
            super(name); this.wage = wage; this.hoursWorked = hours;
        }
        @Override double earnings() { return wage * hoursWorked; }
    }

    static class SalariedEmployee extends Employee {
        private double monthlySalary;
        SalariedEmployee(String name, double salary) {
            super(name); this.monthlySalary = salary;
        }
        @Override double earnings() { return monthlySalary; }
    }

    public static void main(String[] args) {
        Employee[] staff = {
            new HourlyEmployee("Alice", 25.0, 160),
            new SalariedEmployee("Bob", 5000),
            new HourlyEmployee("Charlie", 18.5, 140),
            new SalariedEmployee("Diana", 7500)
        };
        System.out.println("=== Monthly Payroll ===");
        double total = 0;
        for (Employee e : staff) { e.display(); total += e.earnings(); }
        System.out.printf("Total Payroll: %.2f%n", total);
    }
}
