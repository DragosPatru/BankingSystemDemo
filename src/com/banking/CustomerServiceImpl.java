package com.banking;

import java.util.List;

import com.banking.account.BankAccount;
import com.banking.account.Currency;
import com.banking.customer.Customer;
import com.banking.events.Event;
import com.banking.events.FinancialStatus;
import com.banking.eventservice.FinancialEventService;
import com.banking.exception.AccountNotFoundException;
import com.banking.exception.CustomerNotRegisteredException;
import com.banking.exception.InsufficientFundsException;
import com.banking.exception.InvalidAmountException;
import com.banking.exception.MinimumAmountException;
import com.banking.exception.NonZeroAmountException;
import com.banking.factory.AccountFactory;
import com.banking.factory.EventFactory;
import com.banking.services.interfaces.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	@Override
	/// Creates 2 accounts for the customer
	public void registerCustomer(Customer customer) {
		// TODO Auto-generated method stub

		if (customer != null) {	
			BankAccount eurAccount = AccountFactory.createBankAccount(customer, Currency.EUR);
			BankAccount ronAccount = AccountFactory.createBankAccount(customer, Currency.RON);
		
			Bank.instance().addAccount(eurAccount);
			Bank.instance().addAccount(ronAccount);
		}
	}

	@Override
	public void withdraw(Customer customer, Currency currency, double amount)
			throws InsufficientFundsException, InvalidAmountException, AccountNotFoundException, MinimumAmountException {
		// TODO Auto-generated method stub
		if (customer != null) {
			BankAccount account = Bank.instance().getCustomerAccount(customer, currency);

			if (account == null) {
				throw new AccountNotFoundException(
						String.format("Not found an account having currency %s for customer %s",
								String.valueOf(currency), customer.getCNP()));
			}
			
			if ((account.getBalance() - amount) < Constants.MINIMUM_AMOUNT) {
				throw new MinimumAmountException(String.format("Customer's account %s should have at least %f",account.getAccountNumber(), Constants.MINIMUM_AMOUNT));
			}
			
			account.withdraw(amount);
			
			// POST THE EVENT
			this.createAndPostEventForCustomer(customer);
		}
	}

	@Override
	public void deposit(Customer customer, Currency currency, double amount)
			throws InvalidAmountException, AccountNotFoundException {
		if (customer != null) {
			BankAccount account = Bank.instance().getCustomerAccount(customer, currency);

			if (account == null) {
				throw new AccountNotFoundException(
						String.format("Not found an account having currency %s for customer %s",
								String.valueOf(currency), customer.getCNP()));
			}
			
			account.deposit(amount);
			
			// POST THE EVENT
			this.createAndPostEventForCustomer(customer);
		}

	}

	@Override
	public void closeAccounts(Customer customer) throws NonZeroAmountException, CustomerNotRegisteredException {
		if (customer != null) {
			List<BankAccount> accounts = Bank.instance().getCustomerAccounts(customer);

			if (accounts == null || accounts.size() == 0) {
				throw new CustomerNotRegisteredException(
						String.format("Customer %s is not registered", customer.getCNP()));
			}
			for (BankAccount account : accounts) {
				if (account.getBalance() > 0) {
					throw new NonZeroAmountException(String.format("Customer %s, have %f %s in the account with number: %s", customer.getName(),account.getBalance(), String.valueOf(account.getCurrency()), account.getAccountNumber()));
				}
			}
	
			// remove info about customer
			for (BankAccount account : accounts) {
				Bank.instance().removeAccount(account);
			}
			
		}

	}

	@Override
	public String balanceOfBankAccounts(Customer customer) throws CustomerNotRegisteredException {
		// get accounts
		String balanceOfAccountsSerialized = "";
		
		if (customer != null) {
			List<BankAccount> accounts = Bank.instance().getCustomerAccounts(customer);

			if (accounts == null || accounts.size() == 0) {
				throw new CustomerNotRegisteredException(
						String.format("Customer %s is not registered", customer.getCNP()));
			}
			
			for (BankAccount account : accounts) {
				balanceOfAccountsSerialized += account.toString();
			}
		}
		// return a merged String from toString on each account
		

		return balanceOfAccountsSerialized;
	}
	
	/**
	 * Create an event based on customer data
	 * Inform the event service about the event
	 * */
	private void createAndPostEventForCustomer (Customer customer) {
		if (customer != null) {
			List<BankAccount> accounts = Bank.instance().getCustomerAccounts(customer);
			// create the event based on customer data
			FinancialStatus status = EventFactory.createFinancialStatusEvent(customer, accounts);
			// post the event
			this.publish(status);
		}
	}

	@Override
	public void publish(Event event) {
		FinancialEventService.instance().inform(event);
		
	}

}
