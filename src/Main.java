import com.banking.customer.Customer;
import com.banking.exception.AccountNotFoundException;
import com.banking.exception.CustomerNotRegisteredException;
import com.banking.exception.InsufficientFundsException;
import com.banking.exception.InvalidAmountException;
import com.banking.exception.MinimumAmountException;
import com.banking.exception.NonZeroAmountException;
import com.banking.services.interfaces.CustomerService;
import com.banking.services.interfaces.FiscalService;
import com.fiscal.ConcreteFiscalEntity;
import com.banking.*;
import com.banking.account.Currency;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Customer customerOne = new Customer("NameOne", "123456789");
		Customer customerTwo = new Customer("NameTwo", "912345678");
		Customer customerThree = new Customer("NameThree", "812345678");
		Customer customerFour = new Customer("NameFOur", "712345678");

		CustomerService customerService = new CustomerServiceImpl();

		customerService.registerCustomer(customerOne);
		customerService.registerCustomer(customerTwo);
		customerService.registerCustomer(customerThree);
		customerService.registerCustomer(customerFour);

		FiscalService fiscalService = new FiscalServiceImpl();

		// Create fiscal entities
		ConcreteFiscalEntity fiscalEntity = new ConcreteFiscalEntity("Fisc");
		ConcreteFiscalEntity fiscalEntityTwo = new ConcreteFiscalEntity("FiscTwo");
		// subscribe fiscal entity
		try {
			fiscalService.startMonitoring(customerOne, fiscalEntity);
			fiscalService.startMonitoring(customerOne, fiscalEntity);
			fiscalService.startMonitoring(customerOne, fiscalEntityTwo);

		} catch (CustomerNotRegisteredException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}

		try {

			System.out.println(customerService.balanceOfBankAccounts(customerOne));
			// Deposit EUR
			customerService.deposit(customerOne, Currency.EUR, 5000);
			// Deposit RON
			try {
				customerService.closeAccounts(customerOne);
			} catch (NonZeroAmountException e) {
				// TODO Auto-generated catch block
				System.out.println("\nError: \n" + e.getMessage());
			}

			customerService.deposit(customerOne, Currency.RON, 5000);
			System.out.println(customerService.balanceOfBankAccounts(customerOne));

			// subscribe fiscal entity
			try {
				fiscalService.startMonitoring(customerTwo, fiscalEntity);

			} catch (CustomerNotRegisteredException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}

			customerService.deposit(customerTwo, Currency.RON, 15000);
			customerService.withdraw(customerTwo, Currency.RON, 14000);

			customerService.deposit(customerOne, Currency.RON, 14000);

			// FISC ONE STOP MONITORING CUSTOMER ONE
			try {
				
				fiscalService.stopMonitoring(customerOne, fiscalEntity);

			} catch (CustomerNotRegisteredException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}
			System.out.println("\nDeposit again - only FiscalTwo should be informed about change");
			customerService.deposit(customerOne, Currency.EUR, 20);
			
			
			
		} catch (InsufficientFundsException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());

		} catch (InvalidAmountException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());

		} catch (AccountNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());

		} catch (MinimumAmountException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());

		} catch (CustomerNotRegisteredException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

		System.out.println("Done");
	}

}
