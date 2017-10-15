package com.banking.services.interfaces;

import com.banking.customer.Customer;
import com.banking.exception.CustomerNotRegisteredException;
import com.fiscal.FiscalEntity;

public interface FiscalService {
	/// Start the monitoring process
	void startMonitoring(Customer customer, FiscalEntity fiscalEntity) throws CustomerNotRegisteredException;
	
	/// Stop the monitoring process
	void stopMonitoring(Customer customer, FiscalEntity fiscalEntity) throws CustomerNotRegisteredException;
}
