package scenarioBased.part2;/*
 * Question 4: In a bank, different customers having saving account.
 * Some customers may have taken a loan from the bank.
 * Design a Base class Customer (name, phone-number).
 * Derive a class Depositor (accno, balance) from Customer.
 * Again derive a class Borrower (loan-no, loan-amt) from Depositor.
 * Write necessary member functions to read and display the details of 'n' customers.
 */

import java.util.Scanner;

// Base class
class Customer {
    String name;
    String phoneNumber;

    void readCustomer(Scanner sc) {
        System.out.print("Enter Name: ");
        name = sc.nextLine();
        System.out.print("Enter Phone Number: ");
        phoneNumber = sc.nextLine();
    }

    void displayCustomer() {
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phoneNumber);
    }
}

// Derived class from Customer
class Depositor extends Customer {
    int accNo;
    double balance;

    void readDepositor(Scanner sc) {
        readCustomer(sc);
        System.out.print("Enter Account Number: ");
        accNo = sc.nextInt();
        System.out.print("Enter Balance: ");
        balance = sc.nextDouble();
        sc.nextLine(); // consume newline
    }

    void displayDepositor() {
        displayCustomer();
        System.out.println("Account No: " + accNo);
        System.out.printf("Balance: Rs. %.2f%n", balance);
    }
}

// Derived class from Depositor (Multilevel Inheritance)
class Borrower extends Depositor {
    int loanNo;
    double loanAmt;

    void readBorrower(Scanner sc) {
        readDepositor(sc);
        System.out.print("Enter Loan Number: ");
        loanNo = sc.nextInt();
        System.out.print("Enter Loan Amount: ");
        loanAmt = sc.nextDouble();
        sc.nextLine();
    }

    void displayBorrower() {
        displayDepositor();
        System.out.println("Loan No: " + loanNo);
        System.out.printf("Loan Amount: Rs. %.2f%n", loanAmt);
    }
}

public class Q4_Bank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of borrowers: ");
        int n = sc.nextInt();
        sc.nextLine();

        Borrower[] borrowers = new Borrower[n];

        // Read details
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Borrower " + (i + 1) + " ---");
            borrowers[i] = new Borrower();
            borrowers[i].readBorrower(sc);
        }

        // Display details
        System.out.println("\n========= Bank Records =========");
        for (int i = 0; i < n; i++) {
            System.out.println("\nRecord " + (i + 1) + ":");
            borrowers[i].displayBorrower();
            System.out.println("--------------------------------");
        }
    }
}
