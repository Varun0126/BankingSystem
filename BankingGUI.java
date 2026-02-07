package BankingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;

public class BankingGUI extends JFrame implements ActionListener {

    private JTextField accField, nameField, amtField;
    private JButton createBtn, depositBtn, withdrawBtn, balanceBtn;

    private HashMap<Integer, BankAccount> accounts = new HashMap<>();
    private static final String FILE_NAME = "accounts.txt";

    public BankingGUI() {
        setTitle("Simple Banking System");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 10, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        accField = new JTextField();
        nameField = new JTextField();
        amtField = new JTextField();

        createBtn = new JButton("Create Account");
        depositBtn = new JButton("Deposit");
        withdrawBtn = new JButton("Withdraw");
        balanceBtn = new JButton("Check Balance");

        add(new JLabel("Account Number"));
        add(accField);
        add(new JLabel("Name"));
        add(nameField);
        add(new JLabel("Amount"));
        add(amtField);

        add(createBtn);
        add(depositBtn);
        add(withdrawBtn);
        add(balanceBtn);

        createBtn.addActionListener(this);
        depositBtn.addActionListener(this);
        withdrawBtn.addActionListener(this);
        balanceBtn.addActionListener(this);

        loadAccounts();   // 🔥 LOAD FROM FILE

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ================= BUTTON LOGIC =================
    @Override
    public void actionPerformed(ActionEvent e) {

        int accNo;
        double amount;

        try {
            accNo = Integer.parseInt(accField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid account number");
            return;
        }

        if (e.getSource() == createBtn) {

            if (accounts.containsKey(accNo)) {
                JOptionPane.showMessageDialog(this, "Account already exists");
                return;
            }

            String name = nameField.getText();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name cannot be empty");
                return;
            }

            accounts.put(accNo, new BankAccount(accNo, name, 0));
            saveAccounts();
            JOptionPane.showMessageDialog(this, "Account Created!");

        } else {

            BankAccount acc = accounts.get(accNo);
            if (acc == null) {
                JOptionPane.showMessageDialog(this, "Account not found");
                return;
            }

            try {
                amount = Double.parseDouble(amtField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount");
                return;
            }

            if (e.getSource() == depositBtn) {

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Invalid amount");
                    return;
                }

                acc.deposit(amount);
                saveAccounts();
                JOptionPane.showMessageDialog(this, "Amount Deposited");

            } else if (e.getSource() == withdrawBtn) {

                if (!acc.withdraw(amount)) {
                    JOptionPane.showMessageDialog(this, "Insufficient Balance");
                } else {
                    saveAccounts();
                    JOptionPane.showMessageDialog(this, "Withdrawal Successful");
                }

            } else if (e.getSource() == balanceBtn) {
                JOptionPane.showMessageDialog(this,
                        "Balance: " + acc.getBalance());
            }
        }
    }

    // ================= FILE HANDLING =================

    private void saveAccounts() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (BankAccount acc : accounts.values()) {
                pw.println(acc.toString());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving data");
        }
    }

    private void loadAccounts() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                BankAccount acc = BankAccount.fromString(line);
                accounts.put(acc.getAccountNumber(), acc);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading data");
        }
    }

    public static void main(String[] args) {
        new BankingGUI();
    }
}
