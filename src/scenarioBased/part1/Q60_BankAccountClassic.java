package scenarioBased.part1;/*
/*
 * Question 60 - Classic Scenario: BANK ACCOUNT
 * Simulate a bank with a class BankAccount.
 * Data members: account number, customer name, and balance.
 * Member functions: deposit(), withdraw() (check for insufficient funds),
 * and display().
 * Demonstrate with multiple accounts.
 */

public class Q60_BankAccountClassic {
    static class BankAccount {
        private String accNo, customerName;
        private double balance;

        BankAccount(String accNo, String name, double balance) {
            this.accNo = accNo; this.customerName = name; this.balance = balance;
        }

        void deposit(double amount) {
            if (amount <= 0) { System.out.println("Invalid deposit amount."); return; }
            balance += amount;
        }

        boolean withdraw(double amount) {
            if (amount <= 0) { System.out.println("Invalid withdrawal amount."); return false; }
            if (amount > balance) { System.out.println("Insufficient funds in account " + accNo); return false; }
            balance -= amount;
            return true;
        }

        void display() {
            System.out.printf("Acc: %-10s Name: %-15s Balance: %.2f%n", accNo, customerName, balance);
        }
    }

    public static void main(String[] args) {
        BankAccount[] accounts = {
            new BankAccount("ACC001", "Alice",   5000),
            new BankAccount("ACC002", "Bob",     2500),
            new BankAccount("ACC003", "Charlie", 8000)
        };

        System.out.println("=== Initial Balances ===");
        for (BankAccount a : accounts) a.display();

        accounts[0].deposit(1000);
        accounts[1].withdraw(3000); // should fail
        accounts[2].withdraw(2000);

        System.out.println("\n=== Updated Balances ===");
        for (BankAccount a : accounts) a.display();
    }
}
