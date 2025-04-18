import java.util.Scanner;

// Class to represent the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }
}

// Class to represent the ATM
class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;

        do {
            System.out.println("\n===== Welcome to the ATM =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option! Please choose again.");
            }

        } while (choice != 4);
    }

    private void checkBalance() {
        System.out.printf("Your current balance is: $%.2f%n", account.getBalance());
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: $");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            if (amount > 0) {
                if (account.deposit(amount)) {
                    System.out.println("Deposit successful!");
                } else {
                    System.out.println("Invalid deposit amount.");
                }
            } else {
                System.out.println("Please enter a positive amount.");
            }
        } else {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();  // Consume the invalid input
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: $");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            if (account.withdraw(amount)) {
                System.out.println("Withdrawal successful!");
            } else {
                System.out.println("Insufficient balance or invalid amount.");
            }
        } else {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();  // Consume the invalid input
        }
    }
}

// Main class
public class ATMApp {
    public static void main(String[] args) {
        // Create a bank account with an initial balance
        BankAccount userAccount = new BankAccount(10000.0);

        // Create ATM and start session
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}

