import java.util.ArrayList;
import java.util.Scanner;

// Class to represent an ATM
public class ATMSimulator {
    // Initial values for account
    private double balance;
    private int pin;
    private ArrayList<String> transactionHistory;

    // Constructor
    public ATMSimulator(double initialBalance, int initialPin) {
        balance = initialBalance;
        pin = initialPin;
        transactionHistory = new ArrayList<>();
    }

    // Method to check PIN
    private boolean validatePin(int enteredPin) {
        return pin == enteredPin;
    }

    // Method to display balance
    public void checkBalance() {
        System.out.println("Your current balance is: ₹" + balance);
        transactionHistory.add("Balance Inquiry: ₹" + balance);
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println("Please collect your cash: ₹" + amount);
            transactionHistory.add("Withdrawal: ₹" + amount);
        }
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
        } else {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully.");
            transactionHistory.add("Deposit: ₹" + amount);
        }
    }

    // Method to change PIN
    public void changePin(int oldPin, int newPin) {
        if (validatePin(oldPin)) {
            pin = newPin;
            System.out.println("PIN changed successfully.");
            transactionHistory.add("PIN changed.");
        } else {
            System.out.println("Incorrect old PIN.");
        }
    }

    // Method to print transaction history
    public void showTransactionHistory() {
        System.out.println("Transaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String record : transactionHistory) {
                System.out.println("- " + record);
            }
        }
    }

    // Main method to drive the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create ATM simulator with ₹10,000 balance and PIN 1234
        ATMSimulator atm = new ATMSimulator(10000.0, 1234);

        System.out.print("Enter your 4-digit PIN: ");
        int enteredPin = scanner.nextInt();

        if (!atm.validatePin(enteredPin)) {
            System.out.println("Incorrect PIN. Access Denied.");
            return;
        }

        // ATM menu
        while (true) {
            System.out.println("\n--- ATM MENU ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Cash");
            System.out.println("3. Deposit Cash");
            System.out.println("4. Change PIN");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter old PIN: ");
                    int oldPin = scanner.nextInt();
                    System.out.print("Enter new PIN: ");
                    int newPin = scanner.nextInt();
                    atm.changePin(oldPin, newPin);
                    break;
                case 5:
                    atm.showTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}