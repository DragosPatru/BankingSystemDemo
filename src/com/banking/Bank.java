package com.banking;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.banking.account.BankAccount;
import com.banking.account.Currency;
import com.banking.customer.Customer;


public class Bank {

	private Vector<BankAccount> bankAccounts;

	private Bank() {
		super();
		bankAccounts = new Vector<BankAccount>(0);
	}

	static private Bank singleton = null;


	// Provides well-known access point to singleton Bank
	static public Bank instance() {
		if (singleton == null)
			singleton = new Bank();
		return singleton;
	}
	
	
	/**
	 * @param customer
	 * @param currency
	 *            Returns the customer Account if any
	 */
	public BankAccount getCustomerAccount(Customer customer, Currency currency) {
		BankAccount bankAccount = null;

		for (BankAccount bankAcc : bankAccounts) {
			if (bankAcc.getOwner().equals(customer)) {
				if (bankAcc.getCurrency() == currency) {
					bankAccount = bankAcc;
					break;
				}
			}

		}

		return bankAccount;
	}

	/**
	 * @param customer
	 * @param currency
	 * Returns a list of customer accounts
	 */
	public List<BankAccount> getCustomerAccounts(Customer customer) {
		List<BankAccount> accounts = new ArrayList<BankAccount>(0);

		for (BankAccount bankAcc : this.bankAccounts) {
			if (bankAcc.getOwner().equals(customer)) {
				accounts.add(bankAcc);
			}

		}

		return accounts;
	}

	/**
	 * Add a new account to the list of accounts
	 * */
	public void addAccount (BankAccount bankAccount) {
		if (bankAccount != null) {
			this.bankAccounts.addElement(bankAccount);
		}
	}
	
	/**
	 * Remove account from the list of accounts
	 * */
	public void removeAccount (BankAccount bankAccount) {
		if (bankAccount != null) {
			this.bankAccounts.removeElement(bankAccount);
		}
	}
	
	/**
	 * Check if the customer is a bank client or not
	 * */
	public boolean isCustomerRegistered(Customer customer) {
		
		if (this.getCustomerAccounts(customer).size() > 0) {
			return true;
		} 
		return false;
	}
}
