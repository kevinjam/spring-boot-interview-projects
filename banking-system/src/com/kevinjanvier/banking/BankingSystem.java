package com.kevinjanvier.banking;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankingSystem {
    private Map<String, Account> accounts;

    public BankingSystem() {
        accounts = new HashMap<>();
    }

    public void createAccount(String accountHolder, String password) {
        if (accounts.containsKey(accountHolder)){
            System.out.println("Account already exists.");
        }else {
            Account account = new Account(accountHolder, password);
            accounts.put(accountHolder, account);
            System.out.println("Account created for " + accountHolder + ".");
        }
    }
    public Account authenticate(String accountHolder, String password) {
        var account = accounts.get(accountHolder);
        if (account != null && account.authenticate(password)) {
            return account;
        }else {
            System.out.println("Authentication failed.");
            return null;
        }
    }

    public void manageAccount(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome, " + account.getAccountHolder() + "!");
        while (true) {
            System.out.println("\n Choose an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Logout");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = Double.parseDouble(scanner.nextLine());
                    account.deposit(depositAmount);
                    break;
                case "2":
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = Double.parseDouble(scanner.nextLine());
                    account.withdraw(withdrawAmount);
                    break;
                case "3":
                    account.checkBalance();
                    break;
                case "4":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n === Banking System ===");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter account holder name: ");
                    String name = scanner.nextLine();
                    System.out.print("Set a password: ");
                    String pwd = scanner.nextLine();
                    createAccount(name, pwd);
                    break;
                case "2":
                    System.out.print("Enter account holder name: ");
                    String loginName = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPwd = scanner.nextLine();
                    Account account = authenticate(loginName, loginPwd);
                    if (account != null) {
                        manageAccount(account);
                    }
                    break;
                case "3":
                    System.out.println("Thank you for using the banking system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
