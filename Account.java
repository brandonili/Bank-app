package bank;

import java.util.ArrayList;

public abstract class Account {
private String holderName, accountNumber;
private double balance;
private ArrayList<String> transactions;
  
public Account()
{
this.holderName = "";
this.accountNumber = "";
this.balance = 0;
this.transactions = new ArrayList<>();
}

public Account(String holderName, String accountNumber, double balance) {
this.holderName = holderName;
this.accountNumber = accountNumber;
this.balance = balance;
this.transactions = new ArrayList<>();
}

public String getHolderName() {
return holderName;
}

public String getAccountNumber() {
return accountNumber;
}

public double getBalance() {
return balance;
}

public void setBalance(double balance) {
this.balance = balance;
}
  
public ArrayList<String> getTransactions() {
return transactions;
}
  
abstract public void deposit(double amt);
abstract public void withdraw(double amt);
abstract public void transfer(Account acc, double amt);
}