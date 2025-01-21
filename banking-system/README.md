# Banking System (Command-Line Application)

This project is a simple command-line banking system written in Java, demonstrating object-oriented programming (OOP) principles. Users can create and manage multiple accounts, each with functionalities such as deposit, withdrawal, and balance inquiry.

## Features
- **Account Creation**: Users can create accounts with a unique username and password.
- **Multiple Accounts**: Support for managing multiple accounts simultaneously (e.g., Bob and Mary).
- **Authentication**: Users need to log in with their password to access their account.
- **Account Management**:
    - Deposit money.
    - Withdraw money.
    - Check account balance.
- **User-Friendly CLI**: Simple and interactive text-based interface.

## Project Structure
```
src
├── com.kevinjanvier.banking
│   ├── Account.java        // Account class: Handles account-related functionalities
│   ├── BankingSystem.java  // BankingSystem class: Manages the overall system logic
│   └── com.kevinjanvier.banking.Main.java           // com.kevinjanvier.banking.Main class: Entry point for the application
```

## How to Run

### Prerequisites
- **Java JDK 17** or higher installed on your machine.
- **IntelliJ IDEA** or any other Java IDE for running the program.

### Steps
1. Clone this repository or download the source code.
2. Open the project in IntelliJ IDEA.
3. Ensure the `src` folder is marked as a **source root**.
4. Navigate to `com.kevinjanvier.banking.Main.java` and run the `main` method.
5. Interact with the program via the command-line interface.

### Example Usage
#### 1. Create Accounts
```plaintext
=== Banking System ===
1. Create Account
2. Login
3. Exit
Enter your choice: 1
Enter account holder name: Bob
Set a password: bob123
Account created for Bob.
```
#### 2. Login and Manage Account
```plaintext
=== Banking System ===
1. Create Account
2. Login
3. Exit
Enter your choice: 2
Enter account holder name: Bob
Enter password: bob123
Welcome, Bob!
Choose an option:
1. Deposit
2. Withdraw
3. Check Balance
4. Logout
Enter your choice: 1
Enter amount to deposit: 1000
Deposited 1000.0. New balance: 1000.0
```
#### 3. Logout and Exit
```plaintext
Enter your choice: 4
Logging out...
=== Banking System ===
1. Create Account
2. Login
3. Exit
Enter your choice: 3
Thank you for using the banking system. Goodbye!
```

## Technologies Used
- **Java**: Programming language for implementation.
- **IntelliJ IDEA**: IDE for development and execution.

## OOP Principles Demonstrated
1. **Encapsulation**: The `Account` class encapsulates account-related data and methods, exposing only necessary behaviors.
2. **Abstraction**: The `BankingSystem` class abstracts the system-level logic for managing multiple accounts.
3. **Polymorphism**: Achieved through dynamic method invocation (e.g., calling `deposit` or `withdraw` methods on an account instance).
4. **Modularity**: The program is divided into distinct classes, each with a single responsibility.
