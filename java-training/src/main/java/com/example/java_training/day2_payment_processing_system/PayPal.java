package com.example.java_training.day2_payment_processing_system;

import java.math.BigDecimal;

public final class PayPal extends ElectronicPayment {
	private String email;

	public PayPal(BigDecimal amount, String email) {
		super(amount);
		this.email = email;
	}
	
	public Boolean verifyPaymentDetails() {
		if (email.contains("@")) {
			return true;
		}
		else {
			System.out.println("You entered an invalid email.");
			return false;
		}
	}
	
	@Override
	public void processPayment() {
		System.out.println("Processing PayPal payment...");
	}
	
	public String getEmail() {
		return email;
	}
}