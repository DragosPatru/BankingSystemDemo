package com.banking.customer;

public class Customer {

	private String name;
	private String CNP;
	
	public Customer(String name, String cNP) {
		super();
		this.name = name;
		CNP = cNP;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCNP() {
		return CNP;
	}
	
	public void setCNP(String cNP) {
		CNP = cNP;
	}
	
	@Override
	public boolean equals(Object other){
		
	    if (other == null) { 
	    	return false;
	    }
	    if (other == this) { 
	    	return true;
	    }
	    if (!(other instanceof Customer)) {
	    	return false;
	    }
	    
	    if (this.getCNP().equals(((Customer)other).getCNP())) {
	    		return true;
	    }
	    
	    return false;
	}
	
}
