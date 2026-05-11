package scenarioBased.part1;/*
/*
 * Question 52 - Chapter 14: Templates and Exceptions
 * Write a program that models a bank account with exception handling.
 * Create custom exception classes InsufficientFundsException
 * and InvalidAmountException.
 * The withdraw() function throws these when appropriate.
 */

public class Q52_BankExceptions {
    static class InsufficientFundsException extends Exception {
        InsufficientFundsException(double amount, double balance) {
            super(String.format("Cannot withdraw %.2f. Balance is only %.2f.", amount, balance));
        }
    }

    static class InvalidAmountException extends Exception {
        InvalidAmountException(double amount) {
            super(String.format("Invalid amount: %.2f. Must be positive.", amount));
        }
    }

    static class BankAccount {
        private double balance;
        private String owner;

        BankAccount(String owner, double initial) { this.owner = owner; this.balance = initial; }

        void deposit(double amount) throws InvalidAmountException {
            if (amount <= 0) throw new InvalidAmountException(amount);
            balance += amount;
            System.out.printf("Deposited: %.2f | Balance: %.2f%n", amount, balance);
        }

        void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
            if (amount <= 0) throw new InvalidAmountException(amount);
            if (amount > balance) throw new InsufficientFundsException(amount, balance);
            balance -= amount;
            System.out.printf("Withdrawn: %.2f | Balance: %.2f%n", amount, balance);
        }

        double getBalance() { return balance; }
    }

    public static void main(String[] args) {
        BankAccount acc = new BankAccount("Alice", 1000.00);
        System.out.println("Account: Alice, Balance: " + acc.getBalance());

        try { acc.deposit(500); } catch (InvalidAmountException e) { System.out.println("Error: " + e.getMessage()); }
        try { acc.withdraw(200); } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
        try { acc.withdraw(2000); } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
        try { acc.deposit(-100); } catch (InvalidAmountException e) { System.out.println("Error: " + e.getMessage()); }
    }
}
