package com.banking.factory;

import java.util.ArrayList;
import java.util.List;

import com.banking.account.*;
import com.banking.events.*;
import com.banking.customer.Customer;

public class EventFactory {

	public static FinancialStatus createFinancialStatusEvent (Customer customer, List<BankAccount> accounts) {
		FinancialStatus financialStatus = null;
		
		
		if (customer != null && accounts != null && accounts.size() > 0) {
			ArrayList<AccountSummary> summaries = new ArrayList<AccountSummary>(2);
			
			for (BankAccount account : accounts) {
				AccountSummary summary = new AccountSummary(account.getCurrency(), account.getBalance(), account.getAccountNumber());
				summaries.add(summary);
			}
			
			financialStatus = new FinancialStatus(customer.getCNP(),summaries);
		}
		
		
		
		return financialStatus;
	}
}
