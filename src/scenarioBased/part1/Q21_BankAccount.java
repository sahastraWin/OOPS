package scenarioBased.part1;/*
/*
 * Question 21 - Chapter 6: Objects and Classes
 * Model a simple bank account with a class called Account.
 * Include data for balance and member functions deposit(),
 * withdraw(), and display_balance().
 * Ensure the balance cannot go negative.
 */

import java.util.Scanner;

public class Q21_BankAccount {
    static class Account {
        private double balance;

        Account(double initialBalance) { this.balance = initialBalance; }

        void deposit(double amount) {
            if (amount <= 0) { System.out.println("Deposit amount must be positive."); return; }
            balance += amount;
            System.out.printf("Deposited: %.2f | New Balance: %.2f%n", amount, balance);
        }

        void withdraw(double amount) {
            if (amount <= 0) { System.out.println("Withdrawal amount must be positive."); return; }
            if (amount > balance) { System.out.println("Insufficient funds!"); return; }
            balance -= amount;
            System.out.printf("Withdrawn: %.2f | New Balance: %.2f%n", amount, balance);
        }

        void displayBalance() { System.out.printf("Current Balance: %.2f%n", balance); }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Account acc = new Account(1000.00);
        acc.displayBalance();
        acc.deposit(500);
        acc.withdraw(200);
        acc.withdraw(2000); // Should fail
        acc.displayBalance();
        sc.close();
    }
}
