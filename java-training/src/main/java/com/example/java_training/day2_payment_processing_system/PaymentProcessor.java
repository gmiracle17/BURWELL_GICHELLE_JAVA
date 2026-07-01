package com.example.java_training.day2_payment_processing_system;

import java.math.BigDecimal;

public abstract sealed class PaymentProcessor implements PaymentMethod permits ElectronicPayment, BankTransfer{
	// Encapsulation: Hidden private variable
	private final BigDecimal amount;
	
	protected PaymentProcessor(BigDecimal amount) { 
		this.amount = amount; 
	}
	
	public void showPaymentAmount() {
		System.out.println("PHP " + amount + " paid.");
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public abstract void processPayment();

}
