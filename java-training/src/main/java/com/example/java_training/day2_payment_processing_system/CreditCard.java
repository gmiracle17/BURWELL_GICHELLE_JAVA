package com.example.java_training.day2_payment_processing_system;

import java.math.BigDecimal;

public final class CreditCard extends ElectronicPayment {
	private String cardNumber;

	public CreditCard(BigDecimal amount, String cardNumber) {
		super(amount);
		this.cardNumber = cardNumber;
	}
	
	public Boolean verifyPaymentDetails() {
		if (cardNumber.length() == 16) {
			return true;
		}
		else {
			System.out.println("You entered an invalid card number.");
			return false;
		}
	}
	
	@Override
	public void processPayment() {
		System.out.println("Processing Credit Card payment...");
	}
	
	public String getCardNumber() {
		return cardNumber;
	}

}
