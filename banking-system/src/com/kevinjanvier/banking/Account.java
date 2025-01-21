package com.kevinjanvier.banking;

public class Account {
    private String accountHolder;
    private String password;
    private double balance;

    public Account(String accountHolder, String password) {
        this.accountHolder = accountHolder;
        this.password = password;
        this.balance = 0.0;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited " + amount + ". New balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else {
            balance -= amount;
            System.out.println("Withdrawn " + amount + ". New balance: " + balance);
        }
    }

    public void checkBalance() {
        System.out.println("Account holder: " + accountHolder + ", Balance: " + balance);
    }
}

