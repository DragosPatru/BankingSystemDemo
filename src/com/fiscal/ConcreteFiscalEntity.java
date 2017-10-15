package com.fiscal;

import com.banking.account.AccountSummary;
import com.banking.events.*;

import java.util.List;
import java.util.Vector;

public class ConcreteFiscalEntity implements FiscalEntity {

	private Vector<FinancialStatus> financialReports;
	private String name;
	
	public ConcreteFiscalEntity(String name) {
		this.name = name;
		financialReports = new Vector<FinancialStatus>();
	}
	
	@Override
	public void inform(Event e) {
		// TODO Auto-generated method stub
		if (e instanceof FinancialStatus) {
			FinancialStatus financialStatus = (FinancialStatus )e;
			// get the last record about the customer
			FinancialStatus lastSavedStatus = null;
			for (FinancialStatus status : financialReports) {
				if (status.getCustomerIdentifier().equals(financialStatus.getCustomerIdentifier())) {
					lastSavedStatus = status;
				}
			}
			
			logDifference(lastSavedStatus, financialStatus);
			// add new report to list
			financialReports.addElement(financialStatus);
		}
	}

	private void logDifference(FinancialStatus oldStatus, FinancialStatus newStatus) {
		if (oldStatus == null && newStatus == null) {
			System.out.println("No info about customer");
		}
		else {
			if (oldStatus == null) {
				System.out.println("\n" + this.getName()+" - first info about customer \n" + newStatus.getAccountsSummary().toString());
			
			} else {
				List <AccountSummary> oldSummaries = oldStatus.getAccountsSummary();
				List <AccountSummary> newSummaries = newStatus.getAccountsSummary();
				
				for (AccountSummary newSummary : newSummaries) {
					for (AccountSummary oldSummary : oldSummaries) {
						if (newSummary.getAccountNumber().equals(oldSummary.getAccountNumber())) {
							if (newSummary.getAmount() != oldSummary.getAmount()) {
								System.out.println("\n" + this.getName()+"\nAccount:" + newSummary.getAccountNumber() + " has new balance: "+ newSummary.getAmount() + " "+ String.valueOf(newSummary.getCurrrency()));
								break;
							}
						}
					}
				}
			}
			
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
