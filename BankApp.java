package BankingSystem;

import java.io.*;
import java.util.*;

public class BankApp {

    private static final String FILE_NAME = "accounts.txt";
    private static Scanner scanner = new Scanner(System.in);
    private static List<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {
        loadAccounts();

        int choice;
        do {
            System.out.println("\n--- Simple Banking System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> checkBalance();
                case 5 -> saveAccounts();
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        System.out.println("Thank you for using the bank!");
    }

    private static void createAccount() {
        System.out.print("Enter account number: ");
        int accNo = scanner.nextInt();

        if (findAccount(accNo) != null) {
            System.out.println("Account already exists!");
            return;
        }

        scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();

        if (balance < 0) {
            System.out.println("Invalid balance.");
            return;
        }

        accounts.add(new BankAccount(accNo, name, balance));
        saveAccounts();
        System.out.println("Account created successfully!");
    }

    private static void deposit() {
        BankAccount acc = findAccountPrompt();
        if (acc == null) return;

        System.out.print("Enter amount: ");
        double amt = scanner.nextDouble();

        if (amt <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        acc.deposit(amt);
        saveAccounts();
        System.out.println("Amount deposited.");
    }

    private static void withdraw() {
        BankAccount acc = findAccountPrompt();
        if (acc == null) return;

        System.out.print("Enter amount: ");
        double amt = scanner.nextDouble();

        if (!acc.withdraw(amt)) {
            System.out.println("Insufficient balance or invalid amount.");
        } else {
            saveAccounts();
            System.out.println("Withdrawal successful.");
        }
    }

    private static void checkBalance() {
        BankAccount acc = findAccountPrompt();
        if (acc != null) {
            System.out.println("Current Balance: " + acc.getBalance());
        }
    }

    private static BankAccount findAccountPrompt() {
        System.out.print("Enter account number: ");
        int accNo = scanner.nextInt();
        return findAccount(accNo);
    }

    private static BankAccount findAccount(int accNo) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber() == accNo) {
                return acc;
            }
        }
        System.out.println("Account not found.");
        return null;
    }

    private static void saveAccounts() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (BankAccount acc : accounts) {
                pw.println(acc);
            }
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    private static void loadAccounts() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                accounts.add(BankAccount.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading data.");
        }
    }
}
