import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TheBankApp {

    private static JFrame mainFrame;
    private static JPanel mainPanel, loginPanel, detailsPanel, buttonsPanel;
    private static JLabel loginAccLabel;
    private static JTextField loginField;
    private static JButton loginButton;

    private static JLabel holderNameLabel;
    private static JTextField holderNameField;
    private static JLabel accountNumberLabel;
    private static JTextField accountNumberField;
    private static JLabel balanceLabel;
    private static JTextField balanceField;

    private static JButton depositButton, withdrawButton, transferButton, writeButton;

    private static final String FILENAME = "transactions_file.txt";
    private static Account owner = null;

    public static void main(String[] args) {
        // load all accounts
        ArrayList<Account> accounts = loadAccounts();

        mainFrame = new JFrame("The Banking App");
        mainPanel = new JPanel(new GridLayout(3, 0));

        loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginAccLabel = new JLabel("Enter account number: ");
        loginField = new JTextField(25);
        loginButton = new JButton("Log In");
        loginPanel.add(loginAccLabel);
        loginPanel.add(loginField);
        loginPanel.add(loginButton);

        detailsPanel = new JPanel(new GridLayout(3, 2));
        holderNameLabel = new JLabel("Holder Name: ");
        holderNameField = new JTextField(20);
        holderNameField.setEditable(false);
        accountNumberLabel = new JLabel("Account Number: ");
        accountNumberField = new JTextField(25);
        accountNumberField.setEditable(false);
        balanceLabel = new JLabel("Account Balance: ");
        balanceField = new JTextField(10);
        balanceField.setEditable(false);
        detailsPanel.add(holderNameLabel);
        detailsPanel.add(holderNameField);
        detailsPanel.add(accountNumberLabel);
        detailsPanel.add(accountNumberField);
        detailsPanel.add(balanceLabel);
        detailsPanel.add(balanceField);

        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        transferButton = new JButton("Transfer");
        writeButton = new JButton("Write Transactions to File");
        depositButton.setEnabled(false);
        withdrawButton.setEnabled(false);
        transferButton.setEnabled(false);
        writeButton.setEnabled(false);
        buttonsPanel.add(depositButton);
        buttonsPanel.add(withdrawButton);
        buttonsPanel.add(transferButton);
        buttonsPanel.add(writeButton);

        mainPanel.add(loginPanel);
        mainPanel.add(detailsPanel);
        mainPanel.add(buttonsPanel);

        mainFrame.add(mainPanel);
        mainFrame.setSize(500, 250);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        // action listener for log in button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginField.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Please enter an account number!");
                else {
                    String accNum = loginField.getText().trim();
                    if (searchAccount(accounts, accNum) == -1)
                        JOptionPane.showMessageDialog(null, "No accounts found for account number: " + accNum);
                    else {
                        owner = accounts.get(searchAccount(accounts, accNum));
                        holderNameField.setText(owner.getHolderName());
                        accountNumberField.setText(owner.getAccountNumber());
                        balanceField.setText(String.format("$ %.2f", owner.getBalance()));

                        depositButton.setEnabled(true);
                        withdrawButton.setEnabled(true);
                        transferButton.setEnabled(true);
                        writeButton.setEnabled(true);
                    }
                }
            }
        });

        // action listener for deposit button
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inp = JOptionPane.showInputDialog("Please enter the amount to deposit:");
                while (!isDouble(inp)) {
                    inp = JOptionPane.showInputDialog("Please enter the amount to deposit:");
                }
                double amount = Double.parseDouble(inp);
                owner.deposit(amount);
                balanceField.setText(String.format("$ %.2f", owner.getBalance()));
            }
        });

        // action listener for withdraw button
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inp = JOptionPane.showInputDialog("Please enter the amount to withdraw:");
                while (!isDouble(inp)) {
                    inp = JOptionPane.showInputDialog("Please enter the amount to withdraw:");
                }
                double amount = Double.parseDouble(inp);
                owner.withdraw(amount);
                balanceField.setText(String.format("$ %.2f", owner.getBalance()));
            }
        });

        // action listener for transfer button
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accNum = JOptionPane.showInputDialog("Please enter the account number to receive the amount:");
                if (accNum.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid account number!");
                    return;
                }
                int index = searchAccount(accounts, accNum);
                if (index == -1) {
                    JOptionPane.showMessageDialog(null, "No accounts found for account number: " + accNum);
                    return;
                }

                Account transfer = accounts.get(index);
                String inp = JOptionPane.showInputDialog("Please enter the amount to transfer:");
                while (!isDouble(inp)) {
                    inp = JOptionPane.showInputDialog("Please enter the amount to transfer:");
                }
                owner.transfer(transfer, index);
                balanceField.setText(String.format("$ %.2f", owner.getBalance()));
            }
        });

        // action listener for write button
        writeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (owner != null && owner.getTransactions().isEmpty())
                    JOptionPane.showMessageDialog(null, "There are no current transactions to write!");
                else
                    writeTransactionsToFile(owner.getTransactions(), FILENAME);
            }
        });
    }

    private static ArrayList<Account> loadAccounts() {
        ArrayList<Account> accs = new ArrayList<>();

        accs.add(new Savings("Brandon", "1111111111111111", 5000));
        accs.add(new Checking("Joel", "2222222222222222", 3990));
        accs.add(new Savings("Henry", "3333333333333333", 5000));
        accs.add(new Checking("Zach", "4444444444444444", 4500));
        accs.add(new Savings("Admin", "0000000000000000", 0000));

        return accs;
    }

    private static int searchAccount(ArrayList<Account> accounts, String accountNumber) {
        int index = -1;

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber().equals(accountNumber)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static void writeTransactionsToFile(ArrayList<String> trans, String filename) {
        FileWriter fw;
        PrintWriter pw;
        try {
            fw = new FileWriter(new File(filename));
            pw = new PrintWriter(fw);

            for (String tr : trans) {
                pw.write(tr + System.lineSeparator());
            }

            pw.flush();
            fw.close();
            pw.close();
            JOptionPane.showMessageDialog(null, trans.size() + " transactions have been written to file: " + filename);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in writing data to " + filename);
            System.exit(0);
        }
    }

    private static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, s + " is not a valid double value!");
            return false;
        }
    }
}
