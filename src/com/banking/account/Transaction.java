package com.banking.account;

import java.time.Instant;


public class Transaction {
	
	private long timeStampMillis;
	private TransactionType transactionType;
	private double amountOfMoney;
	
	public Transaction(TransactionType transactionType, double amountOfMoney) {
		super();
		this.transactionType = transactionType;
		this.amountOfMoney = amountOfMoney;
		Instant instant = Instant.now();
		this.timeStampMillis = instant.toEpochMilli();
	}
	
	public long getTimeStampMillis() {
		return timeStampMillis;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public double getAmountOfMoney() {
		return amountOfMoney;
	}
	
	@Override 
	public String toString() {
		String serializedForm = String.format("\nDate: %tD \t TransactionType: %s \t Money: %f", timeStampMillis, String.valueOf(transactionType), amountOfMoney);
		return serializedForm;
	}
}
