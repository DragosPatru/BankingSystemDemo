package com.banking.factory;

import java.util.UUID;

import com.banking.account.BankAccount;
import com.banking.account.Currency;
import com.banking.customer.Customer;

public class AccountFactory {

	public static BankAccount createBankAccount(Customer customer, Currency currency) {
		String randomUUID = UUID.randomUUID().toString();
		BankAccount bankAccount = new BankAccount(randomUUID, 0, currency, customer);
		
		return bankAccount;
	}
}
