package BankingSystem;

import java.io.Serializable;

public class BankAccount implements Serializable {

    private int accountNumber;
    private String name;
    private double balance;

    public BankAccount(int accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return accountNumber + "," + name + "," + balance;
    }

    public static BankAccount fromString(String line) {
        String[] data = line.split(",");
        return new BankAccount(
                Integer.parseInt(data[0]),
                data[1],
                Double.parseDouble(data[2])
        );
    }
}
