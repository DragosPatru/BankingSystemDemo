package com.banking.events;

import java.util.List;
import com.banking.account.AccountSummary;

public class FinancialStatus implements Event {

	private String customerIdentifier;
	private List<AccountSummary> accountsSummary;

	public FinancialStatus(String customerIdentifier, List<AccountSummary> accountsSummary) {
		super();
		this.customerIdentifier = customerIdentifier;
		this.accountsSummary = accountsSummary;
	}
	

	public String getCustomerIdentifier() {
		return customerIdentifier;
	}


	public void setCustomerIdentifier(String customerIdentifier) {
		this.customerIdentifier = customerIdentifier;
	}


	public List<AccountSummary> getAccountsSummary() {
		return accountsSummary;
	}


	public void setAccountsSummary(List<AccountSummary> accountsSummary) {
		this.accountsSummary = accountsSummary;
	}

	
}
