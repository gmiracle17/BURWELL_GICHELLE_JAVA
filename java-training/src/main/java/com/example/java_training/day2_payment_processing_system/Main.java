package com.example.java_training.day2_payment_processing_system;

import java.math.BigDecimal;
public class Main {
	public static void main(String[] args) {
		BigDecimal creditCardAmount = new BigDecimal("100500.64");
		BigDecimal paypalAmount = new BigDecimal("100.56");
		BigDecimal bankTransferAmount = new BigDecimal("50000.00");
	
		// Polymorphism: Mixing discrete implementations inside an interface array
		PaymentMethod[] basket = {
			new CreditCard(creditCardAmount, "TXN0010156563773"),
			new PayPal(paypalAmount, "gc@gmail.com"),
			new BankTransfer(bankTransferAmount, "TXN0032567")
		};
	
		for (PaymentMethod payment : basket) {
			payment.processPayment();
		}
		
		// Managing data using an immutable record
		TransactionReceipt receipt = new TransactionReceipt("TXN-001", creditCardAmount);
		System.out.println("Receipt: " + receipt.id() + " | Amount: $" + receipt.amount());
	}
}