package com.banking;

import com.banking.customer.Customer;
import com.banking.eventservice.*;
import com.banking.exception.CustomerNotRegisteredException;
import com.banking.services.interfaces.FiscalService;
import com.fiscal.FiscalEntity;

public class FiscalServiceImpl implements FiscalService {

	@Override
	public void startMonitoring(Customer customer, FiscalEntity fiscalEntity) throws CustomerNotRegisteredException {
		if (customer != null) {
			boolean isBankClient = Bank.instance().isCustomerRegistered(customer);
			
			if (isBankClient) {
				// subscribe for events about the customer
				FinancialEventService.instance().subscribe(fiscalEntity, customer);			
			}
		}
	}

	@Override
	public void stopMonitoring(Customer customer, FiscalEntity fiscalEntity) throws CustomerNotRegisteredException {
		if (customer != null) {
			boolean isBankClient = Bank.instance().isCustomerRegistered(customer);
			
			if (isBankClient) {
				// subscribe for events about the customer
				FinancialEventService.instance().unsubscribe(fiscalEntity, customer);			
			}
		}
		
	}



}
