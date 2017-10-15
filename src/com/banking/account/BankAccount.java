package com.banking.account;

import java.util.ArrayList;
import java.util.List;

import com.banking.customer.Customer;
import com.banking.exception.InsufficientFundsException;
import com.banking.exception.InvalidAmountException;

public class BankAccount {
	
	private String accountNumber;
	private double balance;
	private Customer owner;
	private Currency currency;
	
	private List<Transaction> transactions;

	public BankAccount(String accountNumber, double initialBalance, Currency currency, Customer owner) {
		super();
		this.accountNumber = accountNumber;
		this.balance = initialBalance;
		this.owner = owner;
		this.transactions = new ArrayList<Transaction>(0);
		this.currency = currency;
	}
	

	public String getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public Customer getOwner() {
		return owner;
	}

	public Currency getCurrency() {
		return currency;
	}


	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void deposit(double money) throws InvalidAmountException {
		if (money > 0) {
			this.balance += money;
			
		} else {
			throw new InvalidAmountException("DEPOSIT Operation with negative amount of money");
		}
	}

	public void withdraw(double money) throws InsufficientFundsException, InvalidAmountException {
		if (money < 0) {
			// throw an exception
			throw new InvalidAmountException("WITHDRAW Operation with negative amount of money");
		}
		
		if (money > balance) {
			// throw another exception
			throw new InsufficientFundsException(String.format("Insufficient funds. Current Balance of account %s is %d %s", this.accountNumber, this.getBalance() ,String.valueOf(this.currency)));
		} 
		
		this.balance -= money;
	}
	
	@Override
	public String toString() {
		String accountStatus = String.format("\nAccount number: %s, Balance: %f %s ", this.accountNumber, this.getBalance() ,String.valueOf(this.currency));
		
		return accountStatus;
	}
	
	/**
	 * Returns the serialized form of all account transactions
	 * */
	public String accoutHistory() {
		String accountHistory = String.format("\nAccount number: %s, Balance: %f %s ", this.accountNumber, this.getBalance() ,String.valueOf(this.currency));
		if (this.getTransactions().size() > 0) {
			accountHistory = accountHistory + this.getTransactions().toString();
		}
		
		return accountHistory;
	}
	
	@Override
	public boolean equals(Object other){
		
	    if (other == null) { 
	    	return false;
	    }
	    if (other == this) { 
	    	return true;
	    }
	    if (!(other instanceof BankAccount)) {
	    	return false;
	    }
	    
	    BankAccount otherBankAccount = (BankAccount) other;
	    if (this.getAccountNumber().equals(otherBankAccount.getAccountNumber()) && this.getOwner().equals(otherBankAccount.getOwner())) {
	    		return true;
	    }
	    
	    return false;
	}
}
