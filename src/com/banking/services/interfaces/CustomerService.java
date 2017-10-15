package com.banking.services.interfaces;

import com.banking.account.Currency;
import com.banking.customer.Customer;
import com.banking.eventservice.Publisher;
import com.banking.exception.AccountNotFoundException;
import com.banking.exception.CustomerNotRegisteredException;
import com.banking.exception.InsufficientFundsException;
import com.banking.exception.InvalidAmountException;
import com.banking.exception.MinimumAmountException;
import com.banking.exception.NonZeroAmountException;

public interface CustomerService extends Publisher{

	void registerCustomer(Customer customer);
	
	void withdraw(Customer customer, Currency currency, double amount) throws InsufficientFundsException, InvalidAmountException, AccountNotFoundException, MinimumAmountException;
	
	void deposit(Customer customer, Currency currency, double amount) throws InvalidAmountException, AccountNotFoundException;
	
	String balanceOfBankAccounts(Customer customer) throws CustomerNotRegisteredException;
	
	void closeAccounts(Customer customer) throws NonZeroAmountException, CustomerNotRegisteredException;
}
