package com.banking.account;

public class AccountSummary {
	
	private Currency currrency;
	private double amount;
	private String accountNumber;
	
	public AccountSummary(Currency currrency, double amount, String accountNumber) {
		super();
		this.currrency = currrency;
		this.amount = amount;
		this.accountNumber = accountNumber;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Currency getCurrrency() {
		return currrency;
	}
	public void setCurrrency(Currency currrency) {
		this.currrency = currrency;
	}
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Override 
	public String toString() {
		String output = String.format("Account %s, Current Balance: %f",this.getAccountNumber(), this.getAmount());
		return output;
		
	}
}
