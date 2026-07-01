package com.example.java_training.day2_payment_processing_system;

import java.math.BigDecimal;
public non-sealed class BankTransfer extends PaymentProcessor {
	private String accountNumber;
	
	public BankTransfer(BigDecimal amount, String accountNumber) { 
		super(amount); 
		this.accountNumber = accountNumber;
	}
	
	public Boolean verifyPaymentDetails() {
		if (accountNumber.length() == 10) {
			return true;
		}
		else {
			System.out.println("You entered an invalid card number.");
			return false;
		}
	}
	
	@Override
	public void processPayment() {
		System.out.println("Processing Bank Transfer...");
	}
	
	public String accountNumber() {
		return accountNumber;
	}
}