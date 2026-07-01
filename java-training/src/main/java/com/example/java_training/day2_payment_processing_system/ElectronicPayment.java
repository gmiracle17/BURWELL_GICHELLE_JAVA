package com.example.java_training.day2_payment_processing_system;

import java.math.BigDecimal;

public abstract sealed class ElectronicPayment extends PaymentProcessor permits CreditCard, PayPal {
	protected ElectronicPayment(BigDecimal amount) { 
		super(amount); 
	}
}
