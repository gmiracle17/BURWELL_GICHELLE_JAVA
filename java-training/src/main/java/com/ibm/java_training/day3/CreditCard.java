package com.ibm.java_training.day3;

import java.math.BigDecimal;

/**
 * CreditCard Class
 * - inherits from ElectronicPayment that inherits from PaymentProcessor
 */
public final class CreditCard extends ElectronicPayment {
    private String cardNumber;
    
    public CreditCard(BigDecimal amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }
    
    @Override
    public Boolean verifyPaymentDetails() {
        if (cardNumber.matches("\\d{16}")) { return true; }
        else { return false; }
    }
    
    // Shows polymorphism as processPayment here has its own process (method is from PaymentMethod)
    @Override
    public void processPayment() {
        if (verifyPaymentDetails()) {
            markAsProcessed();
            System.out.println("Processed Credit Card Payment for " + getCardNumber());
        }
        else {
        	System.out.println("You entered an invalid Credit Card number.");
        }
    }
    
    @Override
    public void displayPaymentInfo() {
        showPaymentAmount();
        System.out.println("Card Number: " + getCardNumber());
    }
    
    public String getCardNumber() {
        return cardNumber;
    }
}