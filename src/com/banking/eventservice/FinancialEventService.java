package com.banking.eventservice;

import java.util.Collections;
import java.util.Vector;

import com.banking.customer.Customer;
import com.banking.events.Event;
import com.banking.events.FinancialStatus;
import com.fiscal.FiscalEntity;

/**
 * Inform all objects interested in the changes of customer account
 * when the CustomerService did an operation: deposit/ withdraw
 * */


public class FinancialEventService {

	private class Subscription {

		private Customer customer;
		private FiscalEntity fiscalEntity;

		public Subscription(Customer customer, FiscalEntity fiscalEntity) {
			this.customer = customer;
			this.fiscalEntity = fiscalEntity;
		}

		public Customer getCustomer() {
			return customer;
		}

		public FiscalEntity getFiscalEntity() {
			return fiscalEntity;
		}
		
		@Override
		public boolean equals(Object other){
			
		    if (other == null) { 
		    	return false;
		    }
		    if (other == this) { 
		    	return true;
		    }
		    if (!(other instanceof Subscription)) {
		    	return false;
		    }
		    
		    Subscription subscription = (Subscription) other;
		    if (this.getCustomer().equals(subscription.getCustomer()) && this.getFiscalEntity().equals(subscription.getFiscalEntity())) {
		    		return true;
		    }
		    
		    return false;
		}
	}
	

	private static Vector<Subscription> subscriptions;
	static private FinancialEventService singleton = null;
	
	
	private FinancialEventService() {
		subscriptions = new Vector<Subscription>();
	}

	// Provides well-known access point to singleton EventService
	static public FinancialEventService instance() {
		if (singleton == null) {
			singleton = new FinancialEventService();
			
			subscriptions = new Vector<Subscription>();
		}
		return singleton;
	}

	public void subscribe(FiscalEntity fiscal, Customer customer) {
		Subscription subscription = new Subscription(customer, fiscal);
		// make sure it is only one subscription 
		this.unsubscribe(fiscal, customer);
		
		FinancialEventService.subscriptions.add(subscription);
	}

	/// Remove all subscriptions (Client, fiscalEntity)
	public void unsubscribe(FiscalEntity fiscal, Customer customer) {
		FinancialEventService.subscriptions.removeAll(Collections.singleton(new Subscription(customer, fiscal)));
	}

	public void inform(Event event) {
		for (Subscription subscription : subscriptions) {
			if (event instanceof FinancialStatus) { // for type safety
				
				FinancialStatus status = (FinancialStatus) event;
				// same customer identifiers
				if (status.getCustomerIdentifier().equals(subscription.getCustomer().getCNP())) {
					// inform the fiscal entity
					subscription.getFiscalEntity().inform(event);
				}
			}
		}
	}

}
